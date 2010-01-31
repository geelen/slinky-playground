package scorpio.stages

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

trait RequestMangler extends Function[Request[Stream], Request[Stream]]

trait Terminator extends Function[Request[Stream], Response[Stream]] {
  implicit def toStage = new Stage {
    def apply(request: Request[Stream]) = Right(Terminator.this.apply(request))
  }
}

trait MaybeHandler extends Function[Request[Stream], Option[Response[Stream]]] {
  implicit def toStage = new Stage {
    def apply(request: Request[Stream]) = MaybeHandler.this.apply(request).toRight(request)
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