def call() {
  node {
    unstash "workspace"
    // if no gradle image set, use latest
    sh "docker build -f unit_test.Dockerfile -t pipeline-unit-testing ."
    sh "docker run --rm -t -v \$(pwd):/app -w /app --oom-kill-disable -m 3500m pipeline-unit-testing gradle --no-daemon test"
    sh "ls -al"
  }
    //archiveArtifacts artifacts: 'target/reports/tests/test/**'
}
