<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>QueryCustomerInfo</name>
   <tag></tag>
   <elementGuidId>5085c4ca-eb10-495d-8ae4-6d236c119176</elementGuidId>
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
      <value>QueryCustomerInfo</value>
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
      &lt;api:QueryCustomerInfo>
         &lt;api:QueryCustomerInfoRequestDto>
            &lt;api:MDN>6288295293101&lt;/api:MDN>
            &lt;api:CustomerName>sonoras imperio&lt;/api:CustomerName>
            &lt;api:ICCID>quae divum incedo&lt;/api:ICCID>
            &lt;api:CustomerId>verrantque per auras&lt;/api:CustomerId>
            &lt;api:ESN>per auras&lt;/api:ESN>
            &lt;api:DocType>circum claustra&lt;/api:DocType>
            &lt;api:DocNumber>nimborum in&lt;/api:DocNumber>
            &lt;api:AccountNumber>foedere certo&lt;/api:AccountNumber>
            &lt;api:BankAccountNumber>profundum quippe ferant&lt;/api:BankAccountNumber>
            &lt;api:BillNumber>et carcere&lt;/api:BillNumber>
            &lt;api:TerminationFlag>iovis rapidum iaculata&lt;/api:TerminationFlag>
         &lt;/api:QueryCustomerInfoRequestDto>
      &lt;/api:QueryCustomerInfo>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi</soapServiceEndpoint>
   <soapServiceFunction>QueryCustomerInfo</soapServiceFunction>
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
   <wsdlAddress>http://10.17.85.25:8093/ocswebservices/services/WebServicesForApi?wsdl</wsdlAddress>
</WebServiceRequestEntity>
