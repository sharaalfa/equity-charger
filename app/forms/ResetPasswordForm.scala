package forms

import play.api.data.Forms._
import play.api.data._
/**
 * The Reset Password form
 */
object ResetPasswordForm {
  val form = Form(
    "password" -> nonEmptyText)
}
