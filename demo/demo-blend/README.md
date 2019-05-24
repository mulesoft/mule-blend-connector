# Blend Demo


Add below dependency to demo-blend pom.xml:

```
<dependency>
    <groupId>com.mulesoft.connectors</groupId>
    <artifactId>mule-blend-connector</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <classifier>mule-plugin</classifier>
</dependency>
```
1. Add the configuration values in configuration.yaml located at src/main/resources.
2. Check if global-configuration.xml has picked the properties by clicking on Test Connection.
3. Open one of the mule configuration files and run the project.
4. Once the project gets deployed successfully, hit the endpoints mentioned in listener path from any rest client. 
5. For all the localhost endpoints and valid payload values please import the "blend-demo-postman_collection.json" file in postman client.
6. Note that below listed services do not return any payload.Instead they return a {} response and a success code 200.
 
         i) Update Loan Properties
        ii) Delete a Borrower
       iii) Update Loan Borrowers Location    
7. To run the business use case successfully, first run /deleteAllSF endpoint and then others so that salesforce system is cleaned up before posting contact with same details.

Note: For TLS configurations you should have keystore.jks in src/main/resources directory, already packaged with the demo