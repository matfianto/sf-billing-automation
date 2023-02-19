<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>registerInitialSubs</name>
   <tag></tag>
   <elementGuidId>0ad556f0-3867-4f17-ac67-fc3be76e27c1</elementGuidId>
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
         &lt;ws:msisdn>6288272112188&lt;/ws:msisdn>
         &lt;ws:CustomerName>Bangkit Praja&lt;/ws:CustomerName>
         &lt;ws:DocType>2 KTP&lt;/ws:DocType>
         &lt;ws:DocNumber>1231123123&lt;/ws:DocNumber>
         &lt;ws:DocExpiryDate>06/06/2025&lt;/ws:DocExpiryDate>
         &lt;ws:DocAddress>Jalan BSD&lt;/ws:DocAddress>
         &lt;ws:Gender>0&lt;/ws:Gender>
         &lt;ws:Birthday>06/06/1990&lt;/ws:Birthday>
         &lt;ws:BirthPlace>BSD Dong&lt;/ws:BirthPlace>
         &lt;ws:MotherMaidenName>Ibu Ibu&lt;/ws:MotherMaidenName>
         &lt;ws:AlternateContact>Sapa AJa Boleh&lt;/ws:AlternateContact>
         &lt;ws:EmailAddress>Bangkit@Test.com&lt;/ws:EmailAddress>
      &lt;/ws:registerInitialSubs>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>http://10.17.85.25:8093/ocswebservices/services/WebServices</soapServiceEndpoint>
   <soapServiceFunction>registerInitialSubs</soapServiceFunction>
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
   <wsdlAddress>http://10.17.85.25:8093/ocswebservices/services/WebServices?wsdl</wsdlAddress>
</WebServiceRequestEntity>
