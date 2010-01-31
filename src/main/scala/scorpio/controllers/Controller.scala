package scorpio.controllers

import collection.mutable.Map
import xml.NodeSeq
import scalaz.http.response.Response._
import scalaz.http.ContentType
import scalaz.http.response._
import scalaz.http.Slinky._

trait Controller {
  val actions: Map[Action, NodeSeq] = Map()

  def go(a: Action) = {
    actions.get(a).map(x => {
      //this is what I want
      OK(ContentType, "text/html") << transitional << x
    })
  }
}