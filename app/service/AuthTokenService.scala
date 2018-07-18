package service

import java.util.UUID

import models.AuthToken

import scala.concurrent.Future
import scala.concurrent.duration._

trait AuthTokenService {
  /**
   * Creates a new auth token and saves it in the backing store.
   * @param userID The user ID for which the token should be created.
   * @return The saved auth token.
   */
  def create(userID: UUID): Future[AuthToken]

  /**
   * Validates a token ID.
   * @param id The token ID to validate.
   * @return The token if it's valid, None otherwise.
   */
  def validate(id: UUID): Future[Option[AuthToken]]

  /**
   * Cleans expired tokens
   * @return The list of deleted tokens.
   */
  def clean: Future[Seq[AuthToken]]
}
