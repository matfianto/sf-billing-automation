<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>deleteFolderCycle</name>
   <tag></tag>
   <elementGuidId>8b97a371-6347-48d2-a105-259f24ce4fab</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent></httpBodyContent>
   <httpBodyType></httpBodyType>
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
   <restRequestMethod>DELETE</restRequestMethod>
   <restUrl>${GlobalVariable.JiraURL}/rest/zapi/latest/folder/${folderID}?projectId=${projectID}&amp;versionId=${versionID}&amp;cycleId=${cycle_id}</restUrl>
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
      <id>08bc2701-fa4e-4206-86b2-de54ace79575</id>
      <masked>false</masked>
      <name>projectID</name>
   </variables>
   <variables>
      <defaultValue>'-1'</defaultValue>
      <description></description>
      <id>ef6d0683-ff45-4679-8bf8-26d5d9fdffa0</id>
      <masked>false</masked>
      <name>versionID</name>
   </variables>
   <variables>
      <defaultValue>'9'</defaultValue>
      <description></description>
      <id>e3bf1ba7-9a86-4e1a-8885-1583e9dd0357</id>
      <masked>false</masked>
      <name>cycle_id</name>
   </variables>
   <variables>
      <defaultValue>'7'</defaultValue>
      <description></description>
      <id>1fc5495b-df17-45a4-8a08-d31001e09b86</id>
      <masked>false</masked>
      <name>folderID</name>
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
