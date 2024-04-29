def compile() {
    if (app_lang == "nodejs") {
        sh 'npm install'
    }
    if (app_lang == "maven") {
        sh "mvn package && cp target/${component}-1.0.jar ${component}.jar"
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
    if(app_lang == "nginx" || app_lang == "python") {
        sh "zip -r ${component}-${TAG_NAME}.zip * -x Jenkinsfile VERSION ${extra_file}"
    }

    if(app_lang == "nodejs") {
        sh "zip -r ${component}-${TAG_NAME}.zip node_modules server.js VERSION ${extra_file}"
    }

    if(app_lang == "maven") {
        sh "zip -r ${component}-${TAG_NAME}.zip ${component}.jar VERSION ${extra_file}"
    }
}