<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>SubmitEvoucher</name>
   <tag></tag>
   <elementGuidId>88876e91-e879-4bbc-ba9e-4dba871ca825</elementGuidId>
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
      <value>SubmitEvoucher</value>
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
      &lt;api:SubmitEvoucher>
         &lt;api:SubmitEvoucherRequestDto>
            &lt;api:FgCodeDtoList>
               &lt;api:item>
                  &lt;api:FgCode>401243&lt;/api:FgCode>
                  &lt;api:PromotionID>10000182&lt;/api:PromotionID>
                  &lt;api:PromotionName>Smart Power and Eload 10000182&lt;/api:PromotionName>
                  &lt;api:ParPrice>10000&lt;/api:ParPrice>
                  &lt;api:ReciveCharge>10000&lt;/api:ReciveCharge>
                  &lt;api:Count>1&lt;/api:Count>
               &lt;/api:item>
            &lt;/api:FgCodeDtoList>
            &lt;api:Mdn>6288295291794&lt;/api:Mdn>
            &lt;api:UserId>1085053&lt;/api:UserId>
            &lt;api:OrgId>2&lt;/api:OrgId>
            &lt;api:PaymentMethod>1&lt;/api:PaymentMethod>
            &lt;api:CardNumber>&lt;/api:CardNumber>
            &lt;api:ExpireDate>&lt;/api:ExpireDate>
            &lt;api:BankCode>881&lt;/api:BankCode>
            &lt;api:Comments>&lt;/api:Comments>
            &lt;api:Remarks>&lt;/api:Remarks>
            &lt;api:StaffJobID>5080056&lt;/api:StaffJobID>
         &lt;/api:SubmitEvoucherRequestDto>
      &lt;/api:SubmitEvoucher>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi</soapServiceEndpoint>
   <soapServiceFunction>SubmitEvoucher</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi?wsdl</wsdlAddress>
</WebServiceRequestEntity>
