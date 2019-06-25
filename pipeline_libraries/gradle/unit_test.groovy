def call() {
  node {
    unstash "workspace"
    // if no gradle image set, use latest
    def gradle_image = "latest"
    // else
    if (config.image) {
      gradle_image = config.image.name ?: {error("You must specify an image name")}
      def image_repo = gradle_image.split("/")[0]
      def image_cred = config.image.cred //can be null

      // log into repository
      // pull image
      if (image_cred) {
        withCredentials([usernamePassword(credentialsId: image_cred, passwordVariable: 'pass', usernameVariable: 'user')]) {
          sh "echo ${pass} | docker login -u ${user} --password-stdin ${image_repo} && docker pull ${gradle_image}"
        }
      }

    }

    // inside gradle image
    docker.image("${gradle_image}").inside("--oom-kill-disable -m 3500m"){
      // run unit tests
      sh "gradle test"
      // archive report
      archiveArtifacts artifacts: 'target/reports/tests/test/**'
    }
  }

}
