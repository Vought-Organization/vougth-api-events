package libs.functions

class Functions {
    String cloneAndCheckout(String REPO, String REPO_NAME, String BRANCH){
        def PWD
        sh "git clone -b ${BRANCH} ${REPO} && cd ${REPO_NAME} && git checkout ${BRANCH}"
        PWD = sh (script:  "cd ${REPO_NAME} && pwd", returnStdout: true).trim()
        return PWD
    }

    String getShortCommitHash(String REPO_PWD) {
        def shortCommit
        shortCommit = sh (script:  "cd ${REPO_PWD} && git rev-parse --short HEAD", returnStdout: true).trim()
        return shortCommit
    }
}

return this