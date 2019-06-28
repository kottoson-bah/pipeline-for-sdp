def call() {
  node {
    unstash "workspace"
    // if no gradle image set, use latest
    docker.image(config.image.name).inside("-m 3500m"){
      sh "gradle --no-daemon --stop clean test "
    }
  }
    //archiveArtifacts artifacts: 'target/reports/tests/test/**'
}
