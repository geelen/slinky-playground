package scorpio.controllers

import xml.NodeSeq

trait Action {
  def apply: NodeSeq
}

case object INDEX extends Action
