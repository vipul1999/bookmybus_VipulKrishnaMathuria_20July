pipeline {
    agent any
    tools {
            maven 'my_maven' // Replace with the name you set in Global Tool Configuration
        }

    stages {
        stage('Build') {
            steps {
                checkout scm
                sh 'mvn clean install' // or your specific build command
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test' // or your specific test command
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package' // or your specific packaging command
            }
        }
    }
}
