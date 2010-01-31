package yabe

import scorpio.routes.RoutingStage
import scorpio.routes._
import controllers._
import scorpio.stages._
import scorpio.App

class Yabe extends App {
  //why??
  def stages = Seq(GaeCheckLoggedIn.toStage, Root(MainController).toStage)
}