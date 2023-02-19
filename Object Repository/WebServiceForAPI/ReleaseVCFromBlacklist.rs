<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>ReleaseVCFromBlacklist</name>
   <tag></tag>
   <elementGuidId>f94ff088-964f-47f4-99a5-4f0e855f5b65</elementGuidId>
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
      <value>ReleaseVCFromBlacklist</value>
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
      &lt;api:ReleaseVCFromBlacklist>
         &lt;api:ReleaseVCFromBlacklistRequestDto>
            &lt;api:MDN>gero et&lt;/api:MDN>
            &lt;api:OrderReasonId>sonoras imperio&lt;/api:OrderReasonId>
            &lt;api:OrderReason>quae divum incedo&lt;/api:OrderReason>
            &lt;api:ContactNum>verrantque per auras&lt;/api:ContactNum>
            &lt;api:Email>per auras&lt;/api:Email>
            &lt;api:Remarks>circum claustra&lt;/api:Remarks>
            &lt;api:PaymentPlan>nimborum in&lt;/api:PaymentPlan>
            &lt;api:UserId>foedere certo&lt;/api:UserId>
            &lt;api:OrgId>profundum quippe ferant&lt;/api:OrgId>
            &lt;api:StaffJobID>et carcere&lt;/api:StaffJobID>
         &lt;/api:ReleaseVCFromBlacklistRequestDto>
      &lt;/api:ReleaseVCFromBlacklist>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi</soapServiceEndpoint>
   <soapServiceFunction>ReleaseVCFromBlacklist</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <wsdlAddress>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi?wsdl</wsdlAddress>
</WebServiceRequestEntity>
