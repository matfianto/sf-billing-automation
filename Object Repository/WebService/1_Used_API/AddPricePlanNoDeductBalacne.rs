<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>AddPricePlanNoDeductBalacne</name>
   <tag></tag>
   <elementGuidId>7590a6dd-21ae-4e86-9d4b-a4eedd7a26f3</elementGuidId>
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
      <value>AddPricePlanNoDeductBalacne</value>
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
      &lt;api:AddPricePlanNoDeductBalacne>
         &lt;api:AddPricePlanNoDeductBalacneRequestDto>
            &lt;api:MDN>${MDN_No}&lt;/api:MDN>
            &lt;api:PricePlanCode>${PricePlanCode}&lt;/api:PricePlanCode>
            &lt;api:PaymentMethod>${PaymentMethod}&lt;/api:PaymentMethod>
            &lt;api:CardNumber>${CardNumber}&lt;/api:CardNumber>
            &lt;api:ExpireDate>${ExpireDate}&lt;/api:ExpireDate>
            &lt;api:BankCode>${BankCode}&lt;/api:BankCode>
            &lt;api:Comments>${Comments}&lt;/api:Comments>
            &lt;api:Remarks>${Remarks}&lt;/api:Remarks>
            &lt;api:UserId>${UserId}&lt;/api:UserId>
            &lt;api:OrgId>${OrgId}&lt;/api:OrgId>
            &lt;api:StaffJobID>${StaffJobID}&lt;/api:StaffJobID>
            &lt;api:AREA_ID>${AREA_ID}&lt;/api:AREA_ID>
         &lt;/api:AddPricePlanNoDeductBalacneRequestDto>
      &lt;/api:AddPricePlanNoDeductBalacne>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>${GlobalVariable.g_Base_URL_API_ZSmart}/ocswebservices/services/WebServicesForApi</soapServiceEndpoint>
   <soapServiceFunction>AddPricePlanNoDeductBalacne</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>'6288295290254'</defaultValue>
      <description></description>
      <id>a2547427-628a-49b8-a5a2-f5421b3c64bf</id>
      <masked>false</masked>
      <name>MDN_No</name>
   </variables>
   <variables>
      <defaultValue>'VOL100RB'</defaultValue>
      <description></description>
      <id>c1592f2b-1df8-4812-89e0-fbccd66e5f3a</id>
      <masked>false</masked>
      <name>PricePlanCode</name>
   </variables>
   <variables>
      <defaultValue>'1'</defaultValue>
      <description>1-Cash, 2-Credit Card, 8-DebitCard, 21-E-money</description>
      <id>91c9b1b1-29bc-4c25-867f-b14565551165</id>
      <masked>false</masked>
      <name>PaymentMethod</name>
   </variables>
   <variables>
      <defaultValue>'123456789023456'</defaultValue>
      <description></description>
      <id>6f43cc31-1bba-4371-9864-decbdccb32ff</id>
      <masked>false</masked>
      <name>CardNumber</name>
   </variables>
   <variables>
      <defaultValue>'25/05/2022'</defaultValue>
      <description></description>
      <id>f6ace464-0612-40f8-8296-f29fa8e21447</id>
      <masked>false</masked>
      <name>ExpireDate</name>
   </variables>
   <variables>
      <defaultValue>'14'</defaultValue>
      <description></description>
      <id>3662cc9c-0954-4cf0-8de8-f28e59eb9179</id>
      <masked>false</masked>
      <name>BankCode</name>
   </variables>
   <variables>
      <defaultValue>'QA Automation Test'</defaultValue>
      <description></description>
      <id>9ec42fdc-1187-4846-9bbc-693912de1adb</id>
      <masked>false</masked>
      <name>Comments</name>
   </variables>
   <variables>
      <defaultValue>'QA Automation Test'</defaultValue>
      <description></description>
      <id>f21a19e7-c092-4c06-957b-3b00887ac998</id>
      <masked>false</masked>
      <name>Remarks</name>
   </variables>
   <variables>
      <defaultValue>'996380'</defaultValue>
      <description></description>
      <id>646102b2-b885-4950-86da-b0c2b2c67a77</id>
      <masked>false</masked>
      <name>UserId</name>
   </variables>
   <variables>
      <defaultValue>'2'</defaultValue>
      <description></description>
      <id>b52c9bc9-2333-48f4-8e37-e8c6a5053385</id>
      <masked>false</masked>
      <name>OrgId</name>
   </variables>
   <variables>
      <defaultValue>'2667036'</defaultValue>
      <description></description>
      <id>3eccde47-a76c-41cd-9a23-96ca6f49feed</id>
      <masked>false</masked>
      <name>StaffJobID</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>f4afb338-500a-4dfa-bc88-efe96a14a7d1</id>
      <masked>false</masked>
      <name>AREA_ID</name>
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
