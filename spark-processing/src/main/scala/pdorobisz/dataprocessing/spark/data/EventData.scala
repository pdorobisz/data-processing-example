package pdorobisz.dataprocessing.spark.data

import spray.json.{DefaultJsonProtocol, RootJsonFormat}

case class EventData(applicationName: String, level: String, content: String)

object DataJsonProtocol extends DefaultJsonProtocol {
  implicit def eventDataFormat: RootJsonFormat[EventData] = jsonFormat3(EventData)
}