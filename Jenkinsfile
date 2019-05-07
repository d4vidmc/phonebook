pipeline { 
    //Specify agent master as default agent.
    agent {
        label 'master'
    }
    //Define a group of stages to run from docker.
    stages{
        stage('Build inside docker container') {
            //Initialize from Dockerfile.
            agent {
                dockerfile true
                }
            stages {
                //Update version of dependencies inside project.
                stage('Get missing dependencies') { 
                    steps {
                        sh 'cd /var/www/html/'
                        sh 'composer update'
                    }
                }
                //Run unit tests to see if there is no broken code.
                stage('Unit test') { 
                    steps {
                        sh './vendor/bin/phpunit --log-junit results/phpunit/phpunit.xml --coverage-html results/phpunit/coverage --coverage-clover results/phpunit/coverage.xml -c phpunit.xml'
                        publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: false, 
                            reportDir: 'results/phpunit/coverage', reportFiles: 'index.html', 
                            reportName: 'Code coverage - phonebook', reportTitles: ''])
                    }
                }
                //Execute code inspection to look for possible code smells, bugs on code.
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
        //Stage to deploy the project with a database.
        stage('Deploy with database') {
            stages {
                //Start the services with necesary user permissions.
                stage('Start services'){
                    steps {
                        sh 'sudo chmod o+rwx -R ./public ./storage ./bootstrap ./app ./tests ./vendor'
                        sh 'docker-compose up -d --build --remove-orphans'
                    }
                }
            }
        }
        //Execute the GUI automation test.
        stage('GUI Automation Test') { 
            steps {
            sh 'ls -lat'
            sh 'sudo chmod +x phonebook-selenium-tests/gradlew'
            sh 'mv phonebook-selenium-tests/environment.json.dist phonebook-selenium-tests/environment.json'
            sh './phonebook-selenium-tests/gradlew executeFeature'
            }
        }
    }
   // post task 
    post {
        //Stop docker compose
        always {
            echo 'docker-compose down'
        }
    }
}
