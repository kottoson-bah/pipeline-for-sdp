def call() {
  node {
    unstash "workspace"
    // docker.image(config.image.name).inside("-m 4000m"){
    //   sh "./gradlew --no-daemon clean test "
    // }

    // sh "docker build -f unit_test.Dockerfile -t pipeline-unit-testing ."
    // sh "docker run --rm -t -v \$(pwd):/app -w /app pipeline-unit-testing gradle --no-daemon test"

    sh "./gradlew test"
    sh "ls -al"

  }
    //archiveArtifacts artifacts: 'target/reports/tests/test/**'
}
