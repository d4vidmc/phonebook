pipeline { 
docker { image 'ubuntu:18.04' }
    stages {
        stage('Public directory') { 
            steps {
                sh 'cd /var/www/html'
            }
        }
        stage('Get source code') { 
            steps {
                git branch: 'develop',
                 url: 'https://github.com/d4vidmc/phonebook.git'
            }
        }
        stage('Get missing dependencies') { 
            steps {
                sh 'composer update'
            }
        }
        stage('Unit test') { 
            steps {
                sh './vendor/bin/phpunit --log-junit results/phpunit/phpunit.xml --coverage-html results/phpunit/coverage --coverage-clover results/phpunit/coverage.xml -c phpunit.xml'
            }
        }
    }
   post {
        always {
            junit '**/results/phpunit/*.xml'
        }
    } 
}
