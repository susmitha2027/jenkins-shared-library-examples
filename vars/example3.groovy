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
                   println "The file $data has ${data.length()} bytes"
                 //  println(data)
                   def csv = '''Name,Lastname
                                Mark,Andersson
                                Pete,Hansen'''

                   def myFile = new File('example.txt')
                    def myScanner = new Scanner(myFile)
                    while (myScanner.hasNextLine()) {
                    def line = myScanner.nextLine()
                    println(line)
                    }
                  myScanner.close()
           }
       }
      }
    }
  }
}
