package pdorobisz.dataprocessing.spark

import com.typesafe.config.ConfigFactory

object Configuration {
  private val config = ConfigFactory.load()

  val kafkaServerHost: String = config.getString("dataprocessing.kafka.server.host")
  val kafkaServerPort: Int = config.getInt("dataprocessing.kafka.server.port")
}