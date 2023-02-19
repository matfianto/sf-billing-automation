<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>createTestStep</name>
   <tag></tag>
   <elementGuidId>2761ea7c-1fdb-445c-be36-cc48d148b137</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;step\&quot;: \&quot;${step}\&quot;,\n  \&quot;data\&quot;: \&quot;${data}\&quot;,\n  \&quot;result\&quot;: \&quot;${result}\&quot;\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>${GlobalVariable.ZephyrToken}</value>
   </httpHeaderProperties>
   <katalonVersion>8.0.1</katalonVersion>
   <maxResponseSize>0</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${GlobalVariable.JiraURL}/rest/zapi/latest/teststep/${issueID}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>0</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>'10160'</defaultValue>
      <description></description>
      <id>c75e765d-51ee-4d32-bafd-74a859a546fd</id>
      <masked>false</masked>
      <name>issueID</name>
   </variables>
   <variables>
      <defaultValue>'Add IPP Volume 30GB'</defaultValue>
      <description></description>
      <id>19ea2fc6-0d15-4441-bb4a-e3c47bad4dcd</id>
      <masked>false</masked>
      <name>step</name>
   </variables>
   <variables>
      <defaultValue>'MDN 0888xxxx'</defaultValue>
      <description></description>
      <id>d97e7268-8e21-48de-aabe-3e0868184262</id>
      <masked>false</masked>
      <name>data</name>
   </variables>
   <variables>
      <defaultValue>'# Success Add IPP *Genflix Combo 28D*\\\\n# Get Benefit quota data *5 GB* use partition *SF Bonus Video Streaming (N) (kB)* valid for *28 days 00:00* since added date.yncall'</defaultValue>
      <description></description>
      <id>1cb2a50c-7274-49bb-834c-fbc45e7b6604</id>
      <masked>false</masked>
      <name>result</name>
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
