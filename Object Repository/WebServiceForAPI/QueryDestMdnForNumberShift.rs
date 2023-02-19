<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>QueryDestMdnForNumberShift</name>
   <tag></tag>
   <elementGuidId>4311efe6-d0ad-4ea1-b935-248943255850</elementGuidId>
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
      <value>QueryDestMdnForNumberShift</value>
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
      &lt;api:QueryDestMdnForNumberShift>
         &lt;api:QueryDestMdnForNumberShiftRequestDto>
            &lt;api:SourceMdn>gero et&lt;/api:SourceMdn>
            &lt;api:MdnFrom>sonoras imperio&lt;/api:MdnFrom>
            &lt;api:MdnTo>quae divum incedo&lt;/api:MdnTo>
            &lt;api:Grade>verrantque per auras&lt;/api:Grade>
            &lt;api:Rule>per auras&lt;/api:Rule>
            &lt;api:RecordCount>circum claustra&lt;/api:RecordCount>
            &lt;api:OrgId>nimborum in&lt;/api:OrgId>
            &lt;api:OrgIdFlag>foedere certo&lt;/api:OrgIdFlag>
         &lt;/api:QueryDestMdnForNumberShiftRequestDto>
      &lt;/api:QueryDestMdnForNumberShift>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi</soapServiceEndpoint>
   <soapServiceFunction>QueryDestMdnForNumberShift</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <wsdlAddress>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi?wsdl</wsdlAddress>
</WebServiceRequestEntity>
