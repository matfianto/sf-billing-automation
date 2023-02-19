<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>TerminateSubscriber</name>
   <tag></tag>
   <elementGuidId>e4533b4e-fdb5-49f2-a735-8c9de52cc304</elementGuidId>
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
      <value>TerminateSubscriber</value>
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
      &lt;api:TerminateSubscriber>
         &lt;api:TerminateSubscriberRequestDto>
            &lt;api:Mdn>gero et&lt;/api:Mdn>
            &lt;api:TerminationReason>sonoras imperio&lt;/api:TerminationReason>
            &lt;api:CustomerInputReason>quae divum incedo&lt;/api:CustomerInputReason>
            &lt;api:UserId>verrantque per auras&lt;/api:UserId>
            &lt;api:StaffJobID>per auras&lt;/api:StaffJobID>
            &lt;api:OrgId>circum claustra&lt;/api:OrgId>
            &lt;api:ContactNo>nimborum in&lt;/api:ContactNo>
            &lt;api:Email>foedere certo&lt;/api:Email>
            &lt;api:Remarks>profundum quippe ferant&lt;/api:Remarks>
            &lt;api:PaymentPlan>et carcere&lt;/api:PaymentPlan>
         &lt;/api:TerminateSubscriberRequestDto>
      &lt;/api:TerminateSubscriber>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi</soapServiceEndpoint>
   <soapServiceFunction>TerminateSubscriber</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <wsdlAddress>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi?wsdl</wsdlAddress>
</WebServiceRequestEntity>
