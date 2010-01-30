package scorpio

import _root_.scalaz.http.request.Request
import _root_.scalaz.http.servlet.{HttpServletRequest, HttpServlet}
import scapps.ServletApplicationServlet

abstract class App extends ServletApplicationServlet[Stream, Stream] {
  def stages: Seq[Stage]

  val finalStage = FourOhFour

  def apply(implicit servlet: HttpServlet, servletRequest: HttpServletRequest, request: Request[Stream]) = {
    null
  }
}