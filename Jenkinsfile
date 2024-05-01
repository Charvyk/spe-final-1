pipeline {
    agent any
    environment {
        backend = 'charvykoshta/spe_backend' // Specify your backend Docker image name/tag
        frontend = 'charvykoshta/spe_frontend' // Specify your frontend Docker image name/tag
        database = 'charvykoshta/spe_database' // Specify the MySQL Docker image
        mysql = 'mysql:8'
        MYSQL_PORT = '3306'
        docker_image = ''
    }

    //added tool references for mac
    // tools { 
    //     maven 'mvn'
    //     ansible 'ansible'
    // }

    stages{
        stage('Stage 0: Pull MySQL Image') {
            steps {
                echo 'Pulling MySQL image from DockerHub'
                script {
                    docker.withRegistry('', 'DockerHubCred') { 
                        docker.image("${mysql}").pull()
                    }
                }
            }
        }

        stage('Stage 1: Pull GitHub Repository') {
            steps {
            // Get code from GitHub Repository
            sh 'rm -rf spe-final-1'
            sh 'git clone https://github.com/Charvyk/spe-final-1.git'
            // git branch: 'main', url: 'https://github.com/Charvyk/spe-final-1.git'
            
            }
        }

        stage('Stage 2: Build Database Docker Image') {
            steps {
                echo 'Building backend Docker image'
                dir('spe-final-1/Database')
                {
                    script{
                        containers = sh(returnStdout: true, script: 'docker ps -aq').replaceAll("\n", " ")
                        if (containers) {
                            sh "docker stop ${containers}"
                            sh "docker rm ${containers}"
                        }
                        sh "docker build -t $database ."
                        sh "docker run -dp 127.0.0.1:3306:3306 $database"
                    }
                        
                }
            }
        }

        stage('stage 3: Build maven project') {
            steps {
                echo 'Build maven project'
                dir('spe-final-1/backend') 
                {
                    sleep(time: 20, unit: 'SECONDS')
                    sh 'mvn clean install'
                }
            }
        }
 
        stage('Stage 4: Build backend Docker Image') {
            steps {
                echo 'Building backend Docker image'
                dir('spe-final-1/backend')
                {
                    sh "docker build -t $backend ."
                    sh "docker run -dp 127.0.0.1:8070:8070 $backend"
                }
            }
        }

        stage('Stage 6: Build frontend Docker image') {
            steps {
                echo 'Building frontend Docker image'
                dir('spe-final-1/frontend') {
                    echo 'Changing to frontend directory'
                    sh "docker build -t $frontend ."
                    sh "docker run -dp 127.0.0.1:3000:3000 $frontend"
                }
            }
        }

        stage('Stage 7: Push Database Docker image to DockerHub') {
            steps {
                echo 'Pushing backend Docker image to DockerHub'
                script {
                    docker.withRegistry('', 'DockerHubCred') {
                        sh 'docker push $database'
                    }
                }
            }
        }

        stage('Stage 8: Push backend Docker image to DockerHub') {
            steps {
                echo 'Pushing backend Docker image to DockerHub'
                script {
                    docker.withRegistry('', 'DockerHubCred') {
                        sh 'docker push $backend'
                    }
                }
            }
        }

        stage('Stage 9: Push frontend Docker image to DockerHub') {
            steps {
                echo 'Pushing backend Docker image to DockerHub'
                script {
                    docker.withRegistry('', 'DockerHubCred') {
                        sh 'docker push $frontend'
                    }
                }
            }
        }
        
        stage('Stage 10: Clean docker images') {
            steps {
                script{
                        containers = sh(returnStdout: true, script: 'docker ps -aq').replaceAll("\n", " ")
                        if (containers) {
                            sh "docker stop ${containers}"
                            // sh "docker rm ${containers}"
                        }
                    }
            }
        }

        stage('Stage 11: Ansible Deployment') {
            steps {
                ansiblePlaybook(
                    becomeUser: null,
                    colorized: true,
                    credentialsId: 'localhost',
                    disableHostKeyChecking: true,
                    installation: 'Ansible',
                    inventory: 'spe-final-1/Deployment/inventory',
                    playbook: 'spe-final-1/Deployment/deploy.yml',
                    sudoUser: null
                )
            }
        }
    }
}
