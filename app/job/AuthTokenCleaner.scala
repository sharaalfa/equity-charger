package job

import akka.actor.Actor
import com.mohiva.play.silhouette.api.util.Clock
import javax.inject.Inject
import job.AuthTokenCleaner.Clean
import utils.Logger
import service.AuthTokenService
import scala.concurrent.ExecutionContext.Implicits.global
class AuthTokenCleaner @Inject() (service: AuthTokenService, clock: Clock) extends Actor with Logger {
  override def receive: Receive = {
    case Clean =>
      val start = clock.now.getMillis
      val msg = new StringBuffer("\n")
      msg.append("================================\n")
      msg.append("Start to cleanup auth tokens\n")
      msg.append("================================\n")
      service.clean.map { deleted =>
        val seconds = (clock.now.getMillis - start) / 1000
        msg.append("Total of %s auth token(s) were deleted in %s seconds".format(deleted.length, seconds)).append("\n")
        msg.append("==============================\n")

        msg.append("==============================\n")
        logger.info(msg.toString)

      }.recover {
        case e =>
          msg.append("Couldn't cleanup auth tokens because of unexpected error\n")
          msg.append("==============================\n")
          logger.error(msg.toString, e)
      }
  }
}

/**
 * The companion object.
 */
object AuthTokenCleaner {
  case object Clean
}
