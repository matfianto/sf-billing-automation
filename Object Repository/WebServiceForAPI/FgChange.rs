<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>FgChange</name>
   <tag></tag>
   <elementGuidId>49272d56-ad25-4aa4-acac-b39e7ce42cc2</elementGuidId>
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
      <value>FgChange</value>
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
      &lt;api:FgChange>
         &lt;api:FgChangeRequestDto>
            &lt;api:FgType>gero et&lt;/api:FgType>
            &lt;api:CurrentEsn>sonoras imperio&lt;/api:CurrentEsn>
            &lt;api:CurrentICCID>quae divum incedo&lt;/api:CurrentICCID>
            &lt;api:CurrentMdn>verrantque per auras&lt;/api:CurrentMdn>
            &lt;api:NewEsn>per auras&lt;/api:NewEsn>
            &lt;api:NewFgCode>circum claustra&lt;/api:NewFgCode>
            &lt;api:NewIccid>nimborum in&lt;/api:NewIccid>
            &lt;api:UserId>foedere certo&lt;/api:UserId>
            &lt;api:StaffJobID>profundum quippe ferant&lt;/api:StaffJobID>
            &lt;api:OrgId>et carcere&lt;/api:OrgId>
            &lt;api:ReasonId>iovis rapidum iaculata&lt;/api:ReasonId>
            &lt;api:Remarks>speluncis abdidit&lt;/api:Remarks>
         &lt;/api:FgChangeRequestDto>
      &lt;/api:FgChange>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi</soapServiceEndpoint>
   <soapServiceFunction>FgChange</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <wsdlAddress>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi?wsdl</wsdlAddress>
</WebServiceRequestEntity>
