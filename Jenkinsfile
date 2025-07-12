pipeline {
    agent any

    environment {
        IMAGE_NAME = 'springboot-app'
        IMAGE_TAG = "${BUILD_NUMBER}"
        HELM_RELEASE = 'springboot-app'
        K8S_NAMESPACE = 'default'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build App') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
            }
        }

        stage('Load to Minikube') {
            steps {
                sh "minikube image load ${IMAGE_NAME}:${IMAGE_TAG}"
            }
        }

        stage('Deploy to Kubernetes with Helm') {
            steps {
                sh """
                    helm upgrade --install ${HELM_RELEASE} ./helm-chart \
                      --set image.repository=${IMAGE_NAME} \
                      --set image.tag=${IMAGE_TAG} \
                      --set image.pullPolicy=Never \
                      --namespace ${K8S_NAMESPACE}
                """
            }
        }
    }
}
