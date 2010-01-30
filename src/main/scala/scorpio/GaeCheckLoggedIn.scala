package scorpio

import scalaz.http.request.Request
import scalaz.http.response.Response
import collection.immutable.Stream
import com.google.appengine.api.users.UserServiceFactory

object GaeCheckLoggedIn extends MaybeHandler {
  val userService = UserServiceFactory.getUserService

  def apply(request: Request[Stream]) = {
    implicit val r = request
    userService.getCurrentUser match {
      case null => Some(Response.redirects(userService.createLoginURL("/")))
      case _ => None
    }
  }
}