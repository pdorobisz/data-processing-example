package pdorobisz.dataprocessing.spark.data

import java.util

import org.apache.kafka.common.serialization.{Deserializer, StringDeserializer}


class DataDeserializer extends Deserializer[EventData] {

  import DataJsonProtocol._
  import spray.json._

  private val stringDeserializer = new StringDeserializer

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = stringDeserializer.configure(configs, isKey)

  override def close(): Unit = stringDeserializer.close()

  override def deserialize(topic: String, data: Array[Byte]): EventData = new String(data).parseJson.convertTo[EventData]
}
