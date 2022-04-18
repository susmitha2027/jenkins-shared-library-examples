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
                   
                    def data = readCSV file: 'Jenkins_CaseStudy.xlsx')
                    println(data)
                   println "The file has ${data.length()} bytes"
                   def csv = '''Name,Lastname
                                Susmitha,Sathi
                                Naveen,Ganta'''

                   def data1 = new CsvParser().parse(csv)
                   for(line in data1) {
                        println "$line.Name $line.Lastname"
                    }
           }
       }
      }
      stage("Email"){
            steps{
             emailext body: '''Hello Your email has configured successfully!!!''', subject: 'Hi This is for email configuration in jenkins', to: 'susmithasathi98@gmail.com'
            }
            }
    }
    
  }
}
