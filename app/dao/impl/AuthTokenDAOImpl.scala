package dao.impl

import java.util.UUID

import dao.AuthTokenDAO
import models.AuthToken
import org.joda.time.DateTime

import scala.collection.mutable
import scala.concurrent.Future

/**
 * Give acces to the [[models.AuthToken]] object.
 */
class AuthTokenDAOImpl extends AuthTokenDAO {
  /**
   *
   * @param id
   * @return
   */
  override def find(id: UUID) = Future.successful(tokens.get(id))

  /**
   *
   * @param dateTime
   * @return
   */
  override def findExpired(dateTime: DateTime) = Future.successful {
    tokens.filter {
      case (_, token) => token.expiry.isBefore(dateTime)
    }.values.toSeq
  }

  /**
   *
   * @param token
   * @return
   */
  override def save(token: AuthToken) = {
    tokens += (token.id -> token)
    Future.successful(token)
  }

  /**
   *
   * @param id
   * @return
   */
  override def remove(id: UUID): Future[Unit] = {
    tokens -= id
    Future.successful(())
  }
  /**
   * The list of tokens
   */
  val tokens: mutable.HashMap[UUID, AuthToken] = mutable.HashMap()
}
