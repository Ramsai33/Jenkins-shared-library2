def call() {

    pipeline {
        agent any

        stages {
            stage('Build/compile') {
                steps {
                    script {
                        echo "compile"
                    }
                }
            }
        }
        stages {
            stage('Unittest') {
                steps {
                    script {
                        echo "unit test"
                    }
                }
            }
        }
        stages {
            stage('Quality check') {
                steps {
                    script {
                        echo "Quality check"
                    }
                }
            }
        }
        stages {
            stage('Upload artifacts') {
                steps {
                    script {
                        echo "Upload artifacts"
                    }
                }
            }
        }
    }
}