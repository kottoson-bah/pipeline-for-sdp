libraries {
  merge = true
  pipeline_testing {
    ocp {
      url = "master.ocp-dev.microcaas.net:8443"
      credential_id = "oc-admin"
    }
    sdp_installation_name = "test-live"
  }
}

keywords {
  merge = true
}

stages {
  merge = true
}

steps {
  merge = true
}

template_methods{
  unit_test
  static_code_analysis
  build
  scan_container_image
  penetration_test
  accessibility_compliance_test
  performance_test
  functional_test
  test_live
  validate_docs
  publish
  deploy
  deploy_docs
}
