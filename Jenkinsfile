pipeline {
  agent any
  stages {
    stage('ensureMaven') {
      steps {
        parallel(
          "ensureMaven": {
            tool 'Maven3.3'
            
          },
          "cleanWorkspace": {
            sh 'rm -rf *'
            
          }
        )
      }
    }
  }
}