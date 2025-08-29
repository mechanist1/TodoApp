pipeline {
    agent any

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

        stage('Build & Test') {
            steps {
                // Run Maven in a temporary container, mounted to workspace
                sh 'docker run --rm -v $PWD:/app -w /app maven:3.9.8-eclipse-temurin-17 mvn clean package -DskipTests'
                sh 'docker run --rm -v $PWD:/app -w /app maven:3.9.8-eclipse-temurin-17 mvn test'
            }
        }

        stage('Dockerize') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE:$DOCKER_TAG -f Dockerfile .'
            }
        }

        stage('Run Container (optional)') {
            steps {
                sh 'docker run --rm -d -p 8080:8080 $DOCKER_IMAGE:$DOCKER_TAG'
            }
        }

        stage('Push to Registry') {
            when {
                branch 'main'
            }
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh '''
                      echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                      docker tag $DOCKER_IMAGE:$DOCKER_TAG $DOCKER_USER/$DOCKER_IMAGE:$DOCKER_TAG
                      docker push $DOCKER_USER/$DOCKER_IMAGE:$DOCKER_TAG
                    '''
                }
            }
        }
    }
}
