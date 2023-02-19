<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>SubmitEvoucher</name>
   <tag></tag>
   <elementGuidId>63b88b2f-34c9-4922-839b-f1479326aeb0</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>0</connectionTimeout>
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
      <webElementGuid>b842b069-732f-4f58-95ec-44e01a06f681</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>text/xml; charset=utf-8</value>
      <webElementGuid>3cfa9a95-6853-4ff3-893d-acd3bf786e70</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>7.7.2</katalonVersion>
   <maxResponseSize>0</maxResponseSize>
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
                  &lt;api:ParPrice>${Denom}&lt;/api:ParPrice>
                  &lt;api:ReciveCharge>${Denom}&lt;/api:ReciveCharge>
                  &lt;api:Count>1&lt;/api:Count>
               &lt;/api:item>
            &lt;/api:FgCodeDtoList>
            &lt;api:Mdn>${MDN_No}&lt;/api:Mdn>
            &lt;api:UserId>1085053&lt;/api:UserId>
            &lt;api:OrgId>20285&lt;/api:OrgId>
            &lt;api:PaymentMethod>1&lt;/api:PaymentMethod>
            &lt;api:CardNumber>&lt;/api:CardNumber>
            &lt;api:ExpireDate>&lt;/api:ExpireDate>
            &lt;api:BankCode>&lt;/api:BankCode>
            &lt;api:Comments>&lt;/api:Comments>
            &lt;api:Remarks>&lt;/api:Remarks>
            &lt;api:StaffJobID>6&lt;/api:StaffJobID>
         &lt;/api:SubmitEvoucherRequestDto>
      &lt;/api:SubmitEvoucher>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>${GlobalVariable.g_Base_URL_API_ZSmart}/ocswebservices/services/WebServicesForApi</soapServiceEndpoint>
   <soapServiceFunction>SubmitEvoucher</soapServiceFunction>
   <socketTimeout>0</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>'6288272112680'</defaultValue>
      <description></description>
      <id>72c1c6e4-8cba-4e9e-ad6f-09a97426e522</id>
      <masked>false</masked>
      <name>MDN_No</name>
   </variables>
   <variables>
      <defaultValue>'100000'</defaultValue>
      <description></description>
      <id>8f440355-05f1-40e1-9078-a8854cf7522d</id>
      <masked>false</masked>
      <name>Denom</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress>${GlobalVariable.g_Base_URL_API_ZSmart}/ocswebservices/services/WebServicesForApi?wsdl</wsdlAddress>
</WebServiceRequestEntity>
