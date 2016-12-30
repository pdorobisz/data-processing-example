package pdorobisz.dataprocessing.datainput.api.model

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import pdorobisz.dataprocessing.datainput.api.model.EventLevel._
import spray.json.{DefaultJsonProtocol, JsString, JsValue, RootJsonFormat}


trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {

  implicit object EventLevelFormat extends RootJsonFormat[EventLevel] {
    override def read(json: JsValue): EventLevel = json match {
      case JsString("INFO") => Info
      case JsString("WARNING") => Warning
      case JsString("ERROR") => Error
    }

    override def write(obj: EventLevel): JsValue = JsString(obj.toString.toUpperCase)
  }

  implicit val eventDataFormat: RootJsonFormat[EventData] = jsonFormat3(EventData)
}
