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
    def apply(request: Request[Stream]) = Left(rm.apply(request))
  }
}

trait Terminator extends Function[Request[Stream], Response[Stream]] {
  implicit def TerminatorStage(t: Terminator) = new Stage {
    def apply(request: Request[Stream]) = Right(t.apply(request))
  }
}

trait MaybeHandler extends Function[Request[Stream], Option[Response[Stream]]] {
  implicit def MaybeStage(ms: MaybeHandler) = new Stage {
    def apply(request: Request[Stream]) = ms.apply(request).toRight(request)
  }
}

object FourOhFour extends Terminator {
  def apply(request: Request[Stream]) = {
    implicit val r = request
    NotFound.xhtml
  }
}

// can't get this going :(
//
//object ReturnStatus {
//  import Terminator._
//
//  def of(s: Status): Stage = new Terminator {
//    def apply(request: Request[Stream]) = {
//      implicit val r = request
//      s.xhtml
//    }
//  }
//}