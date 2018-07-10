import java.io.PrintWriter
import java.net.InetSocketAddress

import play.sbt.PlayRunHook
import sbt._

import scala.io.Source
import scala.sys.process.Process

/**
  * Hooks into Play's dev mode
  *
  * https://www.playframework.com/documentation/2.6.x/SBTCookbook
  *
  */
object NpmHooks {

  val install: String = "npm install"
  val serve: String = "npm run serve"
  val build: String = "npm run build"


  def apply(base: File): PlayRunHook = {

    //File Paths
    val frontendBase = base / "frontend"
    val packageJsonPath = frontendBase / "package.json"

    //Locally stored file hash of package.json
    val frontEndTarget = frontendBase / "target"
    val packageJsonHashPath = frontEndTarget / "package.json.hash"


    object NpmBuildHook extends PlayRunHook {

      var process: Option[Process] = None

      /**
        *  Run npm install if changes are detected in package.json
        */
      override def beforeStarted(): Unit = {

        val currPackageJsonHash = Source.fromFile(packageJsonPath).getLines().mkString.hashCode.toString
        val oldPackageJsonHash = getStoredPackageJsonHash.get

        if (!currPackageJsonHash.equals(oldPackageJsonHash)) {

          println("Found new/changed package.json. Invoking npm install...")

          Shell.invokeProcess(install, frontendBase)

          updateStoredPackageJsonHash(currPackageJsonHash)

        }

      }

      /**
        * > npm serve
        */
      override def afterStarted(addr: InetSocketAddress): Unit = {

        println("> npm serve");

        process = Option(

          Shell.process(serve, frontendBase)

        )
      }

      /**
        * cleanup
        */
      override def afterStopped(): Unit = {
        process.foreach(_.destroy())
        process = None
      }

      def getStoredPackageJsonHash(): Option[String] = {

        if ((packageJsonHashPath).exists()) {

          val hash = Source.fromFile(packageJsonHashPath).getLines().mkString

          Some(hash)

        }
        else {
          Some(null)
        }
      }

      def updateStoredPackageJsonHash(hash: String) = {


        val dir = frontEndTarget;

        if(!dir.exists){
          dir.mkdirs
        }

        val pw = new PrintWriter(packageJsonHashPath)

        try{

          pw.write(hash)

        }
        finally{

          pw.close()

        }



      }




    }

    NpmBuildHook
  }

}
