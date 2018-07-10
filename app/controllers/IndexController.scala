package controllers

import controllers.Assets.Asset
import javax.inject._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class IndexController @Inject()(assets: Assets, cc: ControllerComponents) extends AbstractController(cc) {


  def playApp = Action {

    val html = views.html.index("Your Play App is ready.")

    Ok(html)

  }

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index: Action[AnyContent] = {

    assets.at("/public", "index.html")

  }


  def serveRootJsFiles(file: String): Action[AnyContent] = {

    assets.versioned("/public", file+".js")

  }
}
