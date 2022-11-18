def build(){
    sh "gradle clean build"
}

def run(){
    sh "gradle bootRun &"
}

def test(){
    sh "gradle test"
}

return this