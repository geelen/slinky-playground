package yabe.controllers

import scorpio.controllers.{INDEX, Controller}

object MainController extends Controller {
  INDEX {
    <html>
      <body>
        <p>
          Sup
        </p>
      </body>
    </html>
  }
}