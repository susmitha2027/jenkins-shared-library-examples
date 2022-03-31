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
          ./gradlew clean build
        }
      }
    }
  }
}
