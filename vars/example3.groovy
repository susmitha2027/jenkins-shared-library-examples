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
     
      stage('read') {
           steps {
               script {
                   
                   // def data = readCSV file: 'Jenkins_CaseStudy.xlsx',format:excelFormat
                   // def op = data.toString()
                    //def excelFormat = CSVFormat.EXCEL
                    def data = readFile(file: 'Jenkins_CaseStudy.xlsx')
                  //  def records = readCSV file: 'Jenkins_CaseStudy.xlsx'
                    def op = data.toString()
                    println(op)
                   // println(records)
                   //println "The file has ${data.length()} bytes"
//                    def csv = '''Name,Lastname
//                                 Susmitha,Sathi
//                                 Naveen,Ganta'''

//                    def data1 = new CsvParser().parse(csv)
//                    for(line in data1) {
//                         println "$line.Name $line.Lastname"
//                     }
           }
       }
      }
      stage("Email"){
            steps{
              mail(
        body: "<p>Hello Your email has configured successfully!!!</p>",
        charset: 'UTF-8',
        from: 'susmithasathi98@gmail.com',
        mimeType: 'text/html',
        replyTo: '',
        subject: "Hi This is for email configuration in jenkins",
        to: "susmithasathi98@gmail.com",
        attachments: 'Jenkins_CaseStudy.xlsx'
)
             //emailext body: '''Hello Your email has configured successfully!!!''', subject: 'Hi This is for email configuration in jenkins', to: 'susmithasathi98@gmail.com'
            }
            }
    }
    
  }
}
