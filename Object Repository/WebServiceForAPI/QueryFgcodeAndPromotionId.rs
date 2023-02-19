<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>QueryFgcodeAndPromotionId</name>
   <tag></tag>
   <elementGuidId>ffc6a201-f939-4675-8d81-419233d1e077</elementGuidId>
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
      <value>QueryFgcodeAndPromotionId</value>
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
      &lt;api:QueryFgcodeAndPromotionId>
         &lt;api:QueryFgcodeAndPromotionIdRequestDto>
            &lt;api:ICCID>gero et&lt;/api:ICCID>
            &lt;api:ESN>sonoras imperio&lt;/api:ESN>
            &lt;api:MDN>quae divum incedo&lt;/api:MDN>
            &lt;api:OrgId>verrantque per auras&lt;/api:OrgId>
            &lt;api:FgType>per auras&lt;/api:FgType>
            &lt;api:FgCode>circum claustra&lt;/api:FgCode>
            &lt;api:FgState>nimborum in&lt;/api:FgState>
         &lt;/api:QueryFgcodeAndPromotionIdRequestDto>
      &lt;/api:QueryFgcodeAndPromotionId>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi</soapServiceEndpoint>
   <soapServiceFunction>QueryFgcodeAndPromotionId</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <wsdlAddress>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi?wsdl</wsdlAddress>
</WebServiceRequestEntity>
