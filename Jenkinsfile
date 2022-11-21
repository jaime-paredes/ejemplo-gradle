def toolScript

pipeline {
    agent any

    tools {
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
                    params.BUILD_TOOL = "${params.BUILD_TOOL.toLowerCase()}"
                }
            }
        }
        stage("*** SCRIPT LOAD ***"){
            steps{
                echo "*** SCRIPT LOAD ***"
                script{
                    toolScript = load "${params.BUILD_TOOL}.groovy"
                }
            }
        }
        stage("*** BUILD ***"){
            steps{
                echo "*** BUILDING WITH ${params.BUILD_TOOL} ***"
                script{
                    toolScript.buildApp()
                }
            }
        }
        stage("*** RUN ***") {
            when {
                expression { params.BUILD_TOOL == "gradle" }
            }
            steps {
                echo "*** RUNNING WITH ${params.BUILD_TOOL} ***"
                timeout(time: 30, unit: 'SECONDS'){
                    waitUntil{
                        script{
                            toolScript.runApp()
                            return true
                        }
                    }
                }
            }
        }
        stage("*** TEST *** "){
            steps {
                echo "*** TESTING WITH ${params.BUILD_TOOL} ***"
                script{
                    toolScript.testApp()
                }
            }            
        }
        stage("*** PACKAGE ***") {
            when {
                expression { params.BUILD_TOOL == "maven" }
            }
            steps {
                echo "*** PACKAGING WITH ${params.BUILD_TOOL} ***"
                script{
                    toolScript.packageApp()
                }
            }
        }
    }
}