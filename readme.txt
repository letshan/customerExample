Customer Example Instruction
2013/08/27
han zhang

This project is an example of ajax web service

To build:

unzip the downlowded file; under your command line run:
mvn package

To install:
copy the built war into your web server working folder, for example %TOMCAT%/webapps and rename the war as CustomerExample.war.
I would use this command:
cp CustomerExample-0.0.3-SNAPSHOT.war %TOMCAT%/webapps/CustomerExample.war

note: the example used derby database so no extra database setting need to config.

To test:
Unit test 
	maven build covered the service unit test cases.
Integration Test
	1 run the web application on your web server.
	2 edit com.authmo.customer.client.CustomerServiceClientTest.java under src/test/java folder, 
	comment/remove @Ignore annotation. 
	then run this class as junit test. 