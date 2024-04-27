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
                        common.unittest()
                    }
                }
            }

            stage('Quality check') {
                steps {
                    script {
                        sh 'sonar-scanner -Dsonar.host.url=http://172.31.27.53:9000 -Dsonar.login=admin -Dsonar.password=admin123'
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