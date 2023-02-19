<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>VA Settle</name>
   <tag></tag>
   <elementGuidId>d21d3f81-a72e-4375-8bb7-55257856fb5a</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;\u003cSOAP-ENV:Envelope xmlns:SOAP-ENV\u003d\&quot;http://schemas.xmlsoap.org/soap/envelope/\&quot; xmlns:SOAP-ENC\u003d\&quot;http://schemas.xmlsoap.org/soap/encoding/\&quot; xmlns:xsi\u003d\&quot;http://www.w3.org/2001/XMLSchema-instance\&quot; xmlns:xsd\u003d\&quot;http://www.w3.org/2001/XMLSchema\&quot;\u003e\n    \u003cSOAP-ENV:Body\u003e\n        \u003cm:payment xmlns:m\u003d\&quot;bankmandiri.h2h.billpayment.ws\&quot;\u003e\n            \u003cm:request\u003e\n                \u003cm:language\u003e\u003c/m:language\u003e\n                \u003cm:trxDateTime\u003e\u003c/m:trxDateTime\u003e\n                \u003cm:transmissionDateTime\u003e\u003c/m:transmissionDateTime\u003e\n                \u003cm:companyCode\u003e10001\u003c/m:companyCode\u003e\n                \u003cm:channelID\u003e\u003c/m:channelID\u003e\n                \u003cm:billKey1\u003e${walletID}\u003c/m:billKey1\u003e\n                \u003cm:billKey2\u003e\u003c/m:billKey2\u003e\n                \u003cm:billKey3\u003e\u003c/m:billKey3\u003e\n                \u003cm:paidBills\u003e\n                    \u003cm:string\u003e01\u003c/m:string\u003e\n                \u003c/m:paidBills\u003e\n                \u003cm:paymentAmount\u003e${amount}\u003c/m:paymentAmount\u003e\n                \u003cm:currency\u003e360\u003c/m:currency\u003e\n                \u003cm:transactionID\u003e\u003c/m:transactionID\u003e\n                \u003cm:reference1\u003e\u003c/m:reference1\u003e\n                \u003cm:reference2\u003e\u003c/m:reference2\u003e\n                \u003cm:reference3\u003e\u003c/m:reference3\u003e\n            \u003c/m:request\u003e\n        \u003c/m:payment\u003e\n    \u003c/SOAP-ENV:Body\u003e\n\u003c/SOAP-ENV:Envelope\u003e&quot;,
  &quot;contentType&quot;: &quot;application/xml&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/xml</value>
   </httpHeaderProperties>
   <katalonVersion>7.7.2</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${GlobalVariable.g_Base_URL_PAGGR}/payment-gw/mandiriva/settle</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>'50000'</defaultValue>
      <description></description>
      <id>a9b28aeb-ee2b-4bb7-b65e-57c1686d9fd1</id>
      <masked>false</masked>
      <name>amount</name>
   </variables>
   <variables>
      <defaultValue>'8978800000011378'</defaultValue>
      <description></description>
      <id>0f56163e-0e11-4efe-9622-a55bde7f7faa</id>
      <masked>false</masked>
      <name>walletID</name>
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
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
