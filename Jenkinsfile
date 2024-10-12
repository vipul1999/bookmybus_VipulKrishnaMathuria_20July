pipeline {
    agent any
    tools {
            maven 'my_maven' // Replace with the name you set in Global Tool Configuration
            docker 'my_docker'
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
        stage('Deploy') {
            steps {
                script {
                    // Build Docker image
                    sh '''
                    docker build -t tms-app .
                    docker rm -f tms-app-container || true
                    docker run -d --name tms-app-container -p 8081:8081 tms-app-container
                    '''
                }
            }
        }
    }
}
