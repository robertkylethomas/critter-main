pipeline {
    agent any

    stages {
        stage('Checkout codebase') {
            steps {
                cleanWs()
                checkout(scm: [$class: 'GitSCM', branches: [[name:/'master']])
            }
        }
         stage('Build'){
        steps{
            sh 'mkdir lib'
            sh 'cd lib/ ; wget https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.7.0/junit-platform-console-standalone-1.7.0-all.jar'
            sh 'cd src ; javac -cp "../libjunit-platform-console-standalone-1.7.0-all.jar" CritterFunctionalTest.java'
        }
        }
    }
