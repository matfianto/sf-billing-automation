<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>ChangeOwnerShip</name>
   <tag></tag>
   <elementGuidId>eb7b7bb9-155d-47d8-8096-67c2c855d378</elementGuidId>
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
      <value>ChangeOwnerShip</value>
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
      &lt;api:ChangeOwnerShip>
         &lt;api:ChangeOwnerShipRequestDto>
            &lt;api:Mdn>gero et&lt;/api:Mdn>
            &lt;api:NewCustomerId>sonoras imperio&lt;/api:NewCustomerId>
            &lt;api:UserId>quae divum incedo&lt;/api:UserId>
            &lt;api:StaffJobID>verrantque per auras&lt;/api:StaffJobID>
            &lt;api:OrgId>per auras&lt;/api:OrgId>
            &lt;api:ContactNo>circum claustra&lt;/api:ContactNo>
            &lt;api:Email>nimborum in&lt;/api:Email>
            &lt;api:Remarks>foedere certo&lt;/api:Remarks>
            &lt;api:PaymentPlan>profundum quippe ferant&lt;/api:PaymentPlan>
         &lt;/api:ChangeOwnerShipRequestDto>
      &lt;/api:ChangeOwnerShip>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi</soapServiceEndpoint>
   <soapServiceFunction>ChangeOwnerShip</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <wsdlAddress>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi?wsdl</wsdlAddress>
</WebServiceRequestEntity>
