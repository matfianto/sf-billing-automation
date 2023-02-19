<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>CreateDeposit</name>
   <tag></tag>
   <elementGuidId>6eeb486a-858b-4394-93b2-de6f2d4809dd</elementGuidId>
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
      <value>CreateDeposit</value>
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
      &lt;api:CreateDeposit>
         &lt;api:CreateDepositRequestDto>
            &lt;api:MDN>gero et&lt;/api:MDN>
            &lt;api:DepositTypeId>sonoras imperio&lt;/api:DepositTypeId>
            &lt;api:PaymentMethod>quae divum incedo&lt;/api:PaymentMethod>
            &lt;api:Reason>verrantque per auras&lt;/api:Reason>
            &lt;api:Comments>per auras&lt;/api:Comments>
            &lt;api:BankCode>circum claustra&lt;/api:BankCode>
            &lt;api:CardNumber>nimborum in&lt;/api:CardNumber>
            &lt;api:ExpireDate>foedere certo&lt;/api:ExpireDate>
            &lt;api:UserId>profundum quippe ferant&lt;/api:UserId>
            &lt;api:OrgId>et carcere&lt;/api:OrgId>
            &lt;api:StaffJobID>iovis rapidum iaculata&lt;/api:StaffJobID>
         &lt;/api:CreateDepositRequestDto>
      &lt;/api:CreateDeposit>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi</soapServiceEndpoint>
   <soapServiceFunction>CreateDeposit</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <wsdlAddress>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi?wsdl</wsdlAddress>
</WebServiceRequestEntity>
