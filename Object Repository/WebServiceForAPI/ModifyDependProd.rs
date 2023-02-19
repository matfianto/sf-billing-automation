<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>ModifyDependProd</name>
   <tag></tag>
   <elementGuidId>cf109b95-26ed-42a5-a2be-1cac91cf423a</elementGuidId>
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
      <value>ModifyDependProd</value>
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
      &lt;api:ModifyDependProd>
         &lt;api:ModifyDependProdRequestDto>
            &lt;api:Mdn>gero et&lt;/api:Mdn>
            &lt;api:DependProdId>sonoras imperio&lt;/api:DependProdId>
            &lt;api:ProdAttrDtoList>
               &lt;api:item>
                  &lt;api:ProdAttrID>quae divum incedo&lt;/api:ProdAttrID>
                  &lt;api:AttrValue>verrantque per auras&lt;/api:AttrValue>
               &lt;/api:item>
            &lt;/api:ProdAttrDtoList>
            &lt;api:Action>per auras&lt;/api:Action>
            &lt;api:EffectiveType>circum claustra&lt;/api:EffectiveType>
            &lt;api:EffectiveDate>nimborum in&lt;/api:EffectiveDate>
            &lt;api:ExpiryDate>foedere certo&lt;/api:ExpiryDate>
            &lt;api:ContactNo>profundum quippe ferant&lt;/api:ContactNo>
            &lt;api:Email>et carcere&lt;/api:Email>
            &lt;api:Remarks>iovis rapidum iaculata&lt;/api:Remarks>
            &lt;api:PaymentPlan>speluncis abdidit&lt;/api:PaymentPlan>
            &lt;api:SendSMS>bella gero et&lt;/api:SendSMS>
            &lt;api:UserId>flammas turbine&lt;/api:UserId>
            &lt;api:StaffJobID>hoc metuens&lt;/api:StaffJobID>
            &lt;api:OrgId>ac vinclis&lt;/api:OrgId>
         &lt;/api:ModifyDependProdRequestDto>
      &lt;/api:ModifyDependProd>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi</soapServiceEndpoint>
   <soapServiceFunction>ModifyDependProd</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <wsdlAddress>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi?wsdl</wsdlAddress>
</WebServiceRequestEntity>
