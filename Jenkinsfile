pipeline {
    agent any

    stages {
        stage('Checkout') { // hook for commit
            steps {
                checkout scm
            }
        }

        stage('Build API') { // build the jar
            steps {
                dir('api') {
                    sh './mvnw clean package -DskipTests'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('frontreact') {
                    sh 'npm ci'
                    sh 'npm run build'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                sh 'docker build -t myproject-api ./api'
                sh 'docker build -t myproject-front ./frontreact'
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                sh 'docker compose down'
                sh 'docker compose up -d --build'
            }
        }
    }
}
