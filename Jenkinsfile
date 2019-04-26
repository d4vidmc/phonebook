pipeline { 
    agent any 
    stages {
        stage('Get source code') { 
            steps {
                git url: 'https://github.com/d4vidmc/phonebook.git'
            }
        }
        stage('Get missing dependencies') { 
            steps {
                sh 'sudo apt-get update && sudo apt-get install -yq --no-install-recommends \
                    apt-utils \
                    curl'
                sh 'sudo curl -sS https://getcomposer.org/installer | sudo php -- --install-dir=/usr/local/bin --filename=composer'
                sh 'composer update'
            }
        }
        stage('Unit test') { 
            steps {
                sh './vendor/bin/phpunit'
            }
        }
    }
}