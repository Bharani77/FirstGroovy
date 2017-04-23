pipeline {
  agent any
  stages {
    stage('ensureMaven') {
      steps {
        parallel(
          "ensureMaven": {
            tool(name: 'JDK', type: '1.7')
            
          },
          "cleanWorkspace": {
            sh 'rm -rf .*'
            
          }
        )
      }
    }
  }
}