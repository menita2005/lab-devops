pipeline {
    agent any

    environment {
        REPO = 'calculadora-ci'
        // Usamos host.docker.internal para que Jenkins pueda acceder al Docker del host
        DOCKER_HOST = 'tcp://host.docker.internal:2375'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -B clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn -B test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn -B package -DskipTests'
            }
        }

        // NUEVA ETAPA: Build Image
        stage('Build Image') {
            steps {
                script {
                    // Construir la imagen Docker con el número de build como tag
                    sh "docker build -t ${REPO}:${BUILD_NUMBER} ."
                    // También etiquetar como latest
                    sh "docker tag ${REPO}:${BUILD_NUMBER} ${REPO}:latest"
                }
            }
        }

        // Las siguientes etapas (Deploy y Health Check) las agregaremos después
        stage('Deploy') {
            steps {
                echo 'Deploy stage - to be implemented'
            }
        }

        stage('Health Check') {
            steps {
                echo 'Health Check stage - to be implemented'
            }
        }
    }

    post {
        always {
            echo 'Pipeline ejecutado'
        }
        success {
            echo 'Pipeline exitoso!'
        }
        failure {
            echo 'Pipeline falló!'
        }
    }
}