pipeline { 
    agent any 
    stages{
        stage('Build inside docker container') {
            agent {
                dockerfile true
                }
            stages {
                stage('Get missing dependencies') { 
                    steps {
                        sh 'cd /var/www/html/'
                        sh 'composer update'
                    }
                }
                stage('Unit test') { 
                    steps {
                        sh './vendor/bin/phpunit --log-junit results/phpunit/phpunit.xml --coverage-html results/phpunit/coverage --coverage-clover results/phpunit/coverage.xml -c phpunit.xml'
                    }
                }
                stage('Code quality inspection') { 
                    steps {
                        sh './sonar-scanner-3.3.0.1492-linux/bin/sonar-scanner \
                          -Dsonar.projectKey=d4vidmc_phonebook \
                          -Dsonar.organization=d4vidmc-github \
                          -Dsonar.sources=app/Http/Controllers \
                          -Dsonar.host.url=https://sonarcloud.io \
                          -Dsonar.login=a3c3fde848a83c4d38fd6976d66aba08efd8ff51'
                    }
                }
            }
        }
        stage('Deploy') { 
            steps {
                sh 'docker-compose up --build'
            }
        }
        stage('Post deploy tasks') { 
            agent { 
                docker { image 'phonebook-sonar_website' }
                }
            stages{
                stage('Necesary permission'){
                    steps {
                        sh 'chmod -R og=rwx /var/www/html/'
                    }
                }
            }
        }
    }
}
