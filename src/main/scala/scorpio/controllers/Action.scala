package scorpio.controllers

import xml.NodeSeq
import scala.collection.mutable.Map

trait Action {
  def apply(n: NodeSeq)(implicit actions: Map[Action,NodeSeq]) = actions += (this -> n)
}

case object INDEX extends Action
