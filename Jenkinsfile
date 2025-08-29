pipeline {
    agent {
        docker {
            image 'maven:3.9.8-eclipse-temurin-17'
            args '-v /root/.m2:/root/.m2'   // cache Maven deps
        }
    }

    environment {
        DOCKER_IMAGE = "my-spring-app"
        DOCKER_TAG   = "latest"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }

        stage('Test') {
            steps {
                sh "mvn test"
            }
        }

        stage('Dockerize') {
            agent {
                docker {
                    image 'docker:24.0-cli'
                    args '-v /var/run/docker.sock:/var/run/docker.sock'
                }
            }
            steps {
                sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} -f Dockerfile ."
            }
        }

        stage('Run Container (optional sanity check)') {
            steps {
                sh "docker run --rm -d -p 8080:8080 ${DOCKER_IMAGE}:${DOCKER_TAG}"
                // You could add a curl check here to test endpoint
            }
        }

        // Optional: push to DockerHub
        stage('Push to Registry') {
            when {
                branch 'main'
            }
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh """
                      echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                      docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKER_USER}/${DOCKER_IMAGE}:${DOCKER_TAG}
                      docker push ${DOCKER_USER}/${DOCKER_IMAGE}:${DOCKER_TAG}
                    """
                }
            }
        }
    }
}
