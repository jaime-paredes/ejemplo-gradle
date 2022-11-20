def buildApp(){
    sh "mvn clean compile -e" 
}

def testApp(){
    sh "mvn clean test -e"
}

def packageApp(){
    sh "mvn clean package -e"
}

return this