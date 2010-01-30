package scorpio

import _root_.scalaz.http.request.Request
import _root_.scalaz.http.response.Response
import _root_.scalaz.http.servlet.{HttpServletRequest, HttpServlet}
import scapps.ServletApplicationServlet

abstract class App extends ServletApplicationServlet[Stream, Stream] {
  def stages: Seq[Stage]

  val finalStage: Stage = FourOhFour

  def apply(implicit servlet: HttpServlet, servletRequest: HttpServletRequest, request: Request[Stream]) = {
    val i = Left[Request[Stream], Response[Stream]](request)
    Iterable.concat(stages, Traversable(finalStage)).foldLeft[Either[Request[Stream], Response[Stream]]](i)({
      case (Left(request), stage) => stage.apply(request)
      case (response, _) => response
    }).right.get
  }
}