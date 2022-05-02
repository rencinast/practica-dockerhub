pipeline {
  enviromen {
      registry = "rencinas123/practica_docker_hub"
      registryCredential = 'docker-hub'
      dockerImage = ''
  }
  agent any
  stages {
      stage('Coning our GIT') {
          steps {
              git 'https://github.com/rencinast/pipeline.git'
          }
      }
  }
  stages {
      stage('Deplyu our image') {
          steps {
             script {
                docker.withRegistry('', registryuCredential)
                    dockerImage.push()
             }
          }
      }
  }
  stages {
      stage('Cleaning up') {
          steps {
              sh "docker rml $registry:$Build_NUMBER"
          }
      }
  }
}
