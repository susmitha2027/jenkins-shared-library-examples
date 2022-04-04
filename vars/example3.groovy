#!/usr/bin/env groovy
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
                   def data1 = new CsvParser().parse(data)
                   for(line in data1) {
                        println "$line.AppID $line.AppName"
                    }
               }
           }
       }
      
    }
  }
}
