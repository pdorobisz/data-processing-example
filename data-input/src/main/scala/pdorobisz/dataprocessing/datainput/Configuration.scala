package pdorobisz.dataprocessing.datainput

import com.typesafe.config.ConfigFactory

trait Configuration {
  private val config = ConfigFactory.load()

  val httpPort: Int = config.getInt("dataprocessing.datainput.http.port")
}
