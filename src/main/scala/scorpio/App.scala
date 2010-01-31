package scorpio

import _root_.scalaz.http.request.Request
import _root_.scalaz.http.servlet.{HttpServletRequest, HttpServlet}
import scalaz.http.response.{NotFound, Response}
import stages._
import scapps.{R, ServletApplicationServlet}

abstract class App extends ServletApplicationServlet[Stream, Stream] {
  def stages: Seq[Stage]

  // nkpart - also here
  val finalStage: Stage = FourOhFour.toStage

  def apply(implicit servlet: HttpServlet, servletRequest: HttpServletRequest, request: Request[Stream]) = {
    R.service(request, servletRequest.session) {
      Iterable.concat(stages, Traversable(finalStage)).
              foldLeft[Either[Request[Stream], Response[Stream]]](Left(request))({
        case (Left(request), stage) => stage.apply(request)
        case (response, _) => response
      }).right.get
    }
  }
}