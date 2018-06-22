package dao

import java.util.UUID

import models.AuthToken
import org.joda.time.DateTime

import scala.concurrent.Future

/**
 * Give access to the [[models.AuthToken]] object.
 */
trait AuthTokenDAO {
  /**
   *
   * @param id
   * @return
   */
  def find(id: UUID): Future[Option[AuthToken]]

  /**
   *
   * @param dateTime
   * @return
   */
  def findExpired(dateTime: DateTime): Future[Seq[AuthToken]]

  /**
   *
   * @param token
   * @return
   */
  def save(token: AuthToken): Future[AuthToken]

  /**
   *
   * @param id
   * @return
   */
  def remove(id: UUID): Future[Unit]

}
