def toolScript

pipeline {
    agent any

    tools {
        gradle "gradle"
        maven "maven"
    }

    parameters{
        choice(name: "BUILD_TOOL", choices: ["maven", "gradle"], description: "Build tool")
    }
    
    stages{
        stage("*** SCRIPT LOAD ***"){
            echo "*** SCRIPT LOAD ***"
            steps{
                script{
                    toolScript = load "${params.BUILD_TOOL}.groovy"
                }
            }
        }
        stage("*** BUILD ***"){
            echo "*** BUILD ***"
            steps{
                script{
                    toolScript.build()
                }
            }
        }
        if $params.BUILD_TOOL == "maven" {
            echo "maven"
        } else {
            echo "gradle"
        }
    
    }
}