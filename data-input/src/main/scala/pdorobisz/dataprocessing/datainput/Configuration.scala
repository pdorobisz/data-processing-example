package pdorobisz.dataprocessing.datainput

import com.typesafe.config.ConfigFactory

object Configuration {
  private val config = ConfigFactory.load()

  val httpPort: Int = config.getInt("dataprocessing.datainput.http.port")
  val kafkaServerHost: String = config.getString("dataprocessing.kafka.server.host")
  val kafkaServerPort: Int = config.getInt("dataprocessing.kafka.server.port")
}
