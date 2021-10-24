pipeline {
    agent any

    parameters {
             gitParameter branchFilter: 'origin/(.*)', defaultValue: 'master', name: 'BRANCH', type: 'PT_BRANCH'
        }

    tools {
        maven "M3"
    }

    stages {
        stage('Build') {
            steps {
                git branch: "${params.BRANCH}", url: 'https://github.com/Mezzo941/SauceDemo.git'
                bat "mvn clean test"
            }

            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
                always {
                    script {
                        allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        results: [[path: 'target/allure-results']]
                        ])
                    }
                }
            }
        }
    }
}