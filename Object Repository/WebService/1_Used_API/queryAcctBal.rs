<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>queryAcctBal</name>
   <tag></tag>
   <elementGuidId>3448c401-30ca-436b-ab17-50cff31174f5</elementGuidId>
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
      &lt;ws:queryAcctBal>
         &lt;ws:MSISDN>${MDN_No}&lt;/ws:MSISDN>
         &lt;ws:AcctNbr>${Acct_No}&lt;/ws:AcctNbr>
      &lt;/ws:queryAcctBal>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>${GlobalVariable.g_Base_URL_API_ZSmart}/ocswebservices/services/WebServices</soapServiceEndpoint>
   <soapServiceFunction>queryAcctBal</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>'6288295293101'</defaultValue>
      <description></description>
      <id>325a4c0c-23fa-494a-9934-6631bfd75215</id>
      <masked>false</masked>
      <name>MDN_No</name>
   </variables>
   <variables>
      <defaultValue>'665997512'</defaultValue>
      <description></description>
      <id>46f887c9-f7a3-4e73-829c-7d4d42d522b3</id>
      <masked>false</masked>
      <name>Acct_No</name>
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
