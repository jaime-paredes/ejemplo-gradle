def build(){
    sh "mvn clean compile -e" 
}

def test(){
    sh "mvn clean test -e"
}

def package(){
    sh "mvn clean package -e"
}

return this