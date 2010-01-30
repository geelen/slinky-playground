package scorpio

import scalaz.Scalaz._
import scalaz.http.request._
import scalaz.http.request.Request._
import scalaz.http.servlet._
import scalaz.http.servlet.HttpServlet._
import scalaz.http.Slinky._
import scalaz.http.response._
import scalaz.http.response.Response._
import collection.immutable.Stream
import scalaz.Semigroup

trait Stage extends Function[Request[Stream], Either[Request[Stream], Response[Stream]]]

trait RequestMangler extends Function[Request[Stream], Request[Stream]] {
  implicit def RequestManglerStage(rm: RequestMangler) = new Stage {
    def apply(v1: Request[Stream]) = Left(rm.apply(v1))
  }
}

trait Terminator extends Function[Request[Stream], Response[Stream]] {
  implicit def TerminatorStage(t: Terminator) = new Stage {
    def apply(v1: Request[Stream]) = Right(t.apply(v1))
  }
}

trait MaybeHandler extends Function[Request[Stream], Option[Response[Stream]]] {
  implicit def MaybeStage(ms: MaybeHandler) = new Stage {
    def apply(v1: Request[Stream]) = ms.apply(v1).toRight(v1)
  }
}

object FourOhFour extends Terminator {
  def apply(v1: Request[Stream]) = {
    implicit val r = v1
    NotFound.xhtml
  }
}