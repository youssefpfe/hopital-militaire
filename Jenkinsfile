pipeline {
    agent any

    tools {
        maven "maven-3.8"
    }

    stages {
        stage('Clone from remote repo') {
                    steps {
                      // Get some code from a GitHub repository
                      echo " clone github code "
                      git branch: 'main',
                      credentialsId: 'github_credentials',
                      url: 'https://github.com/youssefpfe/frontend.git'
                      echo " finish cloning "
                      }
         }

        stage('Build'){
            steps {

                // Maven build .
                sh "mvn clean install -Dmaven.test.skip=true"

            }
        }

       stage('Build docker image '){
                   steps {

                       echo "Build a docker image to angular application "
                       sh ' docker build -t youssefpfe/pfeapps:hopital.$BUILD_ID . '
                       sh "docker images"
                       echo " docker image fineshed built "


                   }
               }

                stage('Push the image to DockerHub '){
                   steps {

                      withCredentials([string(credentialsId: 'dockerhub_cred', variable: 'dockerhub')]) {
                         echo " login dockerhub "
                         sh ' docker login -u youssefpfe -p ${dockerhub} '
                         echo " pushing to dockerhub"
                         sh ' docker push youssefpfe/pfeapps:hopital.$BUILD_ID '
                      }

                   }
               }

        stage('Deploy on docker compose '){
            steps {

                echo "Deploying"

                sh "docker-compose up -d"



            }
        }

    }
}