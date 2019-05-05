pipeline {
    agent {
        kubernetes {
            label "automation-tests-slave"
            containerTemplate {
                name "k8s-slave-jdk12-alpine"
                image "openjdk:12-jdk-alpine"
                ttyEnabled true
                command 'cat'
            }
        }
    }
    stages {
        stage("Prerequisites") {
            steps {
                container('k8s-slave-jdk12-alpine') {
                    sh 'apk update && apk add maven git'
                }
            }
        }
        stage("Default Build") {
            when {
                expression { MVN_STRING == null }
            }
            steps {
                container('k8s-slave-jdk12-alpine') {
                    sh "mvn test"
                }
            }
        }
        stage("Parameterized Build") {
            when {
                expression { MVN_STRING != null }
            }
            steps {
                container('k8s-slave-jdk12-alpine') {
                    sh ${params.MVN_STRING}
                }
            }
        }
    }
    post {
        always {
            script {
                allure([
                        includeProperties: false,
                        jdk              : '',
                        properties       : [],
                        reportBuildPolicy: 'ALWAYS',
                        results          : [[path: 'target/allure-results']]
                ])
            }
        }
    }
}
