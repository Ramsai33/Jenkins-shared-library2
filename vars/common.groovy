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