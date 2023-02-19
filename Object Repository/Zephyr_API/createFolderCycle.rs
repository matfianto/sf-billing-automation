<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>createFolderCycle</name>
   <tag></tag>
   <elementGuidId>702f190f-93de-4920-a78d-dbedf95e5e85</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;cycleId\&quot;: ${cycle_id},\n  \&quot;name\&quot;: \&quot;${folderName}\&quot;,\n  \&quot;description\&quot;: \&quot;\&quot;,\n  \&quot;projectId\&quot;: ${projectID},\n  \&quot;versionId\&quot;: ${versionID}\n}&quot;,
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
   <restUrl>${GlobalVariable.JiraURL}/rest/zapi/latest/folder/create</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>0</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>'10009'</defaultValue>
      <description></description>
      <id>c75e765d-51ee-4d32-bafd-74a859a546fd</id>
      <masked>false</masked>
      <name>projectID</name>
   </variables>
   <variables>
      <defaultValue>'Prepaid'</defaultValue>
      <description></description>
      <id>19ea2fc6-0d15-4441-bb4a-e3c47bad4dcd</id>
      <masked>false</masked>
      <name>folderName</name>
   </variables>
   <variables>
      <defaultValue>'9'</defaultValue>
      <description></description>
      <id>d97e7268-8e21-48de-aabe-3e0868184262</id>
      <masked>false</masked>
      <name>cycle_id</name>
   </variables>
   <variables>
      <defaultValue>'-1'</defaultValue>
      <description></description>
      <id>1cb2a50c-7274-49bb-834c-fbc45e7b6604</id>
      <masked>false</masked>
      <name>versionID</name>
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
