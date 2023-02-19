<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>NewConnection</name>
   <tag></tag>
   <elementGuidId>b8364f9a-ba14-404f-ad47-9e4c796e6b3c</elementGuidId>
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
      <value>NewConnection</value>
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
      &lt;api:NewConnection>
         &lt;api:NewConnectionRequestDto>
            &lt;api:MDN>gero et&lt;/api:MDN>
            &lt;api:AcctId>sonoras imperio&lt;/api:AcctId>
            &lt;api:Iccid>quae divum incedo&lt;/api:Iccid>
            &lt;api:PromotionId>verrantque per auras&lt;/api:PromotionId>
            &lt;api:OfferId>per auras&lt;/api:OfferId>
            &lt;api:DependProdDtoList>
               &lt;api:item>
                  &lt;api:DependProdId>circum claustra&lt;/api:DependProdId>
               &lt;/api:item>
            &lt;/api:DependProdDtoList>
            &lt;api:PricePlanDtoList>
               &lt;api:item>
                  &lt;api:PricePlanId>nimborum in&lt;/api:PricePlanId>
                  &lt;api:EFFType>foedere certo&lt;/api:EFFType>
                  &lt;api:EffDate>profundum quippe ferant&lt;/api:EffDate>
                  &lt;api:ExpDate>et carcere&lt;/api:ExpDate>
               &lt;/api:item>
            &lt;/api:PricePlanDtoList>
            &lt;api:DefaultPricePlanId>iovis rapidum iaculata&lt;/api:DefaultPricePlanId>
            &lt;api:UserId>speluncis abdidit&lt;/api:UserId>
            &lt;api:OrgId>bella gero et&lt;/api:OrgId>
            &lt;api:StaffJobID>flammas turbine&lt;/api:StaffJobID>
         &lt;/api:NewConnectionRequestDto>
      &lt;/api:NewConnection>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi</soapServiceEndpoint>
   <soapServiceFunction>NewConnection</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <wsdlAddress>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi?wsdl</wsdlAddress>
</WebServiceRequestEntity>
