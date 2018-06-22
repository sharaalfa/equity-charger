package forms

import play.api.data._
import play.api.data.Forms._
/**
 * The Forgot Password form.
 */
object ForgotPasswordForm {
  val form = Form(
    "email" -> email)
}
