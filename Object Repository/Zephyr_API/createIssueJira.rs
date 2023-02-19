<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>createIssueJira</name>
   <tag></tag>
   <elementGuidId>0b367da3-a0e8-47f1-8996-254a280a79d7</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n    \&quot;fields\&quot;: {\n        \&quot;project\&quot;: {\n            \&quot;key\&quot;: \&quot;${project}\&quot;\n        },\n        \&quot;priority\&quot;: {\n            \&quot;name\&quot;: \&quot;${priority}\&quot;\n        },\n        \&quot;summary\&quot;: \&quot;${summary}\&quot;,\n      \t\&quot;description\&quot;: \&quot;${description}\&quot;,\n        \&quot;issuetype\&quot;: {\n            \&quot;name\&quot;: \&quot;${issuetype}\&quot;\n        },\n        \&quot;assignee\&quot; :{\n            \&quot;name\&quot; : \&quot;${assignee}\&quot;\n        },\n        \&quot;labels\&quot;: [\n            \&quot;${label}\&quot;,\&quot;AutomatedTestCase\&quot;\n        ]\n    }\n}&quot;,
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
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${GlobalVariable.JiraURL}/rest/api/2/issue/</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>'QKB'</defaultValue>
      <description></description>
      <id>afc99aaf-7583-4a33-812a-6a36b5e3a2b1</id>
      <masked>false</masked>
      <name>project</name>
   </variables>
   <variables>
      <defaultValue>'Medium'</defaultValue>
      <description></description>
      <id>09837f87-fea4-4871-afed-fb5020c930e5</id>
      <masked>false</masked>
      <name>priority</name>
   </variables>
   <variables>
      <defaultValue>'[TCS] - [BO-16000] - Topup with denom Rp. 30.000'</defaultValue>
      <description></description>
      <id>636513c0-6cc4-4890-b17f-50e462d82eae</id>
      <masked>false</masked>
      <name>summary</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>5c4aea42-9dc1-4bf1-be57-899a9e2cc104</id>
      <masked>false</masked>
      <name>description</name>
   </variables>
   <variables>
      <defaultValue>'Test'</defaultValue>
      <description></description>
      <id>69405f8e-dda7-43e7-b2a7-3aad62ef445a</id>
      <masked>false</masked>
      <name>issuetype</name>
   </variables>
   <variables>
      <defaultValue>'galih.rakasiwa'</defaultValue>
      <description></description>
      <id>5184fb46-3a2d-44f2-9731-04ae9aad5dd5</id>
      <masked>false</masked>
      <name>assignee</name>
   </variables>
   <variables>
      <defaultValue>'development'</defaultValue>
      <description></description>
      <id>20aa18a4-6e3a-4783-929a-94bcf6a93e10</id>
      <masked>false</masked>
      <name>label</name>
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
