package dao

import java.util.UUID

import com.mohiva.play.silhouette.api.LoginInfo
import javax.inject.Inject
import models.User
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.collection.mutable
import scala.concurrent.Future

/**
 * Give access to the user object using Slick
 */
class UserDAOImpl @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends UserDAO {

  /**
   * Finds a user by its login info.
   *
   * @param loginInfo The login info of the user to find.
   * @return The found user or None if no user for the given login info could be found.
   */
  def find(loginInfo: LoginInfo) = Future.successful(
    users.find { case (_, user) => user.loginInfo == loginInfo }.map(_._2))

  /**
   * Finds a user by its user ID.
   *
   * @param userID The ID of the user to find.
   * @return The found user or None if no user for the given ID could be found.
   */
  def find(userID: UUID) = Future.successful(users.get(userID))

  /**
   * Saves a user.
   *
   * @param user The user to save.
   * @return The saved user.
   */
  def save(user: User) = {
    users += (user.userID -> user)
    Future.successful(user)
  }
  val users: mutable.HashMap[UUID, User] = mutable.HashMap()
}
