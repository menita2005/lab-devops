pipeline {
    agent any

    environment {
        REPO = 'calculadora-ci'
        // Usamos host.docker.internal para que Jenkins pueda acceder al Docker del host
        DOCKER_HOST = 'unix:///var/run/docker.sock'
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
        script {
            // Detener y eliminar contenedor anterior si existe
            sh "docker rm -f calculadora-app || true"
            
            // Desplegar nuevo contenedor
            sh """
                docker run -d \
                    --name calculadora-app \
                    -p 8081:8080 \
                    ${REPO}:${BUILD_NUMBER}
            """
            
            // Verificar que el contenedor está corriendo
            sh "docker ps | grep calculadora-app"
        }
    }
}

        stage('Health Check') {
    steps {
        script {
            // Esperar que la aplicación inicie
            sh "sleep 5"
            
            // Verificar el endpoint /salud
            sh """
                curl -f http://localhost:8081/salud || \
                curl -f http://host.docker.internal:8081/salud
            """
            
            // Mostrar logs del contenedor para verificar
            sh "docker logs calculadora-app"
        }
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