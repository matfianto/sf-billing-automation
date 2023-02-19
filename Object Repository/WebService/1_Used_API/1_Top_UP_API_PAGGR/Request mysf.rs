<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Request mysf</name>
   <tag></tag>
   <elementGuidId>a23a77d1-a045-43b7-82d5-0f0a323722a9</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n    \&quot;request\&quot;: {\n        \&quot;store\&quot;: {\n            \&quot;code\&quot;: \&quot;MYSF\&quot;,\n            \&quot;terminalId\&quot;: \&quot;T001\&quot;,\n            \&quot;confirmAction\&quot;: \&quot;deeplink\&quot;\n        },\n        \&quot;order\&quot;: {\n            \&quot;title\&quot;: \&quot;TestingMandiri602\&quot;,\n            \&quot;customerId\&quot;: \&quot;${MDN_No}\&quot;,\n            \&quot;totalPrice\&quot;: {\n                \&quot;currency\&quot;: \&quot;IDR\&quot;,\n                \&quot;value\&quot;: ${amount}\n            },\n            \&quot;goods\&quot;: [\n                {\n                    \&quot;code\&quot;: \&quot;${code}\&quot;,\n                    \&quot;price\&quot;: {\n                        \&quot;currency\&quot;: \&quot;IDR\&quot;,\n                        \&quot;value\&quot;: ${amount}\n                    },\n                    \&quot;qty\&quot;: 1\n                }\n            ]\n        },\n        \&quot;payment\&quot;: {\n            \&quot;method\&quot;: \&quot;MANDIRIVA\&quot;,\n            \&quot;reference\&quot;: \&quot;testingmandirian610\&quot;,\n            \&quot;account\&quot;: {\n                \&quot;id\&quot;: \&quot;${MDN_No}\&quot;\n            },\n            \&quot;resource\&quot;: [\n                {\n                    \&quot;currency\&quot;: \&quot;IDR\&quot;,\n                    \&quot;value\&quot;: ${amount}\n                }\n            ]\n        }\n    },\n    \&quot;signature\&quot;: \&quot;UyxOfOYCEwipxVsm2L/mKTtrVUYlZvTtzqXTfpJNuSZX53z9Ro2C+J9VrZQp07RSFXVxnN5jXErJmNEjgWSlCM6C9VaD2HFJn9CL7BqhbaPNbXWUL5LeY4LrnvedmUGT+DfuZjARyh7APE2Iou/BG9uQWFqjirvkWVmqVpu5sBUVjg7jT3I4fgUoM3Xpk0+TvRqJszK9T7DYdRCXux4laxikgtJiBDDYRo71fvGFxeg2w61zA9lnODcqKxGpLusyJmv1X2Hy5KL60z81unFyrmXGEi8lL057bnkMwZI3/gYWIiGoltiurceQTvDdokWBSgfvga03F5+lViMbTOqfcIyiKESMKAStakx1C5KxtJ/9ZL15WDafYMxqHkwRamPDZnaq3GlyfEdKp0mXTi78d56uRfAPigU1Ws+l9kL3VgNYGOJ714gMv72kmol5FUS2uBC4ptzeNQlLAHxccMW0qRtN0sDKchP4HX4zTCG69mRG9wXffKTRU92xNa7XiRCw8BbWUIYVCc3wTwtfJPVjH6gn1cc7dB4bmdG9/jpB3YAo2YXqcn1slnFw9cjtmqrjmwqvWN8hGk7NIxtOSQRs/KPpIfZeGOhz7b6bCNWMQVo9Qz19AUChi5fa00QcJOJdLSMamTi9zwQaiS/n1wRvdZwZzPEsJq1qesdWmPevGms\u003d\&quot;\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>X-Version</name>
      <type>Main</type>
      <value>MYSF-1.0</value>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>X-User-Agent</name>
      <type>Main</type>
      <value>Android/MySFv7.0</value>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>X-Origin-Host</name>
      <type>Main</type>
      <value>pay-aggr-mysf</value>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>X-Msg-ID</name>
      <type>Main</type>
      <value>7a48779a-1adc-4401-90b6-3cd5b0acba7b</value>
   </httpHeaderProperties>
   <katalonVersion>7.7.2</katalonVersion>
   <maxResponseSize>0</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${GlobalVariable.g_Base_URL_PAGGR}/payment-aggr/mysf/payment</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>0</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>'6288295293191'</defaultValue>
      <description></description>
      <id>6be55ff0-29da-42c9-84c7-71ec3109e614</id>
      <masked>false</masked>
      <name>MDN_No</name>
   </variables>
   <variables>
      <defaultValue>'100000'</defaultValue>
      <description></description>
      <id>61cfbce0-0595-4b31-8366-8659a66633f1</id>
      <masked>false</masked>
      <name>amount</name>
   </variables>
   <variables>
      <defaultValue>'VOL100RB'</defaultValue>
      <description></description>
      <id>cb00bcd8-21ff-46c6-a42e-87d9137c8cd9</id>
      <masked>false</masked>
      <name>code</name>
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
