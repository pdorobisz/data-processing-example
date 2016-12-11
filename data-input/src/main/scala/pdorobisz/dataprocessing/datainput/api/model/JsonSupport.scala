package pdorobisz.dataprocessing.datainput.api.model

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}


trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val eventDataProtocol: RootJsonFormat[EventData] = jsonFormat3(EventData)
}
