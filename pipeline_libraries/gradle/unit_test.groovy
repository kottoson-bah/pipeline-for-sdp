def call() {
  stage("Unit Test"){
    node {
      unstash "workspace"
      
      def properties_file = new File('gradle.properties')
      properties_file << 'org.gradle.jvmargs=-Xmx2g -XX:MaxMetaspaceSize=512m -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8'
      
      docker.image(config.image.name).inside{
        sh "gradle clean test"
        //input "proceed when ready"
        archiveArtifacts artifacts: 'target/reports/tests/test/**'
      }

      // sh "docker build -f unit_test.Dockerfile -t pipeline-unit-testing ."
      // sh "docker run --rm -t -v \$(pwd):/app -w /app pipeline-unit-testing gradle --no-daemon test"

      // sh "./gradlew test"
      // sh "ls -al"
    }
  }
}
