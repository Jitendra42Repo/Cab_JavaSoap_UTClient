# Cab_JavaSoap_UTClient: Spring Boot SOAP Service

1. Create a client side Java application that consumes the Cab_JavaFirst_SoapService SOAP project's services.
2. Demonstrate a successful ride request is sent to backend.  
3. Use USERNAME_TOKEN (WS-Security) feature to add username and password in the SOAP request before sending it.

## Tech Stack

1. Create a Spring starter project in STS and add "Apache CXF JAX-WS frontend" and "Apache CXF transport HTTP" dependency to enable JAX-WS client support.
2. Copy the CabService.wsdl file and put it under classpath:wsdl path. Use "CXF-codegen-plugin" plugin to generate the stubs. 
3. Write client side code with following steps:

    I. Using CabServiceImplService obtain the portype.     
    II. Using "ClientProxy" object (with portype as parameter) obtain the "Client" object. From "Client" object obtain the "Endpoint" object.     
    II. Create "WSS4JOutInterceptor" and pass properties (like, Action: USERNAME_TOKEN, USER: username, PASSWORD_TYPE: PW_TEXT, PW_CALLBACK_CLASS: 
    PwCallBackHandler) in Map as the parameter.              
    IV. PwCallBackHandler implements CallBackHandler (interface)'s "handle(CallBack[] callbacks)" method. Set the password within the method.
    V. Use portype to access the operations provided by SOAP endpoint and get response.
   
   
