pipeline { 
    agent {
        label 'master'
    }
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
                        publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: false, 
                            reportDir: 'results/phpunit/coverage', reportFiles: 'index.html', 
                            reportName: 'Code coverage - phonebook', reportTitles: ''])
                    }
                }
                stage('Code quality inspection') { 
                    steps {
                        sh 'sonar-scanner'
                    }
                }
            }
        }

        stage('Deploy with database') {
            stages {
                stage('Start services'){
                    steps {
                        sh 'chmod o+rwx -R ./public ./storage ./bootstrap ./app ./tests ./vendor'
                        sh 'docker-compose up -d --build --remove-orphans'
                    }
                }
            }
        }
        stage('GUI Test') { 
            steps {
            sh 'ls -lat'
            sh 'mkdir phonebook-selenium-tests'
            sh 'cd phonebook-selenium-tests/'
            git branch: 'develop',
                url: 'https://github.com/d4vidmc/phonebook-selenium-tests.git'
            sh 'sudo chmod +x gradlew'
            sh 'mv environment.json.dist environment.json'
            sh 'gradle clean executeFeature'
            sh './gradlew clean executeFeature'
            }
        }
    }
}
