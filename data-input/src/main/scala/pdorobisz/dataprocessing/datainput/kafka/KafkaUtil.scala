package pdorobisz.dataprocessing.datainput.kafka

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import pdorobisz.dataprocessing.datainput.Configuration

import scala.collection.JavaConverters._


object KafkaUtil {

  private val props = new Properties()
  props.putAll(Map(
    "bootstrap.servers" -> s"${Configuration.kafkaServerHost}:${Configuration.kafkaServerPort}",
    "client.id" -> "dataInputApp",
    "key.serializer" -> "org.apache.kafka.common.serialization.IntegerSerializer",
    "value.serializer" -> "org.apache.kafka.common.serialization.StringSerializer"
  ).asJava)

  private val producer = new KafkaProducer[Integer, String](props)

  def publish(topic: String, data: String): Unit = {
    val record = new ProducerRecord[Integer, String](topic, data)
    producer.send(record)
  }
}
