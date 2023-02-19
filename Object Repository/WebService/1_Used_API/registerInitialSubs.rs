<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>registerInitialSubs</name>
   <tag></tag>
   <elementGuidId>6a997595-85c6-4db3-93ed-bed4dd5b4104</elementGuidId>
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
   <soapBody>&lt;soapenv:Envelope xmlns:soapenv=&quot;http://schemas.xmlsoap.org/soap/envelope/&quot; xmlns:ws=&quot;http://ws.bss.zsmart.ztesoft.com&quot;>
   &lt;soapenv:Header/>
   &lt;soapenv:Body>
      &lt;ws:registerInitialSubs>
         &lt;ws:msisdn>${MDN_No}&lt;/ws:msisdn>
         &lt;ws:CustomerName>QA Automation&lt;/ws:CustomerName>
         &lt;ws:DocType>2&lt;/ws:DocType>
         &lt;ws:DocNumber>1231123123&lt;/ws:DocNumber>
         &lt;ws:DocExpiryDate>06/06/2025 00:00:00&lt;/ws:DocExpiryDate>
         &lt;ws:DocAddress>Jalan BSD&lt;/ws:DocAddress>
         &lt;ws:Gender>0&lt;/ws:Gender>
         &lt;ws:Birthday>02/01/1990 00:00:00&lt;/ws:Birthday>
         &lt;ws:BirthPlace>BSD&lt;/ws:BirthPlace>
         &lt;ws:MotherMaidenName>Ibu&lt;/ws:MotherMaidenName>
         &lt;ws:AlternateContact>Sapa AJa Boleh&lt;/ws:AlternateContact>
         &lt;ws:EmailAddress>QA@Test.com&lt;/ws:EmailAddress>
      &lt;/ws:registerInitialSubs>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>${GlobalVariable.g_Base_URL_API_ZSmart}/ocswebservices/services/WebServices</soapServiceEndpoint>
   <soapServiceFunction>registerInitialSubs</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>'6288295291268'</defaultValue>
      <description></description>
      <id>12a489b5-ded2-4de4-8496-14e673b960a0</id>
      <masked>false</masked>
      <name>MDN_No</name>
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
   <wsdlAddress>${GlobalVariable.g_Base_URL_API_ZSmart}/ocswebservices/services/WebServices?wsdl</wsdlAddress>
</WebServiceRequestEntity>
