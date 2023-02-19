<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>PaymentSubsOrder</name>
   <tag></tag>
   <elementGuidId>8158dc4e-f0d2-4942-97ce-8cf37142df67</elementGuidId>
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
      <value>PaymentSubsOrder</value>
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
      &lt;api:PaymentSubsOrder>
         &lt;api:PaymentSubsOrderRequestDto>
            &lt;api:OrderNumber>gero et&lt;/api:OrderNumber>
            &lt;api:PaymentMethod>sonoras imperio&lt;/api:PaymentMethod>
            &lt;api:CardNumber>quae divum incedo&lt;/api:CardNumber>
            &lt;api:ExpireDate>verrantque per auras&lt;/api:ExpireDate>
            &lt;api:BankCode>per auras&lt;/api:BankCode>
            &lt;api:Comments>circum claustra&lt;/api:Comments>
            &lt;api:Remarks>nimborum in&lt;/api:Remarks>
            &lt;api:UserId>foedere certo&lt;/api:UserId>
            &lt;api:OrgId>profundum quippe ferant&lt;/api:OrgId>
            &lt;api:StaffJobID>et carcere&lt;/api:StaffJobID>
         &lt;/api:PaymentSubsOrderRequestDto>
      &lt;/api:PaymentSubsOrder>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi</soapServiceEndpoint>
   <soapServiceFunction>PaymentSubsOrder</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <wsdlAddress>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi?wsdl</wsdlAddress>
</WebServiceRequestEntity>
