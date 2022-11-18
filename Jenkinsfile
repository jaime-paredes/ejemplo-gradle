pipeline {
    agent any

    tools {
        gradle "gradle"
        maven "maven"
    }

    stages {
        stage("Build & Test") {
            steps {
                echo "BUILD & TEST"
                sh "gradle build"
            }
        }
        stage("SonarQube Analysis") {
            steps {
                echo "SONARQUBE ANALYSIS"
                withSonarQubeEnv(credentialsId: "access_token_sq", installationName: "MySonar") {
                    sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar -Dsonar.target=sonar.java.binaries -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
                }
            }
        }
        stage("Run") {
            steps {
                echo "RUN"
                sh "gradle bootRun &"
                echo 'RUN OK'        
            }
        }
        // stage("Test") {
        //     steps {
        //         echo "TEST"
        //         sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'"            
        //     }
        // }
        stage("Nexus") {
            steps {
                echo "NEXUS"
                nexusPublisher nexusInstanceId: 'devops-usach-nexus', nexusRepositoryId: 'devops-usach-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: '/var/jenkins_home/workspace/ejemplo-gradle/build/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.2']]]
            }
        }
    }
}