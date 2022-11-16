pipeline {
    agent any

    stages {
        stage('Build & Test') {
            steps {
                echo 'Build & Test'
                sh "gradle build"
            }
        }
        stage('SonarQube Analysis') {
            steps {
                echo 'SonarQube Analysis'
                withSonarQubeEnv(credentialsId: "access_token_sq", installationName: "MySonar") {
                    sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar -Dsonar.target=sonar.java.binaries"
                }
            }
        }
        stage('Run') {
            steps {
                echo 'Running...'
                sh "gradle bootRun"            
            }
        }        
    }
}