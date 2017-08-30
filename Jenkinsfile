podTemplate(label: 'build',
    containers: [
        containerTemplate(name: 'docker', image: 'docker', ttyEnabled: true, command: 'cat'),
        containerTemplate(name: 'helm', image: '192.168.99.100:30400/helm', ttyEnabled: true, command: 'cat')
        ],
    volumes: [
        hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock')
        ]) {
    node('build') {
        stage('Build') {
            checkout scm

            try {
                sh './gradlew build'
            } finally {
                junit allowEmptyResults: true, testResults: 'build/test-results/**/*.xml'
            }
        }

        stage('Build Docker Image') {
            container('docker') {
                sh 'docker build -t 192.168.99.100:30400/javaapp1 .'
                sh 'docker push 192.168.99.100:30400/javaapp1'
            }
        }

        stage('Deploy') {
            container('helm') {
                sh "helm install --name javaapp1-${BUILD_NUMBER} ./helm"
            }
        }
    }
}
