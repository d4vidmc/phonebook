pipeline { 
    agent {
        dockerfile true
        }
    stages {
        stage('Public directory') { 
            steps {
                sh 'cd /var/www/html'
                sh 'ls -la'
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
        stage('Sonar qube') { 
            steps {
                sh 'cd /var/www/html/sonar-scanner-3.3.0.1492-linux/bin'
                sh 'ls -la'
                sh 'sonar-scanner \
                  -Dsonar.projectKey=d4vidmc_phonebook \
                  -Dsonar.organization=d4vidmc-github \
                  -Dsonar.sources=. \
                  -Dsonar.host.url=https://sonarcloud.io \
                  -Dsonar.login=a3c3fde848a83c4d38fd6976d66aba08efd8ff51'
            }
        }
    }

}
