package scorpio.routes

import collection.immutable.Stream
import scalaz.http.request.Request
import scalaz.http.response.Response
import scorpio.stages.MaybeHandler
import scorpio.controllers.INDEX

trait RoutingStage extends MaybeHandler {
  def routes: List[Route]

  def apply(v1: Request[Stream]) = go(v1, routes)

  private def go(v1: Request[Stream], rs: List[Route]): Option[Response[Stream]] = rs match {
    case Nil => None
    //todo: fix
    case r :: rest => if (r.matches(v1.path.list.mkString)) r.controller.go(INDEX) else go(v1, rest)
  }
}

