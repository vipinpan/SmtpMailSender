# SmtpMailSender
This repo can be used to send a mail on smtp server.
Download these class files and put it in your project under different packages and build your project with maven with the help of pom.xml.
Once you are done building project You can use any RestClient such as Postman restclient and then you can Post your request with Body containing all the required fields in the form of json.
You will get the response in the form of json.
In Postman in URL section just type "http://localhost:8080/smtpreq" and chose Post.
Before doing all of this Start Spring boot server by typing "mvn spring-boot:run". Once it starts running on your local machine then you can post your request in Postman by using above given info.
Body of request will look like this : {"host":"mailHost","username":"user@mailHost" , "password" : "pass", "recipientMailId": "vipinpandey75@gmail.com", "mailSubject":"Testing smtp mail sending." , "mailBody":"This is mail number 1." ,"attachFiles":"attachmentPath"}
