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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import internal.GlobalVariable

public class billingTestCase {
	def runTestCase (){
		TestData data = findTestData("Data Files/TestCaseInput/TestCaseExcel")
		def apiUtility = new APIUtility()
		def usageUtil = null
		def utilityBil =  new utility()
		//def zapi = new Zephyr()
		int start = System.currentTimeMillis()
		int i = 0
		//if(GlobalVariable.g_USE_Zephyr == true) {
		//	zapi.createCycleFolder()
		//}
		if(GlobalVariable.g_USAGE_USE_VM == false) {
			usageUtil = new usageUtilityLocal()
		}else {
			usageUtil = new usageUtilityVM()
		}
		for (def index : (1..data.getRowNumbers())) {

			/*
			 KeywordUtil.logInfo('================================================      Test Step #'+index+' : '+data.getValue("Test_Step_Desc", index)+'    ================================================')
			 KeywordUtil.logInfo(' param1: '+data.getValue("param1", index)+
			 ' param2: '+data.getValue("param2", index)+' param3: '+data.getValue("param3", index)+
			 ' param4: '+data.getValue("param4", index)+' param5: '+data.getValue("param5", index)+
			 ' param5: '+data.getValue("param6", index))
			 */
			if (data.getValue("Test_Step", index)=="Jira_Project_Properties") {

			}
			else if (data.getValue("Test_Step", index)=="Start_Event"){

				KeywordUtil.logInfo('***********************************************  Start of Test Case: '+data.getValue("Test_Step_Desc", index)+'  ***********************************************')
				//if(GlobalVariable.g_USE_Zephyr == true) {
				//	zapi.jiraStatusUpdate(data.getValue("Summary", index),data.getValue("Test_Step_Desc", index), data.getValue("param1", index), data.getValue("Expected_Result", index))
				//}
			}
			else if (data.getValue("Test_Step", index)=="End_Event"){
				KeywordUtil.logInfo('***********************************************  End of Test Case: '+data.getValue("Test_Step_Desc", index)+'  ***********************************************\n')
			}
			else{
				i++
				GlobalVariable.g_Test_Case_Desc = 'CDR Event Test Step #'+i+' : '+data.getValue("Test_Step_Desc", index)+'\n'
				//KeywordUtil.logInfo('================================================      Test Step #'+i+' : '+data.getValue("Test_Step_Desc", index)+'    ================================================')
				KeywordUtil.logInfo('>>>  Test Step #'+i+' : '+data.getValue("Test_Step_Desc", index)+'  ===========================================')
				KeywordUtil.logInfo(' param1: '+data.getValue("param1", index)+
						' param2: '+data.getValue("param2", index)+' param3: '+data.getValue("param3", index)+
						' param4: '+data.getValue("param4", index)+' param5: '+data.getValue("param5", index)+
						' param6: '+data.getValue("param6", index)+' param7: '+data.getValue("param7", index)+
						' param8: '+data.getValue("param8", index))
			}

			switch (data.getValue("Test_Step", index)){
				case "Start_Event":
				//KeywordUtil.logInfo('================================================      Start of Test Case: '+data.getValue("Test_Step_Desc", index)+'    ================================================')
					i = 0
					break
				case "End_Event":
				//KeywordUtil.logInfo('================================================      End of Test Case: '+data.getValue("Test_Step_Desc", index)+'    ================================================')
					break

				case "Activation":
				//param1: MDN
					if (data.getValue("param1", index)!=null){

						apiUtility.activationMDN(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Get_Cust_Offer_Detail_Pre":
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						apiUtility.getSubInfoDetailPre(data.getValue("param1", index))
						//utilityBil.getPricePlanPerMDN(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Get_Cust_Offer_Detail_Post":
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						apiUtility.getSubInfoDetailPost(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Top_Up":
				//param1: MDN , param2: denomination/amount
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null){
						apiUtility.topUp(data.getValue("param1", index),data.getValue("param2", index))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "Add_IPP":
				//param1: MDN , param2: service code
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)){
						apiUtility.addIPP(data.getValue("param1", index),data.getValue("param2", index))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "Add_IPP_Fail_Stop":
				//param1: MDN , param2: service code
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)){
						apiUtility.addIPPFailStop(data.getValue("param1", index),data.getValue("param2", index))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "Get_Partisi_Detail":
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						apiUtility.getPartisiDetail(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Usage_Voice":
				//param1: Session Name , param2: MDN A No , param3: MDN B No , param4: Time ,  param5: Duration ,  param6: ANI
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null||data.getValue("param3", index)!=null||
					data.getValue("param4", index)!=null||data.getValue("param5", index)!=null||data.getValue("param6", index)!=null){
						usageUtil.runVOICE(data.getValue("param1", index), data.getValue("param2", index), data.getValue("param3", index), data.getValue("param4", index), data.getValue("param5", index), data.getValue("param6", index), getBillingCycleTypeID(data.getValue("param2", index)))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "Usage_Voice_IN":
				//param1: Session Name , param2: MDN A No , param3: MDN B No , param4: Time ,  param5: Duration
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null||data.getValue("param3", index)!=null||
					data.getValue("param4", index)!=null||data.getValue("param5", index)!=null){
						usageUtil.runVOICE_IN(data.getValue("param1", index), data.getValue("param2", index), data.getValue("param3", index), data.getValue("param4", index), data.getValue("param5", index), getBillingCycleTypeID(data.getValue("param2", index)))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break

				case "Usage_Voice_IN_CF":
				//param1: Session Name , param2: MDN A No , param3: MDN B No , param4: MDN C No , param5: Time ,  param6: Duration
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null||data.getValue("param3", index)!=null||
					data.getValue("param4", index)!=null||data.getValue("param5", index)!=null||data.getValue("param6", index)!=null){
						usageUtil.runVOICE_IN_CF(data.getValue("param1", index), data.getValue("param2", index), data.getValue("param3", index), data.getValue("param4", index), data.getValue("param5", index), data.getValue("param6", index), getBillingCycleTypeID(data.getValue("param3", index)))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break

				case "Usage_SMS":
				//param1: Session Name , param2: MDN A No , param3: MDN B No , param4: Time
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null||data.getValue("param3", index)!=null||
					data.getValue("param4", index)!=null){
						usageUtil.runSMS(data.getValue("param1", index), data.getValue("param2", index), data.getValue("param3", index), data.getValue("param4", index), getBillingCycleTypeID(data.getValue("param2", index)))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "Usage_Data":
				//param1: Session Name , param2: MDN , param3:  RG, param4:  Time,  param5: User Equipment,  param6: Location,  param7: Usage Data (byte), param8: SGSN IP Address
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null||data.getValue("param3", index)!=null||
					data.getValue("param4", index)!=null||data.getValue("param5", index)!=null||data.getValue("param6", index)!=null||data.getValue("param7", index)!=null||data.getValue("param8", index)!=null){

						usageUtil.runGPRS(data.getValue("param1", index), data.getValue("param2", index), data.getValue("param3", index), data.getValue("param4", index), data.getValue("param5", index), data.getValue("param6", index), data.getValue("param7", index), data.getValue("param8", index), getBillingCycleTypeID(data.getValue("param2", index)))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "Usage_Voice_Wait":
				//param1: Session Name , param2: MDN A No , param3: MDN B No , param4: Time ,  param5: Duration ,  param6: ANI
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null||data.getValue("param3", index)!=null||
					data.getValue("param4", index)!=null||data.getValue("param5", index)!=null||data.getValue("param6", index)!=null){

						usageUtil.runVOICEWait(data.getValue("param1", index), data.getValue("param2", index), data.getValue("param3", index), data.getValue("param4", index), data.getValue("param5", index), data.getValue("param6", index), getBillingCycleTypeID(data.getValue("param2", index)))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "Usage_SMS_Wait":
				//param1: Session Name , param2: MDN A No , param3: MDN B No , param4: Time
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null||data.getValue("param3", index)!=null||
					data.getValue("param4", index)!=null){
						usageUtil.runSMSWait(data.getValue("param1", index), data.getValue("param2", index), data.getValue("param3", index), data.getValue("param4", index), getBillingCycleTypeID(data.getValue("param2", index)))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "Usage_Data_Wait":
				//param1: Session Name , param2: MDN , param3:  RG, param4:  Time,  param5: User Equipment ,  param6: Location,  param7: Usage Data (byte)
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null||data.getValue("param3", index)!=null||
					data.getValue("param4", index)!=null||data.getValue("param5", index)!=null||data.getValue("param6", index)!=null||data.getValue("param7", index)!=null){
						usageUtil.runGPRSWait(data.getValue("param1", index), data.getValue("param2", index), data.getValue("param3", index), data.getValue("param4", index), data.getValue("param5", index), data.getValue("param6", index), data.getValue("param7", index), getBillingCycleTypeID(data.getValue("param2", index)))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "Run_Recurring":
				//param1: MDN , param2: Service Code , param3: Recurring Date, param4: Recurring Type
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null||data.getValue("param3", index)!=null||
					data.getValue("param4", index)!=null){
						utilityBil.runRecurring(data.getValue("param1", index), data.getValue("param2", index), data.getValue("param3", index), data.getValue("param4", index))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "Get_Recurring_Charge":
				//param1: MDN , param2: Recurring Date
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null){
						utilityBil.recuringCharge(data.getValue("param1", index),data.getValue("param2", index), getBillingCycleTypeID(data.getValue("param1", index)))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "Get_PCRF_Detail":
					if (data.getValue("param1", index)!=null || data.getValue("param2", index)!=null || data.getValue("param3", index)!=null){
						usageUtil.runPCRF_Speed(data.getValue("param1", index),data.getValue("param2", index),data.getValue("param3", index),data.getValue("param4", index))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "Get_Notification":
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						utilityBil.getNotification(data.getValue("param1", index),start)
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Update_MDN_Initial": //update MDN status become Initial
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						utilityBil.updateMDNstatusInitial(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Update_MDN_Active": //update MDN status become Active
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						utilityBil.updateMDNstatusActive(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Update_MDN_B1W": //update MDN status become B1W (one way block / grace)
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						utilityBil.updateMDNstatusB1W(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Update_MDN_B2W": //update MDN status become B2W (Two way block / grace)
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						utilityBil.updateMDNstatusB2W(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Update_Bal_old":
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						//utilityBil.updateBalance(data.getValue("param1", index))
						utilityBil.runMDBTT(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Update_Bal":
				//param1: MDN, param2:res_id, param3:gross_bal, param4:consume_bal, param5:init_bal, param6:reserve_bal
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null){
						utilityBil.updateBalance(data.getValue("param1", index),data.getValue("param2", index),data.getValue("param3", index),data.getValue("param4", index),data.getValue("param5", index),data.getValue("param6", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty or param2(res_id) empty")
					}
					break
				case "PAYG_Off":
				//param1: Session Name , param2: MDN , param3:  RG, param4:  Time,  param5: User Equipment ,  param6: Location,  param7: Usage Data (byte)
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null||data.getValue("param3", index)!=null||
					data.getValue("param4", index)!=null||data.getValue("param5", index)!=null||data.getValue("param6", index)!=null||data.getValue("param7", index)!=null){

						usageUtil.runPAYGOffData(data.getValue("param1", index), data.getValue("param2", index), data.getValue("param3", index), data.getValue("param4", index), data.getValue("param5", index), data.getValue("param6", index), data.getValue("param7", index), getBillingCycleTypeID(data.getValue("param2", index)))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "Get_Partisi_Detail_Recurring":
				//param1: MDN
					if (data.getValue("param1", index)!=null){

						apiUtility.getQueryAcctBal(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Get_User_Individual_Price_Plan_List":
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						apiUtility.GetUserIndiPricePlanList(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Delete_Partition_MDBTT":
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						utilityBil.deletePartitionMDBTT(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Delete_Recurring_MDBTT":
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						utilityBil.deleteRecurringMDBTT(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Delete_ACM_MDBTT":
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						utilityBil.deleteACMMDBTT(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Remove_IPP":
				//param1: MDN, param2: serviceCode
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null){
						apiUtility.removeIPP(data.getValue("param1", index), data.getValue("param2", index))
						utilityBil.removeIPPMDBTT(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Remove_IPP_Only":
				//param1: MDN, param2: serviceCode
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null){
						apiUtility.removeIPP(data.getValue("param1", index), data.getValue("param2", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Remove_IPP_MDBTT":
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						utilityBil.removeIPPMDBTT(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Get_Partition_MDBTT":
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						utilityBil.selectPartitionMDBTT(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Get_Subs_ACM_Cycle_MDBTT":
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						utilityBil.selectSubsACMCycleMDBTT(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Get_Subs_ACM_MDBTT":
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						utilityBil.selectSubsACMMDBTT(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Get_Subs_ACM_Daily_MDBTT":
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						utilityBil.selectSubsACMDailyMDBTT(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Asyncall_Job":
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						utilityBil.runAsyncallJob(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Update_Bal_Date":
				//param1: MDN, param2: type(0=eff_date,1=exp_date), param3:res_id, param4:date
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null||data.getValue("param3", index)!=null||data.getValue("param4", index)!=null){
						utilityBil.updateBalDate(data.getValue("param1", index), data.getValue("param2", index), data.getValue("param3", index), data.getValue("param4", index))
					}
					else{
						KeywordUtil.logInfo("Please check some param is empty")
					}
					break
				case "Top_Up_Mandiri_VA":
				//param1: MDN , param2: denomination/amount, param3 : code
					if (data.getValue("param1", index)!=null||(data.getValue("param2", index)!=null && data.getValue("param2", index) >= 50000)||data.getValue("param3", index)!=null){
						apiUtility.topUpMandiriVA(data.getValue("param1", index),data.getValue("param2", index),data.getValue("param3", index))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty or amount less than  50k")
					}
					break
				case "Recharge_MFino":
				//param1: MDN , param2: MerchantType, param3 : Bank_Code, param4 : Amount_Balance, param5 : productIndicator
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null || data.getValue("param3", index) !=null||data.getValue("param4", index)!=null||data.getValue("param5", index)!=null){
						apiUtility.rechargeMFino(data.getValue("param1", index),data.getValue("param2", index),data.getValue("param3", index),data.getValue("param4", index),data.getValue("param5", index))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "PackagePurchase_MFino":
				//param1: MDN , param2: MerchantType, param3 : Bank_Code, param4 : Amount_Balance, param5 : productIndicator, param6 : PackageID, param7 : AreaID
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null || data.getValue("param3", index) !=null||data.getValue("param4", index)!=null||data.getValue("param5", index)!=null||data.getValue("param5=6", index)!=null){
						apiUtility.packagePurchaseMFino(data.getValue("param1", index),data.getValue("param2", index),data.getValue("param3", index),data.getValue("param4", index),data.getValue("param5", index),data.getValue("param6", index),data.getValue("param7", index))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty except Area ID")
					}
					break
				case "Update_ACM_Cycle_Value_MDBTT":
				//param1: MDN , param2: value , param3: acm_cycle_type_id
					if (data.getValue("param1", index)!=null){
						utilityBil.updateACMCycleValueMDBTT(data.getValue("param1", index),data.getValue("param2", index),data.getValue("param3", index))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "Update_Subs_ACM_Value_MDBTT":
				//param1: MDN , param2: value , param3: resource_id
					if (data.getValue("param1", index)!=null){
						utilityBil.updateSubsACMValueMDBTT(data.getValue("param1", index),data.getValue("param2", index),data.getValue("param3", index))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "Get_ACM_Cycle_MDBTT":
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						utilityBil.selectACMCycleMDBTT(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "Get_Cust_Product_Detail_Pre":
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						apiUtility.getSubInfoProductOnlyPre(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Get_Credit_Limit_Post":
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						apiUtility.getCreditLimitInfoPost(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check param1 (MDN) empty")
					}
					break
				case "Usage_Voice_Roaming":
				//param1: Session Name ,param2: type event(MO/MT), param3: MDN A No , param4: MDN B No , param5: Time ,  param6: Duration ,  param7: ANI, param8: RatingGroup
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null||data.getValue("param3", index)!=null||
					data.getValue("param4", index)!=null||data.getValue("param5", index)!=null||data.getValue("param6", index)!=null||data.getValue("param7", index)!=null||data.getValue("param8", index)!=null){

						usageUtil.runVOICE_Roaming(data.getValue("param1", index), data.getValue("param2", index), data.getValue("param3", index), data.getValue("param4", index), data.getValue("param5", index), data.getValue("param6", index), data.getValue("param7", index), data.getValue("param8", index), getBillingCycleTypeID(data.getValue("param3", index)))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "Usage_SMS_Roaming":
				//param1: Session Name ,param2: type event(MO/MT), param3: MDN A No , param4: MDN B No , param5: Time ,  param6: ADR_DATA
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null||data.getValue("param3", index)!=null||
					data.getValue("param4", index)!=null||data.getValue("param5", index)!=null||data.getValue("param6", index)!=null){

						usageUtil.runSMS_Roaming(data.getValue("param1", index), data.getValue("param2", index), data.getValue("param3", index), data.getValue("param4", index), data.getValue("param5", index), data.getValue("param6", index), getBillingCycleTypeID(data.getValue("param3", index)))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "Create_Bill_Data":
				//param1: MDN ,param2: bill amount
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null){

						utilityBil.createBillPostpaid(data.getValue("param1", index), data.getValue("param2", index))
					}
					else{
						KeywordUtil.logInfo("Please check MDN param or Bill Amount param is empty")
					}
					break
				case "Get_Subs_Info":
				//param1: MDN
					if (data.getValue("param1", index)!=null){

						apiUtility.GetSubsInfo(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check MDN param is empty")
					}
					break
				case "Add_IPP_No_Deduct_Balance":
				//param1: MDN , param2: service code
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)||data.getValue("param3", index)||data.getValue("param4", index)){
						apiUtility.addIPPNoDeductBalance(data.getValue("param1", index),data.getValue("param2", index),data.getValue("param3", index),data.getValue("param4", index))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break

				case "Update_CreditLimit":
				//param1: MDN , param2: CL_Amount
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)){
						utilityBil.updateCreditLimit(data.getValue("param1", index),data.getValue("param2", index))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "DeleteUsage_CL":
				//param1: MDN
					if (data.getValue("param1", index)!=null){
						utilityBil.deleteUsageCL(data.getValue("param1", index))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "Update_IPP_date":
				//param1: MDN , param2: Service_code, param3: Eff_date, param4: Exp_date
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null){
						utilityBil.updateIPPdate(data.getValue("param1", index),data.getValue("param2", index),data.getValue("param3", index),data.getValue("param4", index))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "Usage_SDP":
				//param1: Session Name , param2: MDN , param3:  SHORT-CODE, param4:  APP-ID,  param5: PRICE, param6: Time
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null||data.getValue("param3", index)!=null||data.getValue("param4", index)!=null||data.getValue("param5", index)!=null||data.getValue("param6", index)!=null){
						usageUtil.runUsageSDP(data.getValue("param1", index), data.getValue("param2", index), data.getValue("param3", index), data.getValue("param4", index), data.getValue("param5", index), data.getValue("param6", index), getBillingCycleTypeID(data.getValue("param2", index)))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				case "Add_Delay":
				//param1: Add Time Second
					try {
						if(data.getValue("param1", index)!="") {
							WS.delay(data.getValue("param1", index).toInteger())
						}
					}catch(Exception e) {}
					break
				case "Usage_SMS_MT":
				//param1: Session Name , param2: MDN A No , param3: MDN B No , param4: Time
					if (data.getValue("param1", index)!=null||data.getValue("param2", index)!=null||data.getValue("param3", index)!=null||
					data.getValue("param4", index)!=null||data.getValue("param5", index)!=null){
						usageUtil.runSMSMT(data.getValue("param1", index), data.getValue("param2", index), data.getValue("param3", index), data.getValue("param4", index), data.getValue("param5", index), getBillingCycleTypeID(data.getValue("param3", index)))
					}
					else{
						KeywordUtil.logInfo("Please check one of the param is empty")
					}
					break
				default:
					KeywordUtil.logInfo("Event Type "+data.getValue("Test_Step", index)+" not defined")
					break
			}
		}
		if(GlobalVariable.g_CDR_List.size() != 0) {
			usageUtil.getCDRList()
		}
	}
	@Keyword
	def getBillingCycleTypeID(String MDN) {
		def dbUtility = new DBUtility()
		String bilingCycle = dbUtility.queryDB('BillingDB', "SELECT BILLING_CYCLE_TYPE_ID FROM acct WHERE cust_id IN(SELECT CUST_ID FROM subs WHERE acc_nbr='"+MDN.subSequence(2, MDN.length())+"')").BILLING_CYCLE_TYPE_ID[0]
		if(bilingCycle == null) {
			KeywordUtil.markFailedAndStop("Getting BILLING_CYCLE_TYPE_ID for "+MDN+"  from database failed, please check manually")
		}
		return bilingCycle
	}
}
