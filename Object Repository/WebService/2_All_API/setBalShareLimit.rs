<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>setBalShareLimit</name>
   <tag></tag>
   <elementGuidId>d5541252-9c68-4802-a2b3-05e27833d4a2</elementGuidId>
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
      <value></value>
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
   <soapBody>&lt;soapenv:Envelope xmlns:soapenv=&quot;http://schemas.xmlsoap.org/soap/envelope/&quot; xmlns:ws=&quot;http://ws.bss.zsmart.ztesoft.com&quot; xmlns:dto=&quot;http://dto.landingSite.ws.bss.zsmart.ztesoft.com&quot;>
   &lt;soapenv:Header/>
   &lt;soapenv:Body>
      &lt;ws:setBalShareLimit>
         &lt;ws:masterAccNbr>gero et&lt;/ws:masterAccNbr>
         &lt;ws:accNbrDtoList>
            &lt;dto:accNbr>sonoras imperio&lt;/dto:accNbr>
            &lt;dto:ceilLimit>quae divum incedo&lt;/dto:ceilLimit>
            &lt;dto:partitionCode>verrantque per auras&lt;/dto:partitionCode>
         &lt;/ws:accNbrDtoList>
      &lt;/ws:setBalShareLimit>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>http://10.17.85.25:8093/ocswebservices/services/WebServices</soapServiceEndpoint>
   <soapServiceFunction>setBalShareLimit</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <wsdlAddress>http://10.17.85.25:8093/ocswebservices/services/WebServices?wsdl</wsdlAddress>
</WebServiceRequestEntity>