package scorpio.stages

import scalaz.http.request.Request

package object stages {
  implicit def RequestManglerStage(rm: RequestMangler) = new Stage {
    def apply(request: Request[Stream]) = Left(rm.apply(request))
  }
}