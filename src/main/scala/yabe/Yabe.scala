package yabe

import scorpio.{GaeCheckLoggedIn, App}

class Yabe extends App {
  def stages = Seq(GaeCheckLoggedIn)
}