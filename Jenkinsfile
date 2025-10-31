pipeline {
    agent any

    tools {
        maven 'Maven_3.8' 
    }

    environment {
        JAVA_HOME = "C:\\Program Files\\Eclipse Adoptium\\jdk-21.0.8.9-hotspot"
    }

    stages {

        // Descargar código
        stage('Checkout') {
            steps {
                echo 'Clonando repositorio...'
                git branch: 'main', url: 'https://github.com/wpuello/yuutoorifinalassets.git'
            }
        }

        // Compilar proyecto
        stage('Build') {
            steps {
                echo 'Compilando aplicación...'
                bat 'mvn clean install -DskipTests=true'
            }
        }

        // Ejecutar pruebas unitarias
        stage('Test') {
            steps {
                echo '🧪 Ejecutando pruebas JUnit...'
                bat 'mvn test'
            }
            post {
                always {
                    echo '📊 Publicando resultados de pruebas...'
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        // Empaquetar aplicación
        stage('Package') {
            steps {
                echo 'Empaquetando aplicación Spring Boot...'
                bat 'mvn package -DskipTests=true'
            }
            post {
                success {
                    echo 'Build exitoso. Guardando artefacto...'
                    archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                }
            }
        }

        //Desplegar (manual o automatizado)
        stage('Deploy') {
            steps {
                echo 'Implementando aplicación (simulado)...'
                // Aquí podrías agregar:
                // bat 'scp target/tu-app.jar usuario@servidor:/ruta/destino'
                // o comandos AWS CLI más adelante
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline completado correctamente.'
        }
        failure {
            echo '❌ Error durante la ejecución del pipeline.'
        }
    }
}
