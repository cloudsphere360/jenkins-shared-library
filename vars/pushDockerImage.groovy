def call(String ProjectName, String ImageTag, String DockerHubUser) {
    withCredentials([
        usernamePassword(
            credentialsId: 'dockerhub-credentials',
            usernameVariable: 'DOCKER_USERNAME',
            passwordVariable: 'DOCKER_PASSWORD'
        )
    ]) {
        sh """
            set -eu
            echo "\$DOCKER_PASSWORD" | docker login -u "\$DOCKER_USERNAME" --password-stdin
            docker push ${DockerHubUser}/${ProjectName}:${ImageTag}
            docker logout
        """
    }
}
