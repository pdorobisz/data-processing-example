package pdorobisz.dataprocessing.datainput.api.model

import pdorobisz.dataprocessing.datainput.api.model.EventLevel.EventLevel

case class EventData(applicationName: String, level: EventLevel, content: String)

object EventLevel {

  sealed trait EventLevel

  case object Info extends EventLevel

  case object Warning extends EventLevel

  case object Error extends EventLevel

}
