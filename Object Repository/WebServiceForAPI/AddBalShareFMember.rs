<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>AddBalShareFMember</name>
   <tag></tag>
   <elementGuidId>3365e64f-8158-4c7b-862c-94c9b4fe1748</elementGuidId>
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
      <value>AddBalShareFMember</value>
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
      &lt;api:AddBalShareFMember>
         &lt;api:AddBalShareFMemberRequestDto>
            &lt;api:MasterAccNbr>gero et&lt;/api:MasterAccNbr>
            &lt;api:AccNbrDtoList>
               &lt;api:item>
                  &lt;api:AccNbr>sonoras imperio&lt;/api:AccNbr>
                  &lt;api:AcctResCode>quae divum incedo&lt;/api:AcctResCode>
                  &lt;api:EffDate>verrantque per auras&lt;/api:EffDate>
                  &lt;api:ExpDate>per auras&lt;/api:ExpDate>
                  &lt;api:CeilLimit>circum claustra&lt;/api:CeilLimit>
                  &lt;api:DailyCeilLimit>nimborum in&lt;/api:DailyCeilLimit>
                  &lt;api:PaymentForce>foedere certo&lt;/api:PaymentForce>
               &lt;/api:item>
            &lt;/api:AccNbrDtoList>
         &lt;/api:AddBalShareFMemberRequestDto>
      &lt;/api:AddBalShareFMember>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi</soapServiceEndpoint>
   <soapServiceFunction>AddBalShareFMember</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <wsdlAddress>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi?wsdl</wsdlAddress>
</WebServiceRequestEntity>
