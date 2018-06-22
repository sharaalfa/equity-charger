package service.impl

import java.util.UUID

import com.mohiva.play.silhouette.api.util.Clock
import dao.AuthTokenDAO
import javax.inject.Inject
import models.AuthToken
import org.joda.time.DateTimeZone
import service.AuthTokenService

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Handles actions to auth tokens.
 * @param authTokenDAO The auth token DAO implementation.
 */
class AuthTokenServiceImpl @Inject() (authTokenDAO: AuthTokenDAO, clock: Clock) extends AuthTokenService {
  val expiry: FiniteDuration = 5.minutes

  /**
   * Creates a new auth token and saves it in the backing store.
   *
   * @param userID The user ID for which the token should be created.
   * @return The saved auth token.
   */
  override def create(userID: UUID) = {
    val token = AuthToken(UUID.randomUUID(), userID, clock.now.withZone(DateTimeZone.UTC).plusSeconds(expiry.toSeconds
      .toInt))
    authTokenDAO.save(token)
  }

  /**
   * Validates a token ID.
   *
   * @param id The token ID to validate.
   * @return The token if it's valid, None otherwise.
   */
  override def validate(id: UUID) = authTokenDAO.find(id)

  /**
   * Cleans expired tokens
   *
   * @return The list of deleted tokens.
   */
  override def clean = authTokenDAO.findExpired(clock.now.withZone(DateTimeZone.UTC)).flatMap { tokens =>
    Future.sequence(tokens.map { token =>
      authTokenDAO.remove(token.id).map(_ => token)
    })
  }
}
