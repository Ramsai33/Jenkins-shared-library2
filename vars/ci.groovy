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

            stage('Unittest') {
                steps {
                    script {
                        echo "unit test"
                    }
                }
            }

            stage('Quality check') {
                steps {
                    script {
                        echo "Quality check"
                    }
                }
            }


            stage('Upload artifacts') {
                steps {
                    script {
                        echo "Upload artifacts"
                    }
                }
            }

    }
}