def toolScript
def failStage

pipeline{
    agent any

    tools{
        gradle "gradle"
        maven "maven"
    }

    parameters{
        choice(name: "BUILD_TOOL", choices: ["Maven", "Gradle"], description: "Build tool")
    }

    stages{
        stage("*** BUILD TOOL ***"){
            steps{
                echo "Using ${params.BUILD_TOOL}!!!"
                script{ 
                    failStage = ENV.STAGE_NAME
                    env.BUILD_TOOL = "${params.BUILD_TOOL.toLowerCase()}"
                }
            }
        }
        stage("*** SCRIPT LOAD ***"){
            steps{
                echo "*** SCRIPT LOAD ***"
                script{
                    failStage = ENV.STAGE_NAME
                    toolScript = load "${env.BUILD_TOOL}.groovy"
                }
            }
        }
        stage("*** BUILD ***"){
            steps{
                echo "*** BUILDING WITH ${env.BUILD_TOOL} ***"
                script{
                    failStage = ENV.STAGE_NAME
                    toolScript.buildApp()
                }
            }
        }
        stage("*** RUN ***"){
            when {
                expression { env.BUILD_TOOL == "gradle" }
            }
            steps {
                echo "*** RUNNING WITH ${env.BUILD_TOOL} ***"
                timeout(time: 5, unit: 'SECONDS'){
                    waitUntil{
                        script{
                            failStage = ENV.STAGE_NAME
                            toolScript.runApp()
                            return true
                        }
                    }
                }
            }
        }
        stage("*** TEST *** "){
            steps {
                echo "*** TESTING WITH ${env.BUILD_TOOL} ***"
                script{
                    failStage = ENV.STAGE_NAME
                    toolScript.testApp()
                }
            }            
        }
        stage("*** PACKAGE ***"){
            when {
                expression { env.BUILD_TOOL == "maven" }
            }
            steps {
                echo "*** PACKAGING WITH ${env.BUILD_TOOL} ***"
                script{
                    failStage = ENV.STAGE_NAME
                    toolScript.packageApp()
                }
            }
        }
        stage("*** SLACK IT ***"){
            steps {
                failStage = ENV.STAGE_NAME
                slackSend channel: 'C04CAGU8ESD', message: 'Slack It!!!'
            }
        }
    }

    post {
        always {
            echo "I will always say hello in the console."
            slackSend channel: 'C04CAGU8ESD', message: "*${currentBuild.currentResult}:* Job ${failStage}"
        }
    }

}