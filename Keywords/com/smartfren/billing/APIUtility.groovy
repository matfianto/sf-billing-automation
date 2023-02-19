package com.smartfren.billing

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import groovy.json.JsonSlurper
import com.kms.katalon.core.util.KeywordUtil
import groovy.xml.*
import com.smartfren.mfino.MFino

public class APIUtility {
	@Keyword
	def getSubInfoDetailPre (String MDN_No){
		ResponseObject objectResp = WS.sendRequest(findTestObject("WebService/1_Used_API/getSubsInfo",[('MDN_No') : MDN_No ]))

		//KeywordUtil.logInfo(objectResp.getResponseText())
		def resp = new XmlParser().parseText(objectResp.getResponseText())

		String MDN = resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.MDN.text()
		String generalInfo = '\nMDN : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.MDN.text() +
				'\nState : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.state.text() +
				'\nICCID : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.ICCID.text() +
				'\nIMSI : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.IMSI.text() +
				'\nproductName : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.productName.text() +
				'\nofferName : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.offerName.text() +
				'\ndefaultPricePlan : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.defaultPricePlan.text() +
				'\nactiveDaten : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.activeDate.text() +
				'\nactiveEndDate : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.activeEndDate.text()
		int i = 0
		String offerList = ""
		String offer

		while (resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.serviceDtoList[0].serviceDtoList[i] != null)
		{
			offer = "\neffectiveDate :" + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.serviceDtoList[0].serviceDtoList[i].effectiveDate.text() +
					"\nexpireDate :" + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.serviceDtoList[0].serviceDtoList[i].expireDate.text() +
					"\nserviceCode :" + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.serviceDtoList[0].serviceDtoList[i].serviceCode.text() +
					"\nserviceName :" + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.serviceDtoList[0].serviceDtoList[i].serviceName.text()
			offerList = offerList+'\n------------------------\n'+offer
			i++
		}
		/*
		 //KeywordUtil.logInfo('offerList :' + offerList)
		 String balanceList = ""
		 String balance
		 String bal
		 i=0
		 while (resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.balanceDtoList[0].balanceDtoList[i] != null)
		 {
		 bal = resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.balanceDtoList[0].balanceDtoList[i].balance.text().toString()
		 if (bal != '0' && resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.balanceDtoList[0].balanceDtoList[i].acctResName.text().toString().contains('(Rp)'))
		 {
		 bal = bal.substring(0, bal.length() - 2)
		 }
		 balance = "acctResID :" + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.balanceDtoList[0].balanceDtoList[i].acctResID.text() +
		 "\nacctResName :" + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.balanceDtoList[0].balanceDtoList[i].acctResName.text() +
		 "\nbalance :" + bal +
		 "\neffDate :" + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.balanceDtoList[0].balanceDtoList[i].effDate.text() +
		 "\nexpDate :" + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.balanceDtoList[0].balanceDtoList[i].expDate.text()
		 balanceList = balanceList+'\n------------------------\n'+balance
		 i++
		 } */
		KeywordUtil.logInfo('===============  generalInfo  ===============' + generalInfo +
				'\n==============  offerList  ================' + offerList /*+
		 '\n=============  balanceList  ===============' + balanceList*/)

		WS.delay(10)
	}
	@Keyword
	def getSubInfoDetailPost (String MDN_No){
		ResponseObject objectResp = WS.sendRequest(findTestObject("WebService/1_Used_API/getSubsInfo",[('MDN_No') : MDN_No ]))
		//KeywordUtil.logInfo(objectResp.getResponseText())
		def resp = new XmlParser().parseText(objectResp.getResponseText())
		String MDN = resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.MDN.text()
		String generalInfo = '\nMDN : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.MDN.text() +
				'\nState : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.state.text() +
				'\nICCID : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.ICCID.text() +
				'\nIMSI : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.IMSI.text() +
				'\nproductName : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.productName.text() +
				'\nofferName : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.offerName.text() +
				'\ndefaultPricePlan : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.defaultPricePlan.text() +
				'\nactiveDaten : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.activeDate.text() +
				'\nactiveEndDate : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.activeEndDate.text()

		int i = 0
		String offerList = ""
		String offer
		while (resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.serviceDtoList[0].serviceDtoList[i] != null) {
			offer = "effectiveDate :" + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.serviceDtoList[0].serviceDtoList[i].effectiveDate.text() +
					"\nexpireDate :" + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.serviceDtoList[0].serviceDtoList[i].expireDate.text() +
					"\nserviceCode :" + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.serviceDtoList[0].serviceDtoList[i].serviceCode.text() +
					"\nserviceName :" + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.serviceDtoList[0].serviceDtoList[i].serviceName.text()
			offerList = offerList+'\n------------------------\n'+offer
			i++
		}
		//KeywordUtil.logInfo('offerList :' + offerList)
		/*
		 String balanceList = ""
		 String balance
		 String bal
		 i=0
		 while (resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.balanceDtoList[0].balanceDtoList[i] != null)
		 {
		 bal = resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.balanceDtoList[0].balanceDtoList[i].balance.text().toString()
		 if (bal != '0' && resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.balanceDtoList[0].balanceDtoList[i].acctResName.text().toString().contains('(Rp)')){
		 bal = bal.substring(0, bal.length() - 2)
		 }
		 balance = "acctResID :" + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.balanceDtoList[0].balanceDtoList[i].acctResID.text() +
		 "\nacctResName :" + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.balanceDtoList[0].balanceDtoList[i].acctResName.text() +
		 "\nbalance :" + bal +
		 "\neffDate :" + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.balanceDtoList[0].balanceDtoList[i].effDate.text() +
		 "\nexpDate :" + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.balanceDtoList[0].balanceDtoList[i].expDate.text()
		 balanceList = balanceList+'\n------------------------\n'+balance
		 i++
		 }*/
		KeywordUtil.logInfo('===============  generalInfo  ===============' + generalInfo +
				'\n==============  offerList  ================' + offerList)

		WS.delay(10)
	}
	@Keyword
	def getPartisiDetail (String MDN_No){
		ResponseObject objectResp = WS.sendRequest(findTestObject("WebService/1_Used_API/getSubsInfo",[('MDN_No') : MDN_No ]))
		//KeywordUtil.logInfo(objectResp.getResponseText())
		def resp = new XmlParser().parseText(objectResp.getResponseText())
		String balanceList = ""
		String balance
		String bal
		long bal_fix
		String bal_type
		int i=0
		while (resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.balanceDtoList[0].balanceDtoList[i] != null)
		{
			bal = resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.balanceDtoList[0].balanceDtoList[i].balance.text().toString()
			bal_type = resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.balanceDtoList[0].balanceDtoList[i].balType.text().toString()
			if (bal != '0' && bal_type != '4' && bal.length()>2){bal = bal.substring(0, bal.length() - 2)}
			bal_fix = bal.toLong() * -1
			balance = "acctResID :" + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.balanceDtoList[0].balanceDtoList[i].acctResID.text() +
					"\nacctResName :" + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.balanceDtoList[0].balanceDtoList[i].acctResName.text() +
					"\nbalance :" + bal_fix.toString() +
					"\neffDate :" + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.balanceDtoList[0].balanceDtoList[i].effDate.text() +
					"\nexpDate :" + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.balanceDtoList[0].balanceDtoList[i].expDate.text()
			balanceList = balanceList+'\n------------------------\n'+balance
			i++
		}
		KeywordUtil.logInfo('\n=============  balanceList  ===============' + balanceList)

		WS.delay(10)
	}

