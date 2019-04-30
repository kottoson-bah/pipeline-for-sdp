void call() {
  stage("test live"){
    echo "Hello from test_live.groovy"
    // deploy Jenkins on target Openshift cluster
      // if testing sdp-helm-chart, unstash the workspace
      echo "This is the ${scm.getUserRemoteConfigs()[0].toString()} repository"
      // else clone sdp-helm-chart and do NOT unstash the workspace
      // login to target Openshift cluster
      // install SDP

    // if testing jenkins-templating-engine
      // optionally unstash the workspace
      // install the build artifact
      // wait for Jenkins to restart

    // create Jenkins job(s)
      // clone repo w/ Job DSL files
      // if testing sdp-libraries, update the files to use the current branch

    // execute Jenkins jobs
      // or, if they just start automatically, wait for them to finish
      // when finished, record results

    // display Jenkins job results
      // fail if over a certain threshold
      // display failed job
      // fetch output of failed job and archive the logs

  }
}
