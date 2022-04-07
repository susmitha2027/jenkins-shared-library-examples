#!/usr/bin/env groovy

@Grab('com.xlson.groovycsv:groovycsv:0.2')
import com.xlson.groovycsv.CsvParser

def call(String repoUrl) {
  pipeline {
    agent any 
    stages {
      stage("checkout") {
        steps {
          git branch: 'master',
            url: "${repoUrl}"
        }
      }
      stage("build") {
        steps {
          bat './gradlew clean build'
        }
      }
      stage('read') {
           steps {
               script {
                   def data = readFile(file: 'Jenkins_CaseStudy.xlsx')
                   println "The file has ${data.length()} bytes"
                   println(data)
                   def csv = '''Name,Lastname
                                Mark,Andersson
                                Pete,Hansen'''

                   def data1 = new CsvParser().parse(csv)
                   for(line in data1) {
                        println "$line.Name $line.Lastname"
                    }
           }
       }
      }
      stage("Email){
            steps{
              emailext (to: 'susmithasathi98@gmail.com', replyTo: 'susmithasathi98@gmail.com', subject: "Email Report from jenkins", body:"hloo");
            }
            }
    }
    
  }
}
