import sbt._

import scala.sys.process.Process

//TODO: Implement windows impl
object Shell {

  def invokeProcess(script: String, dir: File): Int = {

    Process(script, dir)

  } !

  def process(script: String, dir: File): Process = {

    Process(script, dir).run

  }

}
