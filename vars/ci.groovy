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
                    SONAR_USER = '$(aws ssm get-parameters --region us-east-1 --names sonarqube.user  --with-decryption --query Parameters[0].Value | sed \'s/"//g\')'
                    SONAR_PASS = sh(script: 'aws ssm get-parameters --region us-east-1 --names sonarqube.pass  --with-decryption --query Parameters[0].Value | sed \'s/"//g\'', returnStdout: true).trim()
                    script {
                        sh "sonar-scanner -Dsonar.host.url=http://172.31.31.13:9000 -Dsonar.login=$SONAR_USER -Dsonar.password=$SONAR_PASS -Dsonar.projectKey=${component}"
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