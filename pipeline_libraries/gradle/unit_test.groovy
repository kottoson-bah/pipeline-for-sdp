def call() {
  node {
    unstash "workspace"
    // if no gradle image set, use latest
    docker.image(config.image.name).inside{
      sh "gradle clean test"
    }
  }
    //archiveArtifacts artifacts: 'target/reports/tests/test/**'
}
