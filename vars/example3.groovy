#!/usr/bin/env groovy

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
                   def data = new CsvParser().parse(csv)
                   for(line in data) {
                        println "$line.AppID $line.AppName"
                    }
               }
           }
       }
      
    }
  }
}
