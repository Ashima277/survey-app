pipeline {
    agent any

    environment {
        PROJECT_ID ='survey-application-456213'
        REGION = 'us-east1'
        REPO_NAME = 'survey-repo'
        IMAGE = "$REGION-docker.pkg.dev/$PROJECT_ID/$REPO_NAME/springboot-app:latest"
        CLUSTER = 'survey-app-cluster'
        CLUSTER_ZONE = 'us-east1-b'
        DEPLOYMENT_NAME = 'springboot-app'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Ashima277/survey-app'
            }
        }
    stage('Build Backend') {
            steps {
                script {
                    sh 'mvn clean install'
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE .'
            }
        }

        stage('Push Docker Image to Artifact Registry') {
            steps {
                withCredentials([file(credentialsId: 'gcp-service-account', variable: 'GOOGLE_APPLICATION_CREDENTIALS')]) {
                    sh '''
                        gcloud auth activate-service-account --key-file=$GOOGLE_APPLICATION_CREDENTIALS
                        gcloud auth configure-docker $REGION-docker.pkg.dev --quiet
                        docker push $IMAGE
                    '''
                }
            }
        }

        stage('Deploy to GKE') {
            steps {
                withCredentials([file(credentialsId: 'gcp-service-account', variable: 'GOOGLE_APPLICATION_CREDENTIALS')]) {
                    sh '''
                        gcloud auth activate-service-account --key-file=$GOOGLE_APPLICATION_CREDENTIALS
                        gcloud container clusters get-credentials $CLUSTER --zone $CLUSTER_ZONE --project $PROJECT_ID
                        kubectl apply -f deployment.yaml
                        kubectl apply -f service.yaml
                    '''
                }
            }
        }
    }
}
