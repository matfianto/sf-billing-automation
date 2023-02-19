<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>QueryMdn</name>
   <tag></tag>
   <elementGuidId>0cfe1230-6bd4-452e-8eae-6268812fd5f3</elementGuidId>
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
      <value>QueryMdn</value>
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
      &lt;api:QueryMdn>
         &lt;api:QueryMdnRequestDto>
            &lt;api:MdnFrom>gero et&lt;/api:MdnFrom>
            &lt;api:MdnTo>sonoras imperio&lt;/api:MdnTo>
            &lt;api:Grade>quae divum incedo&lt;/api:Grade>
            &lt;api:Rule>verrantque per auras&lt;/api:Rule>
            &lt;api:RecordCount>per auras&lt;/api:RecordCount>
            &lt;api:SubsEventId>circum claustra&lt;/api:SubsEventId>
            &lt;api:PostpaidFlag>nimborum in&lt;/api:PostpaidFlag>
            &lt;api:OrgId>foedere certo&lt;/api:OrgId>
            &lt;api:OrgIdFlag>profundum quippe ferant&lt;/api:OrgIdFlag>
         &lt;/api:QueryMdnRequestDto>
      &lt;/api:QueryMdn>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi</soapServiceEndpoint>
   <soapServiceFunction>QueryMdn</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <wsdlAddress>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi?wsdl</wsdlAddress>
</WebServiceRequestEntity>
