docker.image("gradle:latest").inside("--oom-kill-disable -m 3500m"){
  unstash "workspace"
  sh "./gradlew test"
}
static_code_analysis()
build()
test_live()
validate_docs()
publish()
deploy()
deploy_docs()
