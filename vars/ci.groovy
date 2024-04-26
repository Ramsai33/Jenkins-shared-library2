def call() {

    pipeline {
        agent {
            label 'workstation'
        }

        stages {
            stage('Build/compile') {
                steps {
                    script {
                        common.compile()
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
}