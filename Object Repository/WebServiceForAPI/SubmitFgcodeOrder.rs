<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>SubmitFgcodeOrder</name>
   <tag></tag>
   <elementGuidId>0ee3fef0-ce3a-4a01-928a-5a3680cbd9ef</elementGuidId>
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
      <value>SubmitFgcodeOrder</value>
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
      &lt;api:SubmitFgcodeOrder>
         &lt;api:SubmitFgcodeOrderRequestDto>
            &lt;api:FgDtoList>
               &lt;api:item>
                  &lt;api:ICCID>gero et&lt;/api:ICCID>
                  &lt;api:ESN>sonoras imperio&lt;/api:ESN>
                  &lt;api:MDN>quae divum incedo&lt;/api:MDN>
                  &lt;api:RealESN>verrantque per auras&lt;/api:RealESN>
                  &lt;api:PromotionID>per auras&lt;/api:PromotionID>
                  &lt;api:FgCode>circum claustra&lt;/api:FgCode>
                  &lt;api:SubPromotionId>nimborum in&lt;/api:SubPromotionId>
               &lt;/api:item>
            &lt;/api:FgDtoList>
            &lt;api:PaymentType>foedere certo&lt;/api:PaymentType>
            &lt;api:CustomerName>profundum quippe ferant&lt;/api:CustomerName>
            &lt;api:CertNumber>et carcere&lt;/api:CertNumber>
            &lt;api:CertType>iovis rapidum iaculata&lt;/api:CertType>
            &lt;api:Remarks>speluncis abdidit&lt;/api:Remarks>
            &lt;api:UserId>bella gero et&lt;/api:UserId>
            &lt;api:OrgId>flammas turbine&lt;/api:OrgId>
            &lt;api:StaffJobID>hoc metuens&lt;/api:StaffJobID>
         &lt;/api:SubmitFgcodeOrderRequestDto>
      &lt;/api:SubmitFgcodeOrder>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi</soapServiceEndpoint>
   <soapServiceFunction>SubmitFgcodeOrder</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <wsdlAddress>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi?wsdl</wsdlAddress>
</WebServiceRequestEntity>
