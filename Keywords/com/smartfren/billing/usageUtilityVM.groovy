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
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import org.apache.commons.lang.StringUtils as StringUtils
import internal.GlobalVariable

public class usageUtilityVM {
	/*
	 * Added by Ludfi on 22-Mar-2021
	 * This is for All usage event in Katalon server using SSH and python program on QA VM
	 * Purpose is to do testing from anywhere without static IP registered
	 */
	@Keyword
	runGPRS (String SessionName, String MDN, String Time, String RG, String UserEquip, String Location, String Usage, String SGSN,String custType){
		KeywordUtil.logInfo("Run GPRS --> MDN:"+MDN+" Rating Group:"+RG+" Usage:"+Usage+" Time (default=current time):"+Time+" UserEquip:"+UserEquip+" Location:"+Location)
		def param = SessionName+' '+MDN+' '+Time+' '+RG+' '+UserEquip+' '+Location+' '+Usage+' '+SGSN
		List<String> command = new ArrayList<>();
		command.add("cd ~/SF_Zsmart_Simulator_v.3.0 && python SF-GPRS-Simulator.py "+param);
		def a = new remoteUtility()
		def result=a.runRemoteShellReturn3 (GlobalVariable.g_Usage_Server.get('host'), GlobalVariable.g_Usage_Server.get('username'), GlobalVariable.g_Usage_Server.get('password'), command)
		KeywordUtil.logInfo('GPRS Event Result\n'+result)
		def lines = result.split("\n")
		def result_code = lines[lines.size()-2].split(" ")
		if (result_code[result_code.size()-1].trim() == "2001") {
			GlobalVariable.g_CDR_List.add([StringUtils.substringBetween(lines[lines.size()-2],' ### ',' ### '), MDN, custType, GlobalVariable.g_Test_Case_Desc])
		}
	}
	@Keyword
	runVOICE (String SessionName, String A_No, String B_No, String Time, String Duration, String AccNWInfo, String custType){
		KeywordUtil.logInfo("Run VOICE --> A_No:"+A_No+" B_No:"+B_No+" Duration:"+Duration+" Time (default=current time):"+Time+" AccNWInfo:"+AccNWInfo)
		def param = SessionName+" "+A_No+" "+B_No+" "+Time+" "+Duration+" '"+AccNWInfo+"'"
		List<String> command = new ArrayList<>();
		command.add("cd ~/SF_Zsmart_Simulator_v.3.0 && python SF-VOICE-Simulator.py "+param);
		def a = new remoteUtility()
		def result=a.runRemoteShellReturn3 (GlobalVariable.g_Usage_Server.get('host'), GlobalVariable.g_Usage_Server.get('username'), GlobalVariable.g_Usage_Server.get('password'), command)
		KeywordUtil.logInfo('Voice Event Result\n'+result)
		def lines = result.split("\n")
		def result_code = lines[lines.size()-2].split(" ")
		if (result_code[result_code.size()-1].trim() == "2001") {
			GlobalVariable.g_CDR_List.add([StringUtils.substringBetween(lines[lines.size()-2],' ### ',' ### '), A_No, custType, GlobalVariable.g_Test_Case_Desc])
		}
	}
	@Keyword
	runVOICE_IN (String SessionName, String A_No, String B_No, String Time, String Duration, String custType){
		KeywordUtil.logInfo("Run VOICE --> A_No:"+A_No+" B_No:"+B_No+" Duration:"+Duration+" Time (default=current time):"+Time)
		def param = SessionName+" "+A_No+" "+B_No+" "+Time+" "+Duration
		List<String> command = new ArrayList<>();
		command.add("cd ~/SF_Zsmart_Simulator_v.3.0 && python SF-VOICE-SimulatorIN.py "+param);
		def a = new remoteUtility()
		def result=a.runRemoteShellReturn3 (GlobalVariable.g_Usage_Server.get('host'), GlobalVariable.g_Usage_Server.get('username'), GlobalVariable.g_Usage_Server.get('password'), command)
		KeywordUtil.logInfo('Voice Event Result\n'+result)
		def lines = result.split("\n")
		def result_code = lines[lines.size()-2].split(" ")
		if (result_code[result_code.size()-1].trim() == "2001") {
			GlobalVariable.g_CDR_List.add([StringUtils.substringBetween(lines[lines.size()-2],' ### ',' ### '), A_No, custType, GlobalVariable.g_Test_Case_Desc])
		}
	}
	@Keyword
	runVOICE_IN_CF (String SessionName, String A_No, String B_No, String C_No, String Time, String Duration, String custType){
		KeywordUtil.logInfo("Run VOICE --> A_No:"+A_No+" B_No:"+B_No+" C_No:"+C_No+" Duration:"+Duration+" Time (default=current time):"+Time)
		def param = SessionName+" "+A_No+" "+B_No+" "+C_No+" "+Time+" "+Duration
		List<String> command = new ArrayList<>();
		command.add("cd ~/SF_Zsmart_Simulator_v.3.0 && python SF-VOICE-SimulatorIN-CallForward.py "+param);
		def a = new remoteUtility()
		def result=a.runRemoteShellReturn3 (GlobalVariable.g_Usage_Server.get('host'), GlobalVariable.g_Usage_Server.get('username'), GlobalVariable.g_Usage_Server.get('password'), command)
		KeywordUtil.logInfo('Voice Event Result\n'+result)
		def lines = result.split("\n")
		def result_code = lines[lines.size()-2].split(" ")
		if (result_code[result_code.size()-1].trim() == "2001") {
			GlobalVariable.g_CDR_List.add([StringUtils.substringBetween(lines[lines.size()-2],' ### ',' ### '), B_No, custType, GlobalVariable.g_Test_Case_Desc])
		}
	}
	@Keyword
	runSMS (String SessionName, String A_No, String B_No, String Time, String custType){
		KeywordUtil.logInfo("Run SMS --> A_No:"+A_No+" B_No:"+B_No+" Time (default=current time):"+Time)
		def param = SessionName+' '+A_No+' '+B_No+' '+Time
		List<String> command = new ArrayList<>();
		command.add("cd ~/SF_Zsmart_Simulator_v.3.0 && python SF-SMS-Simulator.py "+param);
		def a = new remoteUtility()
		def result=a.runRemoteShellReturn3 (GlobalVariable.g_Usage_Server.get('host'), GlobalVariable.g_Usage_Server.get('username'), GlobalVariable.g_Usage_Server.get('password'), command)
		KeywordUtil.logInfo('SMS Event Result\n'+result)
		def lines = result.split("\n")
		def result_code = lines[lines.size()-2].split(" ")
		if (result_code[result_code.size()-1].trim() == "2001") {
			GlobalVariable.g_CDR_List.add([StringUtils.substringBetween(lines[lines.size()-2],' ### ',' ### '), A_No, custType, GlobalVariable.g_Test_Case_Desc])
		}
	}
	@Keyword
	runSMSMT (String SessionName, String A_No, String B_No, String ADDR_DATA, String Time, String custType){
		KeywordUtil.logInfo("Run SMS MT --> Calling_No:"+A_No+" Called_No:"+B_No+" ADDR_DATA:"+ADDR_DATA+" Time (default=current time):"+Time)
		def param = SessionName+' '+A_No+' '+B_No+' '+ADDR_DATA+' '+Time
		List<String> command = new ArrayList<>();
		command.add("cd ~/SF_Zsmart_Simulator_v.3.0 && python SF-SMS-MT-Simulator.py "+param);
		def a = new remoteUtility()
		def result=a.runRemoteShellReturn3 (GlobalVariable.g_Usage_Server.get('host'), GlobalVariable.g_Usage_Server.get('username'), GlobalVariable.g_Usage_Server.get('password'), command)
		KeywordUtil.logInfo('SMS MT Event Result\n'+result)
		def lines = result.split("\n")
		def result_code = lines[lines.size()-2].split(" ")
		if (result_code[result_code.size()-1].trim() == "2001") {
			GlobalVariable.g_CDR_List.add([StringUtils.substringBetween(lines[lines.size()-2],' ### ',' ### '), A_No, custType, GlobalVariable.g_Test_Case_Desc])
		}
	}
	@Keyword
	runGPRSWait (String SessionName, String MDN, String Time, String RG, String UserEquip, String Location, String Usage,String custType){
		KeywordUtil.logInfo("Run GPRS --> MDN:"+MDN+" Rating Group:"+RG+" Usage:"+Usage+" Time (default=current time):"+Time+" UserEquip:"+UserEquip+" Location:"+Location)
		def param = SessionName+' '+MDN+' '+Time+' '+RG+' '+UserEquip+' '+Location+' '+Usage
		List<String> command = new ArrayList<>();
		command.add("cd ~/SF_Zsmart_Simulator_v.3.0 && python SF-GPRS-Simulator.py "+param);
		def a = new remoteUtility()
		def result=a.runRemoteShellReturn3 (GlobalVariable.g_Usage_Server.get('host'), GlobalVariable.g_Usage_Server.get('username'), GlobalVariable.g_Usage_Server.get('password'), command)
		KeywordUtil.logInfo('GPRS Wait Event Result\n'+result)
		def lines = result.split("\n")
		def sessionID = StringUtils.substringBetween(lines[lines.size()-2],' ### ',' ### ')
		def dbUtility = new DBUtility()
		def bilingCycle = dbUtility.queryDB('BillingDB', "SELECT BILLING_CYCLE_ID FROM BILLING_CYCLE WHERE to_char(CYCLE_BEGIN_DATE,'yyyymm') = '"+new Date().format('yyyyMM')+"' AND BILLING_CYCLE_TYPE_ID  = '"+custType+"'")
		def queryInput = "select cdr.SESSION_ID,r.RE_NAME EVENT,cdr.BILLING_NBR,cdr.START_TIME,cdr.BYTE_DOWN,cdr.DURATION,cdr.STATE,cdr.BILLING_CYCLE_ID,"+
				"cdr.MESSAGE_ID,cdr.SERVICE_TYPE,p.price_id,p.PRICE_NAME,cdr.ACCT_ITEM_TYPE_ID1,a.ACCT_ITEM_TYPE_NAME ,cdr.PRE_BALANCE_LIST,cdr.CHARGE_LIST,cdr.BALANCE_LIST,cdr.LAC_A,cdr.LAC_B"+
				"from (SELECT * FROM event_usage_c_"+bilingCycle.BILLING_CYCLE_ID[0]+"@link_ccrb01 UNION SELECT * FROM event_usage_c_"+bilingCycle.BILLING_CYCLE_ID[0]+"@link_ccrb02) cdr, re r, subs s, ACCT_ITEM_TYPE a,price p"+
				"where s.acc_nbr='"+MDN.subSequence(2, MDN.length())+"' and cdr.re_id=r.re_id and cdr.subs_id=s.subs_id"+
				"AND cdr.session_id LIKE '%"+sessionID+"%' AND cdr.ACCT_ITEM_TYPE_ID1 = a.ACCT_ITEM_TYPE_ID"+
				"AND p.PRICE_ID = REGEXP_SUBSTR(TO_CHAR(cdr.PRICE_LIST),'[^,]+',1,1)  order by start_time DESC"
		//KeywordUtil.logInfo("queryInput :"+queryInput)
		WS.delay(120)
		dbUtility.displayQueryResult2 ('BillingDB',queryInput)
	}
	@Keyword
	runVOICEWait (String SessionName, String A_No, String B_No, String Time, String Duration, String AccNWInfo, String custType){
		KeywordUtil.logInfo("Run VOICE --> A_No:"+A_No+" B_No:"+B_No+" Duration:"+Duration+" Time (default=current time):"+Time+" AccNWInfo:"+AccNWInfo)
		def param = SessionName+" "+A_No+" "+B_No+" "+Time+" "+Duration+" '"+AccNWInfo+"'"
		List<String> command = new ArrayList<>();
		command.add("cd ~/SF_Zsmart_Simulator_v.3.0 && python SF-VOICE-Simulator.py "+param);
		def a = new remoteUtility()
		def result=a.runRemoteShellReturn3 (GlobalVariable.g_Usage_Server.get('host'), GlobalVariable.g_Usage_Server.get('username'), GlobalVariable.g_Usage_Server.get('password'), command)
		KeywordUtil.logInfo('Voice Wait Event Result\n'+result)
		def lines = result.split("\n")
		def sessionID = StringUtils.substringBetween(lines[lines.size()-2],' ### ',' ### ')
		def dbUtility = new DBUtility()
		def bilingCycle = dbUtility.queryDB('BillingDB', "SELECT BILLING_CYCLE_ID FROM BILLING_CYCLE WHERE to_char(CYCLE_BEGIN_DATE,'yyyymm') = '"+new Date().format('yyyyMM')+"' AND BILLING_CYCLE_TYPE_ID  = '"+custType+"'")
		def queryInput = "SELECT cdr.SESSION_ID,r.RE_NAME EVENT,cdr.BILLING_NBR,cdr.CALLING_NBR,cdr.CALLED_NBR,cdr.START_TIME,cdr.DURATION,cdr.STATE,cdr.BILLING_CYCLE_ID,"+
				"cdr.MESSAGE_ID,cdr.SERVICE_TYPE,p.price_id,p.PRICE_NAME,cdr.ACCT_ITEM_TYPE_ID1,a.ACCT_ITEM_TYPE_NAME ,cdr.PRE_BALANCE_LIST,cdr.CHARGE_LIST,"+
				"cdr.BALANCE_LIST FROM (SELECT * FROM event_usage_"+bilingCycle.BILLING_CYCLE_ID[0]+"@link_ccrb01 UNION SELECT * FROM event_usage_"+bilingCycle.BILLING_CYCLE_ID[0]+"@link_ccrb02) cdr, re r, subs s, ACCT_ITEM_TYPE a,price p"+
				"where s.acc_nbr='"+A_No.subSequence(2, A_No.length())+"' and cdr.re_id=r.re_id and cdr.subs_id=s.subs_id"+
				"AND cdr.session_id LIKE '%"+sessionID+"%' AND cdr.ACCT_ITEM_TYPE_ID1 = a.ACCT_ITEM_TYPE_ID"+
				"AND p.PRICE_ID = REGEXP_SUBSTR(TO_CHAR(cdr.PRICE_LIST),'[^,]+',1,1) order by start_time DESC"
		//KeywordUtil.logInfo("queryInput :"+queryInput)
		WS.delay(120)
		dbUtility.displayQueryResult2 ('BillingDB',queryInput)
	}
	@Keyword
	runSMSWait (String SessionName, String A_No, String B_No, String Time, String custType){
		KeywordUtil.logInfo("Run SMS --> A_No:"+A_No+" B_No:"+B_No+" Time (default=current time):"+Time)
		def param = SessionName+' '+A_No+' '+B_No+' '+Time
		List<String> command = new ArrayList<>();
		command.add("cd ~/SF_Zsmart_Simulator_v.3.0 && python SF-SMS-Simulator.py "+param);
		def a = new remoteUtility()
		def result=a.runRemoteShellReturn3 (GlobalVariable.g_Usage_Server.get('host'), GlobalVariable.g_Usage_Server.get('username'), GlobalVariable.g_Usage_Server.get('password'), command)
		KeywordUtil.logInfo('SMS Wait Event Result\n'+result)
		def lines = result.split("\n")
		def sessionID = StringUtils.substringBetween(lines[lines.size()-2],' ### ',' ### ')
		def dbUtility = new DBUtility()
		def bilingCycle = dbUtility.queryDB('BillingDB', "SELECT BILLING_CYCLE_ID FROM BILLING_CYCLE WHERE to_char(CYCLE_BEGIN_DATE,'yyyymm') = '"+new Date().format('yyyyMM')+"' AND BILLING_CYCLE_TYPE_ID  = '"+custType+"'")
		def queryInput = "SELECT cdr.SESSION_ID,r.RE_NAME EVENT,cdr.BILLING_NBR,cdr.CALLING_NBR,cdr.CALLED_NBR,cdr.START_TIME,cdr.DURATION,cdr.STATE,cdr.BILLING_CYCLE_ID,"+
				"cdr.MESSAGE_ID,cdr.SERVICE_TYPE,p.price_id,p.PRICE_NAME,cdr.ACCT_ITEM_TYPE_ID1,a.ACCT_ITEM_TYPE_NAME ,cdr.PRE_BALANCE_LIST,cdr.CHARGE_LIST,"+
				"cdr.BALANCE_LIST FROM (SELECT * FROM event_usage_"+bilingCycle.BILLING_CYCLE_ID[0]+"@link_ccrb01 UNION SELECT * FROM event_usage_"+bilingCycle.BILLING_CYCLE_ID[0]+"@link_ccrb02) cdr, re r, subs s, ACCT_ITEM_TYPE a,price p"+
				"where s.acc_nbr='"+A_No.subSequence(2, A_No.length())+"' and cdr.re_id=r.re_id and cdr.subs_id=s.subs_id"+
				"AND cdr.session_id LIKE '%"+sessionID+"%' AND cdr.ACCT_ITEM_TYPE_ID1 = a.ACCT_ITEM_TYPE_ID"+
				"AND p.PRICE_ID = REGEXP_SUBSTR(TO_CHAR(cdr.PRICE_LIST),'[^,]+',1,1) order by start_time DESC"
		//KeywordUtil.logInfo("queryInput :"+queryInput)
		WS.delay(120)
		dbUtility.displayQueryResult2 ('BillingDB',queryInput)
	}
	@Keyword
	def getCDRList(){
		KeywordUtil.logInfo('Generating CDR')
		WS.delay(300)
		def dbUtility = new DBUtility()
		def bilingCycle
		def queryInput
		GlobalVariable.g_CDR_List.each { record ->
			//record.get(0) = sessionID  record.get(1) = MDN record.get(2) = CustType
			//KeywordUtil.logInfo(record.get(0)+"    "+record.get(1)+"    "+record.get(2))
			//checkCDR(record)
			if (record.get(0).contains('DATA')){
				bilingCycle = dbUtility.queryDB('BillingDB', "SELECT BILLING_CYCLE_ID FROM BILLING_CYCLE WHERE to_char(CYCLE_BEGIN_DATE,'yyyymm') = '"+new Date().format('yyyyMM')+"' AND BILLING_CYCLE_TYPE_ID  = '"+record.get(2)+"'")
				queryInput = "select cdr.SESSION_ID,r.RE_NAME EVENT,cdr.BILLING_NBR,cdr.START_TIME,cdr.BYTE_DOWN,cdr.DURATION,cdr.STATE,cdr.BILLING_CYCLE_ID,"+
						"cdr.MESSAGE_ID,cdr.SERVICE_TYPE,p.price_id,p.PRICE_NAME,cdr.ACCT_ITEM_TYPE_ID1,a.ACCT_ITEM_TYPE_NAME ,cdr.PRE_BALANCE_LIST,cdr.CHARGE_LIST,cdr.BALANCE_LIST,cdr.LAC_A,cdr.LAC_B"+
						"from (SELECT * FROM event_usage_c_"+bilingCycle.BILLING_CYCLE_ID[0]+"@link_ccrb01 UNION SELECT * FROM event_usage_c_"+bilingCycle.BILLING_CYCLE_ID[0]+"@link_ccrb02) cdr, re r, subs s, ACCT_ITEM_TYPE a,price p"+
						"where s.acc_nbr='"+record.get(1).subSequence(2, record.get(1).length())+"' and cdr.re_id=r.re_id and cdr.subs_id=s.subs_id"+
						"AND cdr.session_id LIKE '%"+record.get(0)+"%' AND cdr.ACCT_ITEM_TYPE_ID1 = a.ACCT_ITEM_TYPE_ID"+
						"AND p.PRICE_ID = REGEXP_SUBSTR(TO_CHAR(cdr.PRICE_LIST),'[^,]+',1,1)  order by start_time DESC"
				//KeywordUtil.logInfo("queryInput :"+queryInput)
				dbUtility.displayQueryResult3 ('BillingDB',queryInput,record.get(3))
			}
			else if (record.get(0).contains('VOICE')||record.get(0).contains('SMS')){
				bilingCycle = dbUtility.queryDB('BillingDB', "SELECT BILLING_CYCLE_ID FROM BILLING_CYCLE WHERE to_char(CYCLE_BEGIN_DATE,'yyyymm') = '"+new Date().format('yyyyMM')+"' AND BILLING_CYCLE_TYPE_ID  = '"+record.get(2)+"'")
				queryInput = "SELECT cdr.SESSION_ID,r.RE_NAME EVENT,cdr.BILLING_NBR,cdr.CALLING_NBR,cdr.CALLED_NBR,cdr.START_TIME,cdr.DURATION,cdr.STATE,cdr.BILLING_CYCLE_ID,"+
						"cdr.MESSAGE_ID,cdr.SERVICE_TYPE,p.price_id,p.PRICE_NAME,cdr.ACCT_ITEM_TYPE_ID1,a.ACCT_ITEM_TYPE_NAME ,cdr.PRE_BALANCE_LIST,cdr.CHARGE_LIST,"+
						"cdr.BALANCE_LIST FROM (SELECT * FROM event_usage_"+bilingCycle.BILLING_CYCLE_ID[0]+"@link_ccrb01 UNION SELECT * FROM event_usage_"+bilingCycle.BILLING_CYCLE_ID[0]+"@link_ccrb02) cdr, re r, subs s, ACCT_ITEM_TYPE a,price p"+
						"where s.acc_nbr='"+record.get(1).subSequence(2, record.get(1).length())+"' and cdr.re_id=r.re_id and cdr.subs_id=s.subs_id"+
						"AND cdr.session_id LIKE '%"+record.get(0)+"%' AND cdr.ACCT_ITEM_TYPE_ID1 = a.ACCT_ITEM_TYPE_ID"+
						"AND p.PRICE_ID = REGEXP_SUBSTR(TO_CHAR(cdr.PRICE_LIST),'[^,]+',1,1) order by start_time DESC"
				//KeywordUtil.logInfo("queryInput :"+queryInput)
				dbUtility.displayQueryResult3 ('BillingDB',queryInput,record.get(3))
			}
		}
	}

