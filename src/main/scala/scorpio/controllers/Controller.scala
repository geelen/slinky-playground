package scorpio.controllers

import collection.mutable.Map
import xml.NodeSeq
import scalaz.http.response.Response._
import scalaz.http.response._
import scalaz.http.ContentType
import scalaz.http.Slinky._
import scalaz.http._
import scapps.R._

trait Controller {
  implicit val actions: Map[Action, NodeSeq] = Map()

  def go(a: Action) = actions.get(a).map(x => {
    // nkpart
    //implicit val body = Body.body(x.mkString.toStream
    OK(ContentType, "text/html") //<< transitional << x
  })
}