	@Keyword
	def removeIPP (String MDN_No, String ServiceCode_No){
		ResponseObject objectResp = WS.sendRequest(findTestObject("WebService/1_Used_API/RemoveService",[('MDN_No') : MDN_No, ('ServiceCode_No') : ServiceCode_No]))
		def resp = new XmlParser().parseText(objectResp.getResponseText())
		String removeIPPstatus = resp.'soapenv:Body'.RemoveServiceResponse.RemoveServiceReturn.resultCode.text()
		KeywordUtil.logInfo('removeIPPstatus : '+removeIPPstatus)
		if (removeIPPstatus=="1"){
			KeywordUtil.logInfo('Remove IPP : '+ServiceCode_No+' Success' +
					"\ntranscationID :" + resp.'soapenv:Body'.RemoveServiceResponse.RemoveServiceReturn.transcationID.text() +
					"\nserviceName :" + resp.'soapenv:Body'.RemoveServiceResponse.RemoveServiceReturn.serviceName.text() +
					"\nresultCode :" + resp.'soapenv:Body'.RemoveServiceResponse.RemoveServiceReturn.resultCode.text() +
					"\nresultDesc :" + resp.'soapenv:Body'.RemoveServiceResponse.RemoveServiceReturn.resultDesc.text() +
					"\neffectiveDate :" + resp.'soapenv:Body'.RemoveServiceResponse.RemoveServiceReturn.effectiveDate.text() +
					"\nexpireDate :" + resp.'soapenv:Body'.RemoveServiceResponse.RemoveServiceReturn.expireDate.text())
		}
		else if (removeIPPstatus!="1") {
			KeywordUtil.logInfo('Remove IPP : '+ServiceCode_No+' Failed' +
					"\nresultCode : " + resp.'soapenv:Body'.RemoveServiceResponse.RemoveServiceReturn.resultCode.text() +
					"\nresultDesc : " + resp.'soapenv:Body'.RemoveServiceResponse.RemoveServiceReturn.resultDesc.text())
		}

		WS.delay(10)
	}
	@Keyword
	def addIPP (String MDN_No, String ServiceCode_No){
		def dbUtility = new DBUtility()
		WS.delay(10)
		ResponseObject objectResp = WS.sendRequest(findTestObject("WebService/1_Used_API/addServcie",[('MDN_No') : MDN_No, ('ServiceCode_No') : ServiceCode_No]))
		def resp = new XmlParser().parseText(objectResp.getResponseText())
		String addIPPstatus = resp.'soapenv:Body'.addServcieResponse.addServcieReturn.returnDto.returnCode.text()
		KeywordUtil.logInfo('addIPPstatus : '+addIPPstatus)
		if (addIPPstatus==""){
			KeywordUtil.logInfo('Add IPP : '+ServiceCode_No+' Success' +
					"\ntranscationID :" + resp.'soapenv:Body'.addServcieResponse.addServcieReturn.transcationID.text() +
					"\nserviceName :" + resp.'soapenv:Body'.addServcieResponse.addServcieReturn.serviceName.text() +
					"\nresultCode :" + resp.'soapenv:Body'.addServcieResponse.addServcieReturn.returnDto.returnCode.text() +
					"\nresultDesc :" + resp.'soapenv:Body'.addServcieResponse.addServcieReturn.returnDto.returnMsg.text() +
					"\neffectiveDate :" + resp.'soapenv:Body'.addServcieResponse.addServcieReturn.effectiveDate.text() +
					"\nexpireDate :" + resp.'soapenv:Body'.addServcieResponse.addServcieReturn.expireDate.text())
			WS.delay(3)
			def checkPendingOrder = dbUtility.queryDB('BillingDB',"select ORDER_ID from subs_order where acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"' and order_state='P'")
			if (checkPendingOrder.ORDER_ID[0]!=null){
				KeywordUtil.logInfo('========WARNING======== : There is Pending Order after addIPP, automation continue with clear out pending order for now with update query. Please check manually.')
				dbUtility.executeUpdateQuery('BillingDB',"update om_order set state='E' where order_id IN (select order_id from subs_order where acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"' and order_state='P')")
			}
		}
		else if (addIPPstatus!="") {
			KeywordUtil.logInfo('Add IPP : '+ServiceCode_No+' Failed' +
					"\nresultCode : " + resp.'soapenv:Body'.addServcieResponse.addServcieReturn.returnDto.returnCode.text() +
					"\nresultDesc : " + resp.'soapenv:Body'.addServcieResponse.addServcieReturn.returnDto.returnMsg.text())
		}

		WS.delay(10)
	}
	@Keyword
	def addIPPFailStop (String MDN_No, String ServiceCode_No){
		ResponseObject objectResp = WS.sendRequest(findTestObject("WebService/1_Used_API/addServcie",[('MDN_No') : MDN_No, ('ServiceCode_No') : ServiceCode_No]))
		def resp = new XmlParser().parseText(objectResp.getResponseText())
		String addIPPstatus = resp.'soapenv:Body'.addServcieResponse.addServcieReturn.returnDto.returnCode.text()
		KeywordUtil.logInfo('addIPPstatus : '+addIPPstatus)
		if (addIPPstatus==""){
			KeywordUtil.logInfo('Add IPP : '+ServiceCode_No+' Success' +
					"\ntranscationID :" + resp.'soapenv:Body'.addServcieResponse.addServcieReturn.transcationID.text() +
					"\nserviceName :" + resp.'soapenv:Body'.addServcieResponse.addServcieReturn.serviceName.text() +
					"\nresultCode :" + resp.'soapenv:Body'.addServcieResponse.addServcieReturn.returnDto.returnCode.text() +
					"\nresultDesc :" + resp.'soapenv:Body'.addServcieResponse.addServcieReturn.returnDto.returnMsg.text() +
					"\neffectiveDate :" + resp.'soapenv:Body'.addServcieResponse.addServcieReturn.effectiveDate.text() +
					"\nexpireDate :" + resp.'soapenv:Body'.addServcieResponse.addServcieReturn.expireDate.text())
		}
		else if (addIPPstatus!="") {
			KeywordUtil.markFailedAndStop('Add IPP : '+ServiceCode_No+' Failed' +
					"\nresultCode : " + resp.'soapenv:Body'.addServcieResponse.addServcieReturn.returnDto.returnCode.text() +
					"\nresultDesc : " + resp.'soapenv:Body'.addServcieResponse.addServcieReturn.returnDto.returnMsg.text())
		}
	}
	@Keyword
	def activationMDN (String MDN_No){
		def dbUtility = new DBUtility()
		ResponseObject objectResp = WS.sendRequest(findTestObject("WebService/1_Used_API/registerInitialSubs",[('MDN_No') : MDN_No]))
		def resp = new XmlParser().parseText(objectResp.getResponseText())
		String activationStatus = resp.'soapenv:Body'.registerInitialSubsResponse.RegisterInitialSubsReturnDto.Result.text()
		//KeywordUtil.logInfo('activationStatus : '+activationStatus)
		if (activationStatus=="1"){
			KeywordUtil.logInfo('activationStatus : '+MDN_No+' Success' +
					"\nactivationStatus (1 success and 0 failed) :" + activationStatus)
			WS.delay(3)
			def checkPendingOrder = dbUtility.queryDB('BillingDB',"select ORDER_ID from subs_order where acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"' and order_state='P'")
			if (checkPendingOrder.ORDER_ID[0]!=null){
				KeywordUtil.logInfo('========WARNING======== : There is Pending Order after activation, automation continue with clear out pending order for now with update query. Please check manually.')
				dbUtility.executeUpdateQuery('BillingDB',"update om_order set state='E' where order_id IN (select order_id from subs_order where acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"' and order_state='P')")
			}
		}
		else if (activationStatus!="1") {
			KeywordUtil.logInfo('activationStatus : '+MDN_No+' Failed' +
					"\nactivationStatus (1 success and 0 failed) :" + activationStatus +
					"\nPlease check if number already active or number is correct" )
		}
		return activationStatus

		WS.delay(10)
	}
	@Keyword
	def topUp (String MDN_No, String Denom){
		ResponseObject objectResp = WS.sendRequest(findTestObject("WebService/1_Used_API/SubmitEvoucher",[('MDN_No') : MDN_No, ('Denom') : Denom]))
		def resp = new XmlParser().parseText(objectResp.getResponseText())
		String topUPMDN = resp.'soapenv:Body'.text().toString()
		//KeywordUtil.logInfo('topUPMDN : '+topUPMDN)
		if (!topUPMDN.contains("userExceptionerrorCode")){
			KeywordUtil.logInfo('Top Up : '+MDN_No+' With Denom: '+Denom+' Success' +
					"\nPaymentDate :" + resp.'soapenv:Body'.SubmitEvoucherResponse.SubmitEvoucherResponseDto.PaymentDate.text() +
					"\nTotalRealPrice :" + resp.'soapenv:Body'.SubmitEvoucherResponse.SubmitEvoucherResponseDto.TotalRealPrice.text() +
					"\nPaymentMethodName :" + resp.'soapenv:Body'.SubmitEvoucherResponse.SubmitEvoucherResponseDto.PaymentMethodName.text() +
					"\nFgName :" + resp.'soapenv:Body'.SubmitEvoucherResponse.SubmitEvoucherResponseDto.EvoucherDtoList.EvoucherDto.FgName.text() +
					"\nSubsEventName :" + resp.'soapenv:Body'.SubmitEvoucherResponse.SubmitEvoucherResponseDto.SubsEventName.text())
		}
		else if (topUPMDN.contains("userExceptionerrorCode")) {
			KeywordUtil.logInfo('Top Up : '+MDN_No+' With Denom: '+Denom+' Failed' +
					"\nfault Detail : " + resp.'soapenv:Body'.text())
		}
		WS.delay(20)
	}
	@Keyword
	def getQueryAcctBal (String MDN_No){
		def dbUtility = new DBUtility()

		def acctIDQuery = "select acct_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('acctIDQuery' + acctIDQuery)
		def acctID = dbUtility.queryDB('BillingDB',acctIDQuery )
		KeywordUtil.logInfo('acctID.acct_id[0]' + acctID.acct_id[0])

		ResponseObject objectResp = WS.sendRequest(findTestObject("WebService/1_Used_API/queryAcctBal",[('MDN_No') : MDN_No, ('Acct_ID') : acctID.acct_id[0]]))
		//KeywordUtil.logInfo(objectResp.getResponseText())
		def resp = new XmlParser().parseText(objectResp.getResponseText())
		String balanceList = ""
		String balance
		String bal
		int i=0
		while (resp.'soapenv:Body'.queryAcctBalResponse.BalDtoList[i] != null)
		{
			bal = resp.'soapenv:Body'.queryAcctBalResponse.BalDtoList[i].balance.text().toString()
			//if (bal > '0'){bal = bal.substring(0, bal.length() - 2)}
			balance = "acctResID :" + resp.'soapenv:Body'.queryAcctBalResponse.BalDtoList[i].acctResID.text() +
					"\nacctResName :" + resp.'soapenv:Body'.queryAcctBalResponse.BalDtoList[i].acctResName.text() +
					"\nbalance :" + bal +
					"\neffDate :" + resp.'soapenv:Body'.queryAcctBalResponse.BalDtoList[i].effDate.text() +
					"\nexpDate :" + resp.'soapenv:Body'.queryAcctBalResponse.BalDtoList[i].expDate.text()
			balanceList = balanceList+'\n------------------------\n'+balance
			i++
		}
		KeywordUtil.logInfo('\n=============  balanceList  ===============' + balanceList)

		WS.delay(10)
	}
	@Keyword
	def GetUserIndiPricePlanList (String MDN_No){
		ResponseObject objectResp = WS.sendRequest(findTestObject("WebService/1_Used_API/GetUserIndiPricePlanList",[('MDN_No') : MDN_No ]))

		//KeywordUtil.logInfo(objectResp.getResponseText())
		def resp = new XmlParser().parseText(objectResp.getResponseText())

		WS.delay(10)

		int i = 0
		String IPPList = ""
		String IPP

		while (resp.'soapenv:Body'.GetUserIndiPricePlanListResponse[0].GetUserIndiPricePlanListReturn[i] != null)
		{
			IPP = "PricePlanCode: " + resp.'soapenv:Body'.GetUserIndiPricePlanListResponse.GetUserIndiPricePlanListReturn[i].pricePlanCode.text() +
					"\nPricePlanName: " + resp.'soapenv:Body'.GetUserIndiPricePlanListResponse.GetUserIndiPricePlanListReturn[i].pricePlanName.text() +
					"\nEffectiveDate: " + resp.'soapenv:Body'.GetUserIndiPricePlanListResponse.GetUserIndiPricePlanListReturn[i].effDate.text() +
					"\nExpiredDate: " + resp.'soapenv:Body'.GetUserIndiPricePlanListResponse.GetUserIndiPricePlanListReturn[i].expDate.text()
			IPPList = IPPList+'\n------------------------\n'+IPP

			i++
		}
		KeywordUtil.logInfo('==============  IPP List  ================' + IPPList)

		WS.delay(10)
	}
	@Keyword
	def topUpMandiriVA (String MDN_No, String amount, String code){
		ResponseObject objectResp = WS.sendRequest(findTestObject("WebService/1_Used_API/1_Top_UP_API_PAGGR/Request mysf",[('MDN_No') : MDN_No , ('amount') : amount , ('code') : code ]))
		JsonSlurper jsonSlurper = new JsonSlurper()
		def jsonResp = jsonSlurper.parseText(objectResp.getResponseText())
		//KeywordUtil.logInfo("response.result.code " + jsonResp.response.result.code)
		if (jsonResp.response.result.code == 1 && WS.verifyResponseStatusCode(objectResp, 200)) {
			objectResp = WS.sendRequest(findTestObject("WebService/1_Used_API/1_Top_UP_API_PAGGR/VA Settle",[('amount') : amount , ('walletID') : jsonResp.response.payment.account.walletId]))
			def respXml = new XmlParser().parseText(objectResp.getResponseText())
			if (respXml.'soapenv:Body'.paymentResponse.paymentResult.status.errorCode.text() == '00' && WS.verifyResponseStatusCode(objectResp, 200)){
				WS.delay(3)
				KeywordUtil.logInfo('Top Up Mandiri VA : '+MDN_No+' With Denom: '+amount+ ' Success' +
						"\nbillInfo1 :" + respXml.'soapenv:Body'.paymentResponse.paymentResult.billInfo1.text() +
						"\nbillInfo2 :" + respXml.'soapenv:Body'.paymentResponse.paymentResult.billInfo2.text() +
						"\nbillInfo3 :" + respXml.'soapenv:Body'.paymentResponse.paymentResult.billInfo3.text())
				WS.delay(3)
			}
			else { KeywordUtil.markFailed("API top Up Mandiri VA failed on payment API")
			}
		}
		else {
			KeywordUtil.markFailed("API top Up Mandiri VA failed on settle API")
		}
	}
	@Keyword
	def rechargeMFino (String MDN, String MerchantType, String Bank_Code, String Amount_Balance, String productIndicator){
		def mfino = new MFino()
		mfino.setIP(GlobalVariable.MFinoISOServer.get('IP'))
		mfino.setPort(GlobalVariable.MFinoISOServer.get('Port'))
		String[] result = mfino.Recharge(MDN, MerchantType, Bank_Code, Amount_Balance, productIndicator)
		KeywordUtil.logInfo('Send ISO Message : \n'+result[0])
		KeywordUtil.logInfo('Get ISO Response Message : \n'+result[1])
		int length = result[1].substring(72, 74).toInteger()
		String result_code = result[1].substring(86+length, 88+length)

		if(result_code == '00') {
			String results=result[1].substring(162+length)
			KeywordUtil.logInfo('TopUp MFino Success, Response Code : '+result_code +
					'\nMDN : '+ results.substring(3, 19) +
					'\nTransaction Amount : '+ results.substring(19, 31).toLong() +
					'\nValidity Period : '+ results.substring(31, 35)+'-'+ results.substring(35, 37)+'-'+ results.substring(37, 39) +
					'\nBill Reference : '+ results.substring(39, 51))
		}else {
			KeywordUtil.markFailed('TopUp MFino Fail, Response Code : '+result_code+'\n'+GlobalVariable.g_Test_Case_Desc.substring(10))
		}
	}
	@Keyword
	def packagePurchaseMFino (String MDN, String MerchantType, String Bank_Code, String Amount_Balance, String productIndicator, String PackageID, String AreaID){
		def mfino = new MFino()
		mfino.setIP(GlobalVariable.MFinoISOServer.get('IP'))
		mfino.setPort(GlobalVariable.MFinoISOServer.get('Port'))
		String[] result = mfino.PackagePurchase(MDN, MerchantType, Bank_Code, Amount_Balance, productIndicator,PackageID,AreaID)
		KeywordUtil.logInfo('Send ISO Message : \n'+result[0])
		KeywordUtil.logInfo('Get ISO Response Message : \n'+result[1])
		int length = result[1].substring(72, 74).toInteger()
		String result_code = result[1].substring(86+length, 88+length)
		//String result_code = result[1].substring(90, 92)
		//String results=result[1].substring(186)
		if(result_code == '00') {
			String results=result[1].substring(182+length)
			if(results.length() <= 180) {
				KeywordUtil.logInfo('Package Purchase MFino Success, Response Code : '+result_code +
						'\nMDN : '+ results.substring(4, 20) +
						'\nPackage ID : '+ results.substring(20, 32).toLong() +
						'\nPackage Code : '+ results.substring(32, 62) +
						'\nDescription : '+ results.substring(62, 112) +
						'\nEffective Date : '+ results.substring(112, 116)+'-'+results.substring(116, 118)+'-'+results.substring(118, 120)+' '+results.substring(120, 122)+':'+results.substring(122, 124)+':'+results.substring(124, 126) +
						'\nExpire Date : '+ results.substring(126, 140).toLong() +
						'\nUsername : '+ results.substring(140, 160) +
						'\nPassword : '+ results.substring(160, 180))
			}else if(AreaID =='') {
				KeywordUtil.logInfo('Package Purchase MFino Success, Response Code : '+result_code +
						'\nMDN : '+ results.substring(4, 20) +
						'\nPackage ID : '+ results.substring(20, 32).toLong() +
						'\nPackage Code : '+ results.substring(32, 62) +
						'\nDescription : '+ results.substring(62, 112) +
						'\nEffective Date : '+ results.substring(112, 116)+'-'+results.substring(116, 118)+'-'+results.substring(118, 120)+' '+results.substring(120, 122)+':'+results.substring(122, 124)+':'+results.substring(124, 126) +
						'\nExpire Date : '+ results.substring(126, 140).toLong() +
						'\nUsername : '+ results.substring(140, 160) +
						'\nPassword : '+ results.substring(160, 180))
			}else {
				KeywordUtil.logInfo('Package Purchase MFino Success, Response Code : '+result_code +
						'\nMDN : '+ results.substring(4, 20) +
						'\nPackage ID : '+ results.substring(20, 32).toLong() +
						'\nPackage Code : '+ results.substring(32, 62) +
						'\nDescription : '+ results.substring(62, 112) +
						'\nEffective Date : '+ results.substring(112, 116)+'-'+results.substring(116, 118)+'-'+results.substring(118, 120)+' '+results.substring(120, 122)+':'+results.substring(122, 124)+':'+results.substring(124, 126) +
						'\nExpire Date : '+ results.substring(126, 140).toLong() +
						'\nUsername : '+ results.substring(140, 160) +
						'\nPassword : '+ results.substring(160, 180) +
						'\nArea ID : '+ results.substring(180, 189) +
						'\nArea Name : '+ results.substring(189))
			}
		}else {
			KeywordUtil.markFailed('Package Purchase MFino Fail, Response Code : '+result_code+'\n'+GlobalVariable.g_Test_Case_Desc.substring(10))
		}
	}
	@Keyword
	def getSubInfoProductOnlyPre (String MDN_No){
		ResponseObject objectResp = WS.sendRequest(findTestObject("WebService/1_Used_API/getSubsInfo",[('MDN_No') : MDN_No ]))

		//KeywordUtil.logInfo(objectResp.getResponseText())
		def resp = new XmlParser().parseText(objectResp.getResponseText())

		String MDN = resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.MDN.text()
		String generalInfo = '\nMDN : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.MDN.text() +
				'\nState : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.state.text() +
				'\nICCID : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.ICCID.text() +
				'\nIMSI : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.IMSI.text() +
				'\nproductName : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.productName.text() +
				'\nofferName : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.offerName.text() +
				'\ndefaultPricePlan : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.defaultPricePlan.text() +
				'\nactiveDaten : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.activeDate.text() +
				'\nactiveEndDate : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.activeEndDate.text()

		KeywordUtil.logInfo('===============  generalInfo  ===============' + generalInfo)

		WS.delay(10)
	}
	@Keyword
	def getSubInfoProductOnlyPost (String MDN_No){
		//getSubsInfo
		ResponseObject objectResp = WS.sendRequest(findTestObject("WebService/1_Used_API/getSubsInfo",[('MDN_No') : MDN_No ]))
		//KeywordUtil.logInfo(objectResp.getResponseText())
		def resp = new XmlParser().parseText(objectResp.getResponseText())
		String MDN = resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.MDN.text()
		String generalInfo = '\nMDN : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.MDN.text() +
				'\nState : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.state.text() +
				'\nICCID : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.ICCID.text() +
				'\nIMSI : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.IMSI.text() +
				'\nproductName : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.productName.text() +
				'\nofferName : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.offerName.text() +
				'\ndefaultPricePlan : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.defaultPricePlan.text() +
				'\nactiveDaten : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.activeDate.text() +
				'\nactiveEndDate : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.activeEndDate.text()
		'\n'

		KeywordUtil.logInfo('===============  generalInfo  ===============' + generalInfo)

		WS.delay(10)
	}
	@Keyword
	def getCreditLimitInfoPost (String MDN_No){
		//Get Credit Limit Corporate
		ResponseObject objectRespGetSubsPostpaidInfo = WS.sendRequest(findTestObject("WebService/1_Used_API/GetSubsPostpaidInfo",[('MDN_No') : MDN_No ]))
		def respGetSubsPostpaidInfo = new XmlParser().parseText(objectRespGetSubsPostpaidInfo.getResponseText())
		String corporateName = respGetSubsPostpaidInfo.'soapenv:Body'.GetSubsPostpaidInfoResponse.GetSubsPostpaidInfoReturn.corporateName.text()

		KeywordUtil.logInfo('Corporate Name : ' + corporateName)

		//Get Credit Limit Personal
		ResponseObject objectResp = WS.sendRequest(findTestObject("WebService/1_Used_API/getSubsInfo",[('MDN_No') : MDN_No ]))
		def resp = new XmlParser().parseText(objectResp.getResponseText())

		String balanceList= ""
		String balance

		if(corporateName!=""){
			balance = '\nCorporate Name : ' + respGetSubsPostpaidInfo.'soapenv:Body'.GetSubsPostpaidInfoResponse.GetSubsPostpaidInfoReturn.corporateName.text() +
					'\nEntrusted Payment : ' + respGetSubsPostpaidInfo.'soapenv:Body'.GetSubsPostpaidInfoResponse.GetSubsPostpaidInfoReturn.entrustedPayment.text().toString() +
					'\nEntrusted Payment Limit : ' + respGetSubsPostpaidInfo.'soapenv:Body'.GetSubsPostpaidInfoResponse.GetSubsPostpaidInfoReturn.entrustedPaymentLimit.text().toString() +
					'\nOutstanding Balance : ' + respGetSubsPostpaidInfo.'soapenv:Body'.GetSubsPostpaidInfoResponse.GetSubsPostpaidInfoReturn.outstandingBalance.text().toString() +
					'\nTotal Postpaid Usage : ' + respGetSubsPostpaidInfo.'soapenv:Body'.GetSubsPostpaidInfoResponse.GetSubsPostpaidInfoReturn.totalPostpaidUsage.text().toString()
		}
		else{
			balance = '\nCurrenct Usage : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.currentUsage.text().toString() +
					'\nTotal Credit Limit : ' + resp.'soapenv:Body'.getSubsInfoResponse.getSubsInfoReturn.totalCreditLimit.text().toString()
		}
		balanceList = balanceList+'\n------------------------\n'+balance

		KeywordUtil.logInfo('\n=============  balanceList  ===============' + balanceList)

		WS.delay(10)
	}
	@Keyword
	def GetSubsInfo(String MDN) {
		ResponseObject response = WS.sendRequest(findTestObject("Object Repository/Sandbox_API/GetSubsInfo",[('MDN') : MDN ]))
		KeywordUtil.logInfo("HEADER\n"+response.getHeaderFields()+"\n\nBODY\n"+response.getResponseBodyContent())
		JsonSlurper jsonSlurper = new JsonSlurper()
		def jsonResp = jsonSlurper.parseText(response.getResponseText())
		if(response.getStatusCode()==200) {
			KeywordUtil.logInfo('=============  GetSubsInfo  ==============='+
					'\nMDN : '+jsonResp.mdn+'\ncurrentUsage : '+jsonResp.currentUsage+
					'\nproductName : '+jsonResp.productName+'\nlastPaymentDate : '+jsonResp.lastPaymentDate+
					'\ncustomerType : '+jsonResp.customerType+'\ndueDate : '+jsonResp.dueDate+
					'\nbillCycleType : '+jsonResp.billCycleType+'\namountDue : '+jsonResp.amountDue+
					'\nterminationDate : '+jsonResp.terminationDate+'\nnextState : '+jsonResp.nextState+
					'\nnextStateDate : '+jsonResp.nextStateDate+'\nPOC : '+jsonResp.POC)
		}else {
			KeywordUtil.markFailed("API GetSubsInfo Failed with error code"+response.getStatusCode())
		}
	}
	@Keyword
	def addIPPNoDeductBalance (String MDN_No, String PricePlanCode, String PaymentMethod, String BankCode){
		WS.delay(10)
		ResponseObject objectResp = WS.sendRequest(findTestObject("WebService/1_Used_API/AddPricePlanNoDeductBalacne",[('MDN_No') : MDN_No, ('PricePlanCode') : PricePlanCode, ('PaymentMethod') : PaymentMethod, ('BankCode') : BankCode]))
		def resp = new XmlParser().parseText(objectResp.getResponseText())
		String TranscationID = resp.'soapenv:Body'.AddPricePlanNoDeductBalacneResponse.AddPricePlanNoDeductBalacneResponseDto.TranscationID.text()
		KeywordUtil.logInfo('TranscationID : '+TranscationID)
		if (TranscationID!=""){
			KeywordUtil.logInfo('Add IPP : '+PricePlanCode+' Success' +
					"\nTranscationID :" + resp.'soapenv:Body'.AddPricePlanNoDeductBalacneResponse.AddPricePlanNoDeductBalacneResponseDto.TranscationID.text() +
					"\nPricePlanName :" + resp.'soapenv:Body'.AddPricePlanNoDeductBalacneResponse.AddPricePlanNoDeductBalacneResponseDto.PricePlanName.text() +
					"\nEffectiveDate :" + resp.'soapenv:Body'.AddPricePlanNoDeductBalacneResponse.AddPricePlanNoDeductBalacneResponseDto.EffectiveDate.text() +
					"\nExpireDate :" + resp.'soapenv:Body'.AddPricePlanNoDeductBalacneResponse.AddPricePlanNoDeductBalacneResponseDto.ExpireDate.text() +
					"\nPaymentMethodName :" + resp.'soapenv:Body'.AddPricePlanNoDeductBalacneResponse.AddPricePlanNoDeductBalacneResponseDto.PaymentMethodName.text())
		}
		WS.delay(10)
	}
}
