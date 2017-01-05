package pdorobisz.dataprocessing.spark

import org.apache.kafka.common.serialization.IntegerDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Minutes, Seconds, StreamingContext}
import pdorobisz.dataprocessing.spark.data.{DataDeserializer, EventData}

object Main {

  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setAppName("EventProcessing").setMaster("local[4]")
    val ssc = new StreamingContext(sparkConf, Seconds(10))
//    ssc.checkpoint("checkpoint")

    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> s"${Configuration.kafkaServerHost}:${Configuration.kafkaServerPort}",
      "key.deserializer" -> classOf[IntegerDeserializer],
      "value.deserializer" -> classOf[DataDeserializer],
      "group.id" -> "data-processing",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val topics = Array("info")
    val stream = KafkaUtils.createDirectStream[String, EventData](
      ssc,
      PreferConsistent,
      Subscribe[String, EventData](topics, kafkaParams)
    )

    stream
      .map(record => record.value.content)
      .flatMap(_.split(" "))
      .map(w => (w, 1))
      .reduceByKey(_ + _)
//      .reduceByKeyAndWindow(_ + _, _ - _, Minutes(1), Seconds(30))
      .print()

    ssc.start()
    ssc.awaitTermination()
  }
}
