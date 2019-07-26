def call() {
  stage("Unit Test"){
    node{
      unstash "workspace"
      sh "make test docker && touch \$(ls target/test-results/test/*.xml)"
      archiveArtifacts "target/reports/tests/test/**"
      junit "target/test-results/test/*.xml"
      stash "workspace"
    }
  }
}
