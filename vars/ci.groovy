def call() {
    node('workstation') {
        stage('Clean workspace') {
            cleanws()
            git branch: 'main', url: "https://github.com/Ramsai33/${component}.git"
            sh 'env'
        }

    }


}