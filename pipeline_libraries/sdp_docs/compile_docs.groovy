
def call() {
  stage("Compile Docs"){
    node{
      unstash "workspace"
      sh "make docs"
      archiveArtifacts "_build/html/**"
    }
  }
}
