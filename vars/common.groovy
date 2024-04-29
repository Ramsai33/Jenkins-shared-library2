def compile() {
    if (app_lang == "nodejs") {
        sh 'npm install'
    }
    if (app_lang == "maven") {
        sh 'mvn package'
    }

}

def unittest() {
    if (app_lang == "nodejs") {
//        sh 'npm test'
        echo 'npm test'
    }
    if (app_lang == "maven") {
//        sh 'mvn package'
      echo 'maven test'
    }

}

def artifactspush() {
    sh "echo ${TAG_NAME} > VERSION"
    if(app_lang == "nginx") {
        sh "zip -r ${component}-${TAG_NAME}.zip      VERSION"
    }
}