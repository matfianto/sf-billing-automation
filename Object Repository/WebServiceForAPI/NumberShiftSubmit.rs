<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>NumberShiftSubmit</name>
   <tag></tag>
   <elementGuidId>c52720e6-247e-412d-afdd-8bd59c900091</elementGuidId>
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
      <value>NumberShiftSubmit</value>
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
      &lt;api:NumberShiftSubmit>
         &lt;api:NumberShiftSubmitRequestDto>
            &lt;api:SourceMdn>gero et&lt;/api:SourceMdn>
            &lt;api:DestMdn>sonoras imperio&lt;/api:DestMdn>
            &lt;api:ContactNumber>quae divum incedo&lt;/api:ContactNumber>
            &lt;api:UserId>verrantque per auras&lt;/api:UserId>
            &lt;api:OrgId>per auras&lt;/api:OrgId>
            &lt;api:StaffJobID>circum claustra&lt;/api:StaffJobID>
            &lt;api:Email>nimborum in&lt;/api:Email>
            &lt;api:Remarks>foedere certo&lt;/api:Remarks>
            &lt;api:PaymentPlan>profundum quippe ferant&lt;/api:PaymentPlan>
            &lt;api:IsBalanceDeduct>et carcere&lt;/api:IsBalanceDeduct>
            &lt;api:actionFlag>iovis rapidum iaculata&lt;/api:actionFlag>
         &lt;/api:NumberShiftSubmitRequestDto>
      &lt;/api:NumberShiftSubmit>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi</soapServiceEndpoint>
   <soapServiceFunction>NumberShiftSubmit</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <wsdlAddress>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi?wsdl</wsdlAddress>
</WebServiceRequestEntity>
