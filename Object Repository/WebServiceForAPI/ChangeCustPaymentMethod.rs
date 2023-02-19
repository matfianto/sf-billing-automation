<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>ChangeCustPaymentMethod</name>
   <tag></tag>
   <elementGuidId>2cd161ca-2013-4a94-b118-29f256f749e0</elementGuidId>
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
      <value>ChangeCustPaymentMethod</value>
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
      &lt;api:ChangeCustPaymentMethod>
         &lt;api:ChangeCustPaymentMethodRequestDto>
            &lt;api:Mdn>gero et&lt;/api:Mdn>
            &lt;api:UserId>sonoras imperio&lt;/api:UserId>
            &lt;api:StaffJobID>quae divum incedo&lt;/api:StaffJobID>
            &lt;api:OrgId>verrantque per auras&lt;/api:OrgId>
            &lt;api:PaymentType>per auras&lt;/api:PaymentType>
            &lt;api:BankAccountType>circum claustra&lt;/api:BankAccountType>
            &lt;api:CreditType>nimborum in&lt;/api:CreditType>
            &lt;api:BankCode>foedere certo&lt;/api:BankCode>
            &lt;api:CardExpireDate>profundum quippe ferant&lt;/api:CardExpireDate>
            &lt;api:CardNumber>et carcere&lt;/api:CardNumber>
            &lt;api:CardOwner>iovis rapidum iaculata&lt;/api:CardOwner>
            &lt;api:BankAccountNo>speluncis abdidit&lt;/api:BankAccountNo>
            &lt;api:BankAccountExpireDate>bella gero et&lt;/api:BankAccountExpireDate>
            &lt;api:BankAccountName>flammas turbine&lt;/api:BankAccountName>
            &lt;api:Remarks>hoc metuens&lt;/api:Remarks>
         &lt;/api:ChangeCustPaymentMethodRequestDto>
      &lt;/api:ChangeCustPaymentMethod>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi</soapServiceEndpoint>
   <soapServiceFunction>ChangeCustPaymentMethod</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <wsdlAddress>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi?wsdl</wsdlAddress>
</WebServiceRequestEntity>
