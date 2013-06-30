import language.postfixOps
import akka.actor.{ActorSystem, ActorRef, Props, Actor}
import akka.pattern.ask
import akka.util.Timeout
import spray.http.HttpRequest
import scala.concurrent.Future
import scala.xml.NodeSeq
import scala.concurrent.duration._
import spray.io._
import spray.util._
import spray.http._
import HttpMethods._
import spray.can.Http
import akka.io.IO
import spray.http._
import spray.client.pipelining._

object Main extends App {
  implicit val system: ActorSystem = ActorSystem("test")

  import system.dispatcher

  implicit val timeout: Timeout = 15 seconds
  
  def connection(): Future[SendReceive] = {
    for (
      Http.HostConnectorInfo(connector, _) <-
      IO(Http) ? Http.HostConnectorSetup("api.direct.yandex.ru", port = 443, sslEncryption = true)
    ) yield sendReceive(connector)
  }

  lazy val pipeline: HttpRequest => Future[String] = {
    request =>
      connection.map(c => c ~> unmarshal[String]).flatMap(_(request))
  }
  
  val response = pipeline(HttpRequest(method = POST, uri = "/json-api/v4/", entity = """{"method":"GetReportList","application_id":"xxx","login":"xxx","token":"xxxx"}"""))
  response map { r =>
    println(r)
    
    system.shutdown()
  }
}
