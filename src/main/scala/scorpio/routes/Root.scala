package scorpio.routes

import scorpio.stages._
import collection.immutable.Stream
import scalaz.http.request.Request
import scorpio.controllers._

case class Root(c: Controller) extends MaybeHandler {
  def apply(request: Request[Stream]) = request.path.list.mkString match {
    case "" => c.go(INDEX)
    case "/" => c.go(INDEX)
    case _ => None
  }
}