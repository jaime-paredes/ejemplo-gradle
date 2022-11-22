def buildApp(){
    sh "gradle clean build"
}

def runApp(){
    sh "gradle bootRun &"
}

def testApp(){
    sh "curl -X GET 'http://localhost:1010/rest/mscovid/test?msg=testing'"
}

return this