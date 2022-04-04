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
                   def data = readFile(file: '/Jenkins_CaseStudy.xlsx')
                   println(data)
               }
           }
       }
      
    }
  }
}
