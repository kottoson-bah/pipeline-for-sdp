def call() {
  node {
    unstash "workspace"
    // if no gradle image set, use latest
    sh "docker build -f unit_test.Dockerfile -t pipeline-unit-testing ."
    sh "docker run --rm -t -v \$(shell pwd):/app -w /app pipeline-unit-testing gradle --no-daemon test"
    sh "ls -al"
  }
    //archiveArtifacts artifacts: 'target/reports/tests/test/**'
}
