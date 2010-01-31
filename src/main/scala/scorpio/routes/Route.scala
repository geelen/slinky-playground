package scorpio.routes

import scorpio.controllers.Controller

case class Route(prefix: String, controller: Controller) {
  def matches(s: String) = s.matches("""^/*""" + s)
}