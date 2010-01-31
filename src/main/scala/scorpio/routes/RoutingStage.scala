package scorpio.routes

import scorpio.{MaybeHandler, Stage}
import collection.immutable.Stream
import scalaz.http.request.Request
import scalaz.http.response.Response

trait RoutingStage extends MaybeHandler {
  def routes: List[Route]

  def apply(v1: Request[Stream]) = go(v1, routes)

  private def go(v1: Request[Stream], rs: List[Route]): Option[Response[Stream]] = rs match {
    case Nil => None
    case r :: rest => if (r.matches(v1.path.mkString)) r.controller()
  }
}

