<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>RestoreMdn</name>
   <tag></tag>
   <elementGuidId>190f4d4b-9251-49f5-83e8-20e84a5ac6aa</elementGuidId>
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
      <value>RestoreMdn</value>
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
      &lt;api:RestoreMdn>
         &lt;api:RestoreMdnRequestDto>
            &lt;api:MDN>gero et&lt;/api:MDN>
            &lt;api:OrderReasonId>sonoras imperio&lt;/api:OrderReasonId>
            &lt;api:OrderReason>quae divum incedo&lt;/api:OrderReason>
            &lt;api:RestoreDate>verrantque per auras&lt;/api:RestoreDate>
            &lt;api:ContactNum>per auras&lt;/api:ContactNum>
            &lt;api:Email>circum claustra&lt;/api:Email>
            &lt;api:Remarks>nimborum in&lt;/api:Remarks>
            &lt;api:PaymentPlan>foedere certo&lt;/api:PaymentPlan>
            &lt;api:UserId>profundum quippe ferant&lt;/api:UserId>
            &lt;api:OrgId>et carcere&lt;/api:OrgId>
            &lt;api:StaffJobID>iovis rapidum iaculata&lt;/api:StaffJobID>
         &lt;/api:RestoreMdnRequestDto>
      &lt;/api:RestoreMdn>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi</soapServiceEndpoint>
   <soapServiceFunction>RestoreMdn</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <wsdlAddress>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi?wsdl</wsdlAddress>
</WebServiceRequestEntity>
