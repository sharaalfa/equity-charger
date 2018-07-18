package forms

import play.api.data._
import play.api.data.Forms._
/**
 * The Change Password form.
 */
object ChangePasswordForm {
  val form = Form(tuple(
    "current-password" -> nonEmptyText,
    "new-password" -> nonEmptyText))
}
