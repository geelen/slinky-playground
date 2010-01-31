package yabe

import scorpio.routes.RoutingStage
import scorpio.routes._
import controllers._
import scorpio.stages._
import scorpio.App

class Yabe extends App {
  // nkpart - I need a nice way for toStage to be implicitly called
  def stages = Seq(GaeCheckLoggedIn.toStage, Root(MainController).toStage)
}