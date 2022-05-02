job('practica nodejs dockerhub') {
    description('pracktica de docker')
    scm {
        git('https://github.com/rencinast/practica-dockerhub.git', 'master') { node ->
            node / gitConfigName('rencinast')
            node / gitConfigEmail('rodrix1233@hotmail.com')
        }
    }
    triggers {
        scm('H/7 * * * *')
    }
    wrappers {
        nodejs('nodejs')
    }
	
    steps {
	dockerBuildAndPublish {
        repositoryName('rencinas123/practica_docker_hub')
        tag('${GIT_REVISION,length=7}')
        registryCredentials('docker-hub')
        forcePull(false)
        createFingerprints(false)
        skipDecorate()
        }
    }

    publishers {
	slackNotifier {
            notifyAborted(true)
            notifyEveryFailure(true)
            notifyNotBuilt(false)
            notifyUnstable(false)
            notifyBackToNormal(true)
            notifySuccess(true)
            notifyRepeatedFailure(false)
            startNotification(false)
            includeTestSummary(false)
            includeCustomMessage(false)
            customMessage(null)
            sendAs(null)
            commitInfoChoice('NONE')
            teamDomain(null)
            authToken(null)
        }
    }
}
