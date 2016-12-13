package pdorobisz.dataprocessing.datainput

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{complete, get, path, pathSingleSlash, _}
import akka.stream.ActorMaterializer
import pdorobisz.dataprocessing.datainput.api.model.{EventData, JsonSupport}

import scala.io.StdIn


object Main extends App with JsonSupport {

  private val ServerPort = 8080

  private implicit val system = ActorSystem("data-input-system")
  private implicit val materializer = ActorMaterializer()
  private implicit val executionContext = system.dispatcher

  val route =
    pathSingleSlash {
      get {
        complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "ok"))
      }
    } ~ path("data") {
      post {
        entity(as[EventData]) {
          eventData =>
            println(eventData)
            complete("ok")
        }
      }
    }

  val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", ServerPort)

  println(s"Server online at $ServerPort/\nPress RETURN to stop...")
  StdIn.readLine()

  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())
}
