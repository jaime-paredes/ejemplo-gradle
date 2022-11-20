def buildApp(){
    sh "gradle clean build"
}

def runApp(){
    sh "gradle bootRun &"
    sleep(15)
}

def testApp(){
    sh "gradle test"
}

return this