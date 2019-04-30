void call() {
  stage("test live"){
    echo "Hello from test_live.groovy"

    def sdp_helm_chart_url = config.sdp_helm_chart.url ?: "https://github.com/boozallen/sdp-helm-chart.git"
    def sdp_helm_chart_cred = config.sdp_helm_chart.cred_id ?: scm.getUserRemoteConfigs()[0].credentialsId.toString()


    // deploy Jenkins on target Openshift cluster
    node() {
      // if testing sdp-helm-chart, unstash the workspace
      if (this.get_repo_name() == "sdp-helm-chart") {
        unstash "workspace"
        this.install_sdp()
      }
      // else clone sdp-helm-chart and do NOT unstash the workspace
      else {
        withGit url: sdp_helm_chart_url, cred: sdp_helm_chart_cred, {
          this.install_sdp()
        }
      }
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


String get_repo_name() {
  return scm.getUserRemoteConfigs()[0].getUrl().tokenize('/')[3].split("\\.")[0]
}

void install_sdp() {
  def ocp_url = config.ocp.url ?: { error "no value for config.openshift_cluster.url"}
  def oc_cluster_cred_id = config.ocp.credential_id ?: { error "no value for config.openshift_cluster.crednetial_id"}
  def sdp_installation_name = config.sdp_installation_name ?: "sdp"

  inside_sdp_image "openshift_helm", {
    withCredentials([usernamePassword(credentialsId: oc_cluster_cred_id, passwordVariable: 'token', usernameVariable: 'user')]) {
      this.oc_login(ocp_url, token)
      sh "./installer.sh -n ${sdp_installation_name} -a"
    }
  }
}

void oc_login(ocp_url, token){
  try {
    echo "Trying to log in via token..."
    sh "oc login --insecure-skip-tls-verify ${ocp_url} --token=${token} > /dev/null"
  } catch (any){
    echo "Trying to log in via user/pass..."
    sh "oc login --insecure-skip-tls-verify ${ocp_url} -u ${user} -p ${token} > /dev/null"
  }
}
