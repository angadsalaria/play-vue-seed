/**
 * sbt invoked npm commands
 */

// Process Exit codes
val SUCCESS = 0
val ERROR = 1

PlayKeys.playRunHooks += baseDirectory.map(NpmHooks.apply).value

lazy val `frontend-prod-build` = TaskKey[Unit]("Prod build for front-end artifacts.")

`frontend-prod-build` := {

  val frontendRoot = baseDirectory.value / "frontend"

  println("> npm install")
  val nodeModuleInstallResult = Shell.invokeProcess(NpmHooks.install, frontendRoot)

  println("> npm build")
  val isBuildSuccess = if (nodeModuleInstallResult == SUCCESS) Shell.invokeProcess(NpmHooks.build, frontendRoot) else ERROR

  if (isBuildSuccess != SUCCESS) throw new Exception("Npm Build Process Failed!")

}

// Execute frontend prod build task prior to play dist execution.
dist := (dist dependsOn `frontend-prod-build`).value
