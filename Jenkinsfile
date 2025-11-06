pipeline {
    agent any // Puedes especificar un agente con Docker y Maven/Java preinstalados

    environment {
        DOCKER_REGISTRY_URL = 'http://dockerregistry.vinalssoler.com:5000'
        DOCKER_REGISTRY_GROUP = 'vs'
        DOCKER_CREDENTIALS_ID = 'docker'

        DEPLOY_SERVER = 'jenkins@dockerhost1.vinalssoler.com'
        SSH_CREDENTIALS_ID = 'dockerhost1'
        COMPOSE_FILE_PATH = 'docker-compose.swarm.yml'

        APP_INTERNAL_PORT = '8282'
    }

    stages {
        stage('Checkout') {
            steps {
                gitlog()
                echo 'Obteniendo código fuente...'
                checkout scm
            }
        }

        stage('Build Application & Image') {
            tools {
                jdk 'jdk21'
            }
            steps {
                sh './mvnw --version'
                sh 'java -version'
                script {
                    echo "Extrayendo artifactId y version del pom.xml..."
                    env.ARTIFACT_ID = sh(script: "./mvnw help:evaluate -Dexpression=project.artifactId -q -DforceStdout", returnStdout: true).trim()
                    env.PROJECT_VERSION = sh(script: "./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout", returnStdout: true).trim()

                    if (env.ARTIFACT_ID.isEmpty()) {
                        error("No se pudo obtener artifactId del pom.xml...")
                    }
                    if (env.PROJECT_VERSION.isEmpty()) {
                        error("No se pudo obtener project.version del pom.xml.")
                    }

                    echo "artifactId (nombre de imagen): ${env.ARTIFACT_ID}"
                    echo "version (tag de imagen): ${env.PROJECT_VERSION}"

                    def registryHostPortForImageName = env.DOCKER_REGISTRY_URL.replace("http://", "").replace("https://", "")
                    // registryHostPortForImageName será 'dockerregistry.vinalssoler.com:5000'

                    // Nombre completo de la imagen para referencia y despliegue
                    env.FULL_IMAGE_NAME_WITH_TAG = "${registryHostPortForImageName}/${env.DOCKER_REGISTRY_GROUP}/${env.ARTIFACT_ID}:${env.PROJECT_VERSION}"

                    // Nombre del contenedor para la etapa de despliegue
                    env.CONTAINER_NAME_DYNAMIC = "${env.ARTIFACT_ID}"

                    echo "Construyendo y subiendo la imagen Quarkus: ${env.FULL_IMAGE_NAME_WITH_TAG}"
                    echo "   Registry (para Quarkus): ${registryHostPortForImageName}"
                    echo "   Group (para Quarkus): ${env.DOCKER_REGISTRY_GROUP}"
                    echo "   Name (para Quarkus): ${env.ARTIFACT_ID}"
                    echo "   Tag (para Quarkus): ${env.PROJECT_VERSION}"

                    // El comando de build de Quarkus debe recibir el nombre SIN http://
                    echo "Construyendo la aplicación Quarkus y la imagen Docker: ${env.FULL_IMAGE_NAME_WITH_TAG}"
                    sh """
                        ./mvnw clean package -Pnative \\
                            -Dquarkus.container-image.build=true \\
                            -Dquarkus.native.container-build=true \\
                            -Dquarkus.container-image.push=true \\
                            -Dquarkus.container-image.insecure=true \\
                            -Dquarkus.container-image.registry=${registryHostPortForImageName} \\
                            -Dquarkus.container-image.group=${env.DOCKER_REGISTRY_GROUP} \\
                            -Dquarkus.container-image.name=${env.ARTIFACT_ID} \\
                            -Dquarkus.container-image.tag=${env.PROJECT_VERSION}
                    """

                    echo "Build de Maven/Quarkus completado. Listando imágenes Docker locales..."
                    sh 'docker images'
                }
            }
        }

        stage('Deploy Stack to Swarm') {
            steps {
                script {
                    // Define a temporary path on the remote server
                    def remoteTempDir = "/tmp/jenkins-deploy-${env.BUILD_NUMBER}-${env.ARTIFACT_ID}"
                    def remoteComposePath = "${remoteTempDir}/${env.COMPOSE_FILE_PATH}"

                    echo "Desplegando stack '${env.ARTIFACT_ID}' en Swarm Manager ${env.DEPLOY_SERVER}..."
                    echo "Usando imagen: ${env.FULL_IMAGE_NAME_WITH_TAG}"

                    // Use sshagent for secure connection and command execution
                    sshagent(credentials: [env.SSH_CREDENTIALS_ID]) {
                        try {
                            // 1. Create temporary directory on remote server
                            sh "ssh -o StrictHostKeyChecking=no ${env.DEPLOY_SERVER} 'mkdir -p ${remoteTempDir}'"

                            // 2. Copy the compose file from Jenkins workspace to remote temp dir
                            sh "scp -o StrictHostKeyChecking=no ${env.COMPOSE_FILE_PATH} ${env.DEPLOY_SERVER}:${remoteTempDir}/"

                            // 3. Replace placeholder tag in the remote compose file
                            sh """
                                ssh -o StrictHostKeyChecking=no ${env.DEPLOY_SERVER} << EOF
                                    echo 'Actualizando tag de imagen en el archivo compose remoto...'
                                    # Use a different delimiter like '#' for sed if paths/tags contain '/'
                                    sed -i 's#__IMAGE_TAG__#${env.PROJECT_VERSION}#g' ${remoteComposePath}
                                    sed -i 's#__ARTIFACT_ID__#${env.ARTIFACT_ID}#g' ${remoteComposePath}
                                    sed -i 's#__APP_PORT__#${env.APP_INTERNAL_PORT}#g' ${remoteComposePath}
                                    echo 'Archivo compose remoto actualizado:'
                                    cat ${remoteComposePath}
EOF
                            """

                            // 4. Deploy the stack using the modified remote compose file
                            // --with-registry-auth sends login credentials from the agent running this Jenkins job
                            sh """
                                ssh -o StrictHostKeyChecking=no ${env.DEPLOY_SERVER} << EOF
                                    echo 'Desplegando/Actualizando el stack ${env.ARTIFACT_ID}...'
                                    docker stack deploy -c ${remoteComposePath} --with-registry-auth ${env.ARTIFACT_ID}
                                    echo 'Esperando un momento para que el servicio se estabilice...'
                                    sleep 10
                                    echo 'Estado actual del stack:'
                                    docker stack services ${env.ARTIFACT_ID}
EOF
                            """
                            echo "Despliegue del stack ${env.ARTIFACT_ID} iniciado."

                        } finally {
                            // 5. Clean up the temporary directory on the remote server
                            echo "Limpiando directorio temporal en ${env.DEPLOY_SERVER}..."
                            sh "ssh -o StrictHostKeyChecking=no ${env.DEPLOY_SERVER} 'rm -rf ${remoteTempDir}'"
                        }
                    } // End sshagent
                } // End script
            } // End steps
        } // End stage Deploy
    }

    post {
        always {
            echo 'Pipeline finalizado.'
            // Aquí puedes añadir pasos de limpieza adicionales si es necesario
        }
        success {
            echo 'Pipeline ejecutado con éxito!'
            slackSend channel: '#jenkins', color: '#00FF00', message: ":thumbsup: Deployment - Projecte: ${env.JOB_NAME} Usuari: ${GIT_USER} Build Number: ${env.BUILD_NUMBER} - ${GIT_COMMIT}"
        }
        failure {
            echo 'Pipeline fallido!'
            slackSend channel: '#jenkins', color: '#FF0000', message: ":thumbsdown: Deployment - Projecte: ${env.JOB_NAME} Usuari: ${GIT_USER} Build Number: ${env.BUILD_NUMBER} - ${GIT_COMMIT}"
        }
    }
}
def gitlog() {
    sh('git log -1 --pretty=%B > commit-log.txt')
    GIT_COMMIT=readFile('commit-log.txt')
    sh('git log --format="%ae" | head -1 > commit-author.txt')
  	GIT_USER=readFile('commit-author.txt').trim()
}