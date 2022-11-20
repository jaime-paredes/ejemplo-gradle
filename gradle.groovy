def buildApp(){
    sh "gradle clean build"
}

def runApp(){
    sh "gradle bootRun &"
}

def testApp(){
    sh "gradle test"
}

return this