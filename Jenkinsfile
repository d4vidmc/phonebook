pipeline { 
    agent any 
    stages {
        stage('Get source code') { 
            steps {
                sh 'composer require phpunit/phpunit'
                sh 'composer update'
            }
        }
        stage('Unit test') { 
            steps {
                sh './vendor/bin/phpunit'
            }
        }
        stage('Code analisys') { 
            steps {
                // 
            }
        }
    }
}