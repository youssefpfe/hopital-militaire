pipeline {
    agent any

    tools {
        maven "maven-3.8"
    }

    environment {

            // This can be nexus3 or nexus2
            NEXUS_VERSION = "nexus3"
            // This can be http or https
            NEXUS_PROTOCOL = "http"
            // Where your Nexus is running
            NEXUS_URL = "20.83.156.247"
            // Repository where we will upload the artifact
            NEXUS_REPOSITORY = "hopital-militaire"
            // Jenkins credential id to authenticate to Nexus OSS
            NEXUS_CREDENTIAL_ID = "nexus_credentials" // 3malt credentials f jenkins w 3aythomlhom houni for security reasons

   }


    stages {
        stage('Clone from remote repo') {
                    steps {
                      // Get some code from a GitHub repository
                      echo " clone github code "
                      git branch: 'main',
                      credentialsId: 'github_credentials',
                      url: 'https://github.com/youssefpfe/hopital-militaire.git'
                      echo " finish cloning "
                      }
         }

         stage('Test'){
                     steps {

                         // Maven Test .
                         sh "mvn test"

                     }
         }
        stage('Build'){
            steps {

                // Maven build .
                sh "mvn clean install -Dmaven.test.skip=true"

            }
        }

         stage('Static Code Analysis'){
                    steps {
                        script {

                             withSonarQubeEnv(credentialsId: 'sonarqube_credentials') {
                             sh 'mvn clean package sonar:sonar'
                             }
                        }

                    }
          }

          stage('Quality gate status'){
                              steps {
                                  script {

                                     waitForQualityGate abortPipeline: false, credentialsId: 'sonarqube_credentials'
                                  }

                              }
                    }
         stage("Publish to Nexus") {

                     steps {
                         script {
                             // Read POM xml file using 'readMavenPom' step , this step 'readMavenPom' is included in: https://plugins.jenkins.io/pipeline-utility-steps
                             def pom = readMavenPom file: 'pom.xml';
                             writeMavenPom model: pom;
                             // Find built artifact under target folder
                             filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                             // Print some info from the artifact found
                             echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                             // Extract the path from the File found
                             artifactPath = filesByGlob[0].path;
                             // Assign to a boolean response verifying If the artifact name exists
                             artifactExists = fileExists artifactPath;

                             if(artifactExists) {
                                 echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";

                                 nexusArtifactUploader(
                                     nexusVersion: NEXUS_VERSION,
                                     protocol: NEXUS_PROTOCOL,
                                     nexusUrl: NEXUS_URL,
                                     groupId: pom.groupId,
                                     version: pom.version,
                                     repository: NEXUS_REPOSITORY,
                                     credentialsId: NEXUS_CREDENTIAL_ID,
                                     artifacts: [
                                         // Artifact generated such as .jar, .ear and .war files.
                                         [artifactId: pom.artifactId,
                                         classifier: '',
                                         file: artifactPath,
                                         type: pom.packaging],

                                         // Lets upload the pom.xml file for additional information for Transitive dependencies
                                         [artifactId: pom.artifactId,
                                         classifier: '',
                                         file: "pom.xml",
                                         type: "pom"]
                                     ]
                                 );

                             } else {
                                 error "*** File: \${artifactPath}, could not be found";
                             }
                         }
                     }
                 }

      stage('Build docker image '){
                   steps {

                       echo "Build a docker image to Backend  hopital-militaire "
                       sh ' docker build -t youssefpfe/pfeapps:hopital.$BUILD_ID . '
                       sh "docker images"
                       echo " docker image fineshed built "


                   }
               }

                stage('Push the image to DockerHub '){
                   steps {

                      withCredentials([string(credentialsId: 'dockerhub_cred', variable: 'dockerhub_cred')]) {
                         echo " login dockerhub "
                         sh ' docker login -u youssefpfe -p ${dockerhub_cred} '
                         echo " pushing to dockerhub"
                         sh ' docker push youssefpfe/pfeapps:hopital.$BUILD_ID '
                      }

                   }
               }

     /*   stage('Deploy on docker compose '){
            steps {

                echo "Deploying"

                sh "docker-compose up -d"



            }

        }*/

    }
}