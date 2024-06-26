def call() {
    node('workstation') {
        if (!env.SONAR_EXTRA_OPTS) {
            env.SONAR_EXTRA_OPTS = " "
        }
        if (!env.TAG_NAME) {
            env.PUSH_CODE = "false"
        } else {
            env.PUSH_CODE = "true"
        }

        stage('Clean workspace') {
            cleanWs()
            git branch: 'main', url: "https://github.com/Ramsai33/${component}.git"
            sh 'env'
        }
        stage('Build') {
            common.compile()
        }
        stage('UnitTest') {
            common.unittest()
        }
        stage('QualityCheck') {
            SONAR_USER = '$(aws ssm get-parameters --region us-east-1 --names sonarqube.user  --with-decryption --query Parameters[0].Value | sed \'s/"//g\')'
            SONAR_PASS = sh(script: 'aws ssm get-parameters --region us-east-1 --names sonarqube.pass  --with-decryption --query Parameters[0].Value | sed \'s/"//g\'', returnStdout: true).trim()
            wrap([$class: 'MaskPasswordsBuildWrapper', varPasswordPairs: [[password: "${SONAR_PASS}", var: 'SECRET']]]) {
//                sh "sonar-scanner -Dsonar.host.url=http://172.31.31.13:9000 -Dsonar.login=$SONAR_USER -Dsonar.password=$SONAR_PASS -Dsonar.projectKey=${component} -Dsonar.qualitygate.wait=true ${SONAR_EXTRA_OPTS}"
                echo "Hello SONA"
            }
        }
        if (env.PUSH_CODE == "true") {
            stage('Upload Artifacts') {
                echo "artifacts"
            }

        }

    }
}