	@Keyword
	runPAYGOffData (String SessionName, String MDN, String Time, String RG, String UserEquip, String Location, String Usage,String custType){
		//usage until partition depleted first (zero)
		KeywordUtil.logInfo("Run GPRS --> MDN:"+MDN+" Rating Group:"+RG+" Usage:"+Usage+" Time (default=current time):"+Time+" UserEquip:"+UserEquip+" Location:"+Location)
		def param = SessionName+' '+MDN+' '+Time+' '+RG+' '+UserEquip+' '+Location+' '+Usage
		List<String> command = new ArrayList<>();
		command.add("cd ~/SF_Zsmart_Simulator_v.3.0 && python SF-GPRS-Simulator.py "+param);
		def a = new remoteUtility()
		def result=a.runRemoteShellReturn3 (GlobalVariable.g_Usage_Server.get('host'), GlobalVariable.g_Usage_Server.get('username'), GlobalVariable.g_Usage_Server.get('password'), command)
		KeywordUtil.logInfo('GPRS Event Result\n'+result)

		//run usage data after partition has been depleted (zero)
		runGPRS(data.getValue("param1", index), data.getValue("param2", index), data.getValue("param3", index), data.getValue("param4", index), data.getValue("param5", index), data.getValue("param6", index), data.getValue("param7", index), data.getValue("CustType", index))
	}

	@Keyword
	runPCRF_Speed (String SessionName, String A_No, String mcc_mnc, String desc){
		def a = new remoteUtility()
		KeywordUtil.logInfo("Run PCRF Speed Check --> A_No:"+A_No+" MCC_MNC:"+mcc_mnc)
		long unixTime = System.currentTimeMillis() / 1000L
		def session_id = SessionName+'PCRF'+unixTime
		def param_initial = 'initial '+session_id+' '+A_No+' '+mcc_mnc

		List<String> command_initial = new ArrayList<>();
		command_initial.add("cd ~/SF_Zsmart_Simulator_v.3.0 && python SF-PCRF-SpeedChecker.py "+param_initial);
		def result_initial=a.runRemoteShellReturn3 (GlobalVariable.g_Usage_Server.get('host'), GlobalVariable.g_Usage_Server.get('username'), GlobalVariable.g_Usage_Server.get('password'), command_initial)
		KeywordUtil.logInfo('PCRF Speed Check Initial Event\n'+result_initial+'\n'+desc)

		//WS.delay(GlobalVariable.g_Usage_Delay)
//		try {
//			String last_file = a.runRemoteCommandReturn(GlobalVariable.PCRF_SSH_Server.get('host'), GlobalVariable.PCRF_SSH_Server.get('username'), GlobalVariable.PCRF_SSH_Server.get('password'), 'cd log && ls ULOG*_PDE | tail -1').trim()
//			//println 'last file : '+last_file
//			//def session='serea1.zte.com.cn;3575694234;6558965'
//			def line_number_command = """grep -n -e 'Session-Id\\s=\\s\\["""+session_id+"""\\]' -e 'Auth-Application-Id\\s=\\s\\[3902001\\]' log/"""+last_file+""" | tail | cut -d ':' -f1"""
//			//println 'line number command : '+line_number_command
//			String line_number = a.runRemoteCommandReturn(GlobalVariable.PCRF_SSH_Server.get('host'), GlobalVariable.PCRF_SSH_Server.get('username'), GlobalVariable.PCRF_SSH_Server.get('password'), line_number_command)
//			//print line_number
//			def data=line_number.split("\n")
//			def start_line=''
//			for(int c=0; c<data.size()-1;c++) {
//				if (c != data.size()-1) {
//					if ((Integer.parseInt(data[c].trim())+1).equals(Integer.parseInt(data[c+1].trim()))) {
//						start_line=data[c]
//						//break
//					}
//				}
//			}
//			//println 'Start line : '+start_line
//			def text_raw=''
//			def text_temp=''
//			String end_line = (Integer.parseInt(start_line)+100).toString()
//			if (!start_line.equals('')) {
//				def command_text="""cd log && sed -n """+start_line+""","""+end_line+"""p """+last_file
//				//println 'Command text : '+command_text
//				text_raw=a.runRemoteCommandReturn(GlobalVariable.PCRF_SSH_Server.get('host'), GlobalVariable.PCRF_SSH_Server.get('username'), GlobalVariable.PCRF_SSH_Server.get('password'), command_text)
//				//println 'Text raw : '+text_raw
//				text_temp=(text_raw.split('\n\n'))[0]
//			}
//			//println text_temp
//			KeywordUtil.logInfo('OCS Package Information\n'+text_temp)
//		}catch(Exception e) {
//			KeywordUtil.markFailed('PCRF Speed Checker unable to get OCS Package Information Log, Please check manually')
//		}
		def param_terminate = 'terminate '+session_id+' '+A_No+' '+mcc_mnc
		List<String> command_terminate = new ArrayList<>();
		command_terminate.add("cd ~/SF_Zsmart_Simulator_v.3.0 && python SF-PCRF-SpeedChecker.py "+param_terminate);
		def result_terminate=a.runRemoteShellReturn3 (GlobalVariable.g_Usage_Server.get('host'), GlobalVariable.g_Usage_Server.get('username'), GlobalVariable.g_Usage_Server.get('password'), command_terminate)
		KeywordUtil.logInfo('PCRF Speed Check Terminate Event\n'+result_terminate)
	}

	@Keyword
	runVOICE_Roaming (String SessionName, String type,String A_No, String B_No, String Time, String Duration, String AccNWInfo, String RG,String custType){
		KeywordUtil.logInfo("Run VOICE Roaming "+type+" --> A_No:"+A_No+" B_No:"+B_No+" Duration:"+Duration+" Time (default=current time):"+Time+" AccNWInfo:"+AccNWInfo+" Rating Group:"+RG)
		def param = SessionName+" "+type+" "+A_No+" "+B_No+" "+Time+" "+Duration+" '"+AccNWInfo+"' "+RG
		println "cd ~/SF_Zsmart_Simulator_v.3.0 && python SF-VOICE-Roaming-Simulator.py "+param
		List<String> command = new ArrayList<>();
		command.add("cd ~/SF_Zsmart_Simulator_v.3.0 && python SF-VOICE-Roaming-Simulator.py "+param);
		def a = new remoteUtility()
		def result=a.runRemoteShellReturn3 (GlobalVariable.g_Usage_Server.get('host'), GlobalVariable.g_Usage_Server.get('username'), GlobalVariable.g_Usage_Server.get('password'), command)
		KeywordUtil.logInfo('Voice Roaming Event Result\n'+result)
		def lines = result.split("\n")
		def result_code = lines[lines.size()-2].split(" ")
		if (result_code[result_code.size()-1].trim() == "2001") {
			GlobalVariable.g_CDR_List.add([StringUtils.substringBetween(lines[lines.size()-2],' ### ',' ### '), A_No, custType, GlobalVariable.g_Test_Case_Desc])
		}
	}

	@Keyword
	runSMS_Roaming (String SessionName, String type,String A_No, String B_No, String Time, String ADR_DATA,String custType){
		KeywordUtil.logInfo("Run SMS Roaming "+type+" --> A_No:"+A_No+" B_No:"+B_No+" Adress-Data:"+ADR_DATA+" Time (default=current time):"+Time)
		def param = SessionName+' '+type+' '+A_No+' '+B_No+' '+Time+' '+ADR_DATA
		List<String> command = new ArrayList<>();
		command.add("cd ~/SF_Zsmart_Simulator_v.3.0 && python SF-SMS-Roaming-Simulator.py "+param);
		def a = new remoteUtility()
		def result=a.runRemoteShellReturn3 (GlobalVariable.g_Usage_Server.get('host'), GlobalVariable.g_Usage_Server.get('username'), GlobalVariable.g_Usage_Server.get('password'), command)
		KeywordUtil.logInfo('SMS Roaming Event Result\n'+result)
		def lines = result.split("\n")
		def result_code = lines[lines.size()-2].split(" ")
		if (result_code[result_code.size()-1].trim() == "2001") {
			GlobalVariable.g_CDR_List.add([StringUtils.substringBetween(lines[lines.size()-2],' ### ',' ### '), A_No, custType, GlobalVariable.g_Test_Case_Desc])
		}
	}
	@Keyword
	runUsageSDP (String SessionName, String MDN, String ShortCode, String appID, String price, String Time, String custType){
		KeywordUtil.logInfo("Run Event SDP --> MDN:"+MDN+" Short Code:"+ShortCode+" APP ID:"+appID+" Price:"+price+" Time (default=current time):"+Time)
		def param = SessionName+' '+MDN+' '+ShortCode+' '+appID+' '+(price.toDouble()*100).toInteger().toString()+' '+Time
		List<String> command = new ArrayList<>();
		command.add("cd ~/SF_Zsmart_Simulator_v.3.0 && python SF-SDP-Simulator.py "+param);
		def a = new remoteUtility()
		def result=a.runRemoteShellReturn3 (GlobalVariable.g_Usage_Server.get('host'), GlobalVariable.g_Usage_Server.get('username'), GlobalVariable.g_Usage_Server.get('password'), command)
		KeywordUtil.logInfo('SDP Event Result\n'+result)
		def lines = result.split("\n")
		def result_code = lines[lines.size()-2].split(" ")
		if (result_code[result_code.size()-1].trim() == "2001") {
			GlobalVariable.g_CDR_List.add([StringUtils.substringBetween(lines[lines.size()-2],' ### ',' ### '), MDN, custType, GlobalVariable.g_Test_Case_Desc])
		}
	}
	@Keyword
	def checkCDR(List record) {
		def dbUtility = new DBUtility()
		def bilingCycle
		def queryInput
		if (record.get(0).contains('DATA')){
			bilingCycle = dbUtility.queryDB('BillingDB', "SELECT BILLING_CYCLE_ID FROM BILLING_CYCLE WHERE to_char(CYCLE_BEGIN_DATE,'yyyymm') = '"+new Date().format('yyyyMM')+"' AND BILLING_CYCLE_TYPE_ID  = '"+record.get(2)+"'")
			queryInput = "select count(*) from event_usage_c_"+bilingCycle.BILLING_CYCLE_ID[0]+"@link_rb1 where session_id = '"+record.get(0)+"'"
			//KeywordUtil.logInfo("queryInput :"+queryInput)
			dbUtility.countQueryCheck ('BillingDB',queryInput)
		}
		else if (record.get(0).contains('VOICE')||record.get(0).contains('SMS')){
			bilingCycle = dbUtility.queryDB('BillingDB', "SELECT BILLING_CYCLE_ID FROM BILLING_CYCLE WHERE to_char(CYCLE_BEGIN_DATE,'yyyymm') = '"+new Date().format('yyyyMM')+"' AND BILLING_CYCLE_TYPE_ID  = '"+record.get(2)+"'")
			queryInput = "select count(*) from event_usage_"+bilingCycle.BILLING_CYCLE_ID[0]+"@link_rb2 where session_id = '"+record.get(0)+"'"
			//KeywordUtil.logInfo("queryInput :"+queryInput)
			dbUtility.countQueryCheck ('BillingDB',queryInput)
		}
	}
}
