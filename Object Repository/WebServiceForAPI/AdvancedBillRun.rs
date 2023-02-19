<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>AdvancedBillRun</name>
   <tag></tag>
   <elementGuidId>384a58ac-07cf-4736-bdf2-d46c3def96cf</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent></httpBodyContent>
   <httpBodyType></httpBodyType>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>SOAPAction</name>
      <type>Main</type>
      <value>AdvancedBillRun</value>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>text/xml; charset=utf-8</value>
   </httpHeaderProperties>
   <katalonVersion>7.7.2</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <restRequestMethod></restRequestMethod>
   <restUrl></restUrl>
   <serviceType>SOAP</serviceType>
   <soapBody>&lt;soapenv:Envelope xmlns:soapenv=&quot;http://schemas.xmlsoap.org/soap/envelope/&quot; xmlns:api=&quot;http://api.ws.bss.zsmart.ztesoft.com&quot;>
   &lt;soapenv:Header/>
   &lt;soapenv:Body>
      &lt;api:AdvancedBillRun>
         &lt;api:AdvancedBillRunRequestDto>
            &lt;api:Mdn>gero et&lt;/api:Mdn>
            &lt;api:AccountNumber>sonoras imperio&lt;/api:AccountNumber>
            &lt;api:UserId>quae divum incedo&lt;/api:UserId>
            &lt;api:StaffJobID>verrantque per auras&lt;/api:StaffJobID>
         &lt;/api:AdvancedBillRunRequestDto>
      &lt;/api:AdvancedBillRun>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi</soapServiceEndpoint>
   <soapServiceFunction>AdvancedBillRun</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <wsdlAddress>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi?wsdl</wsdlAddress>
</WebServiceRequestEntity>
