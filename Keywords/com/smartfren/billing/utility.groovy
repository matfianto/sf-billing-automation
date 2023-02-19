package com.smartfren.billing
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import internal.GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import java.util.Properties
import com.jcraft.jsch.ChannelExec
import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import java.util.regex.Pattern
import java.io.File as File
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import java.awt.image.BufferedImage as BufferedImage
import java.io.BufferedReader as BufferedReader
import javax.imageio.ImageIO as ImageIO


class utility {
	/*
	 * This method created by galih rakasiwa on 09-Oct-2020.
	 * This method to get token from the anonymous login
	 * @Param String for url need to be launch
	 */
	@Keyword
	def openURL(String url) {
		WebUI.openBrowser('')
		//		WebUI.delay(2)
		//		WebUI.deleteAllCookies(FailureHandling.OPTIONAL)
		WebUI.delay(2)
		WebUI.maximizeWindow()
		WebUI.navigateToUrl(url, FailureHandling.STOP_ON_FAILURE)
		WebUI.delay(2)
		WebUI.waitForPageLoad(30, FailureHandling.OPTIONAL)
		WebUI.delay(2)
	}
	/*
	 * This method created by galih rakasiwa on 09-Oct-2020.
	 * This method to get token from the anonymous login
	 * @Param int for how many time scroll require
	 */
	@Keyword
	def scrollPageCapture (int scrollDown){
		int pageInc=0
		while (pageInc < scrollDown) {
			WebUI.scrollToPosition(pageInc, pageInc, FailureHandling.STOP_ON_FAILURE)
			Thread.sleep (GlobalVariable.g_scrollSleep)
			pageInc=pageInc+30
		}
	}
	/*
	 * This method created by galih rakasiwa on 09-Oct-2020.
	 * This method to take capture with file name have date and place on the report folder
	 * @param  fileDate - String paramater to provide file date take from takeCurrentDateTime method
	 */
	@Keyword
	def screenshot (){
		WebUI.delay(1, FailureHandling.OPTIONAL)
		WebUI.takeScreenshot(getExecutionReportDir()+'//Capture//Capture_'+takeCurrentDateTime ()+ '.jpg', FailureHandling.STOP_ON_FAILURE)
	}
	/*
	 * This method created by galih rakasiwa on 09-Oct-2020.
	 * This method to take current date and initially created for screenshot method to provide unique filename with date and time
	 */
	@Keyword
	def takeCurrentDateTime (){
		Date today = new Date()
		String todaysDateTime = today.format('yyyyMMDD')+'_'+today.format('hhmmss')
		return todaysDateTime
	}
	/*
	 * This method created by galih rakasiwa on 09-Oct-2020.
	 * This method get directory during execution running by test suit
	 * This method initially created to support screenshot method while save the file in specific directory
	 */
	@Keyword
	def getExecutionReportDir(){
		def executionReportDir = RunConfiguration.getReportFolder()
		return executionReportDir
	}
	@Keyword
	def getCaptcha(){

		String filePath = RunConfiguration.getProjectDir() + "/Data Files/"
		KeywordUtil.logInfo("filePath : "+filePath)
		WebUI.takeScreenshot(filePath+"\\captchaZSmart.png")
		File file = new File(filePath+"\\captchaZSmart.png")
		BufferedImage  fullImg = ImageIO.read(file);
		// Crop the entire page screenshot to get only element screenshot
		BufferedImage eleScreenshot= fullImg.getSubimage(800,350,95,60);
		ImageIO.write(eleScreenshot, "png", new File(filePath+"\\captchaZSmartCrop.png"))
		WebUI.delay(2)
		String cmd = 'tesseract "'+filePath+'captchaZSmartCrop.png"'+ ' "'+filePath+'captchaZSmart"'
		KeywordUtil.logInfo("cmd : "+cmd)
		Runtime.getRuntime().exec(cmd)
		WebUI.delay(2)
		file = new File(filePath+"\\captchaZSmart.txt")
		BufferedReader reader = new BufferedReader(new FileReader(file))
		String captcha = reader.readLine()
		captcha= captcha.replaceAll("[^0-9]", "")
		KeywordUtil.logInfo("captcha : "+captcha)
		return captcha
	}
	/*
	 * This method created by galih rakasiwa on 29-Nov-2020.
	 * This method to run recurring using param MDN, Recurring Month, Customer Type
	 * Recurring Month values :
	 * Customer Type :
	 * This method initially created to support screenshot method while save the file in specific directory
	 */
	@Keyword
	def recuringCharge(String MDN_No,String RecurringDate, String custType){
		//custType - 1 Prepaid, 2 Postpaid

		def dbUtility = new DBUtility()
		def billingQuery = "SELECT BILLING_CYCLE_ID FROM BILLING_CYCLE WHERE to_char(CYCLE_BEGIN_DATE,'yyyymm') = '"+RecurringDate.substring(0,RecurringDate.length()-2 )+"' AND BILLING_CYCLE_TYPE_ID  = '"+custType+"'"
		KeywordUtil.logInfo('billingQuery' + billingQuery)
		def bilingCycle = dbUtility.queryDB('BillingDB',billingQuery )
		KeywordUtil.logInfo('bilingCycle.BILLING_CYCLE_ID[0]' + bilingCycle.BILLING_CYCLE_ID[0])
		String queryInput = "SELECT r.RE_NAME, s.acc_nbr, cdr.subs_id, cdr.event_inst_id, cdr.recurring_re_type, cdr.re_id, cdr.prod_spec_id, "+
				"cdr.price_plan_id, cdr.billing_cycle_id, cdr.event_begin_time, cdr.event_end_time, cdr.bal_update_date, cdr.state,"+
				"cdr.state_date, cdr.created_date, cdr.charge1, cdr.charge2, cdr.charge3, cdr.charge4 "+
				"FROM event_recurring_"+bilingCycle.BILLING_CYCLE_ID[0]+"@link_rb1 cdr, re r, subs s "+
				"WHERE s.ACC_NBR = '"+MDN_No.subSequence(2, MDN_No.length())+"' AND cdr.re_id = r.re_id AND cdr.subs_id = s.subs_id ORDER BY bal_id1 DESC"
		KeywordUtil.logInfo('Recurring Charge Query :' + queryInput)
		dbUtility.displayQueryResult ('BillingDB',queryInput)

	}
	/*
	 * This method created by galih rakasiwa on 29-Nov-2020.
	 * This method to run recurring using param MDN, Service Code, Recuring Date, Recuring Type
	 * Service Code valid values : 
	 * Recuring Date valid values : 
	 * Recuring Type valid values : 
	 * This method initially created to support screenshot method while save the file in specific directory
	 */
	@Keyword
	def runRecurring(String MDN_No, String ServiceCode_No, String RecurringDate, String RecurringType){
		def dbUtility = new DBUtility()
		def query = "select 'RecurrRate -e '||rpp.recurring_re_type||' -b '"+
				"||acc.billing_cycle_type_id||' -d '||'"+RecurringDate+"'||' -s '|| s.subs_id ||' -L 5' as Command from subs s, prod p, acct acc, subs_upp_inst si, re_pp_recurring rpp where (1=1)"+
				"and s.acc_nbr='"+ MDN_No.subSequence(2, MDN_No.length())+"' and p.prod_id=s.subs_id and acc.acct_id=s.acct_id and si.subs_id=s.subs_id and si.price_plan_id in "+
				"(select price_plan_id from price_plan where price_plan_code='"+ServiceCode_No+"') and (si.exp_date>sysdate or si.exp_date is null) and rpp.price_plan_id=si.price_plan_id and rpp.recurring_re_type = '"+RecurringType+"'"
		KeywordUtil.logInfo('Recurring query' + query)
		def queryCommand = dbUtility.queryDB('BillingDB',query)
		KeywordUtil.logInfo('Recurring Command' + queryCommand.COMMAND[0])
		if (queryCommand.COMMAND[0]!=null){
			def command = "source ~/.bash_profile ; "+queryCommand.COMMAND[0]
			def remoteRecurring = new remoteUtility()
			remoteRecurring.runRemoteCommandJob(GlobalVariable.g_Recurring_Server.get('host'), GlobalVariable.g_Recurring_Server.get('username'), GlobalVariable.g_Recurring_Server.get('password'), GlobalVariable.g_Recurring_Server.get('port'), command, "Program RecurrRate end")
		}
		else {
			KeywordUtil.logInfo('Query to get Reccuring Command null  - Please check the input data/manual check on DB')
		}
	}
	@Keyword
	def getNotification(String MDN_No, int startDate){
		WS.delay(20)
		int endDate = System.currentTimeMillis()
		def dbUtility = new DBUtility()
		def query = "SELECT CREATED_DATE, ACC_NBR ,MSG, MSG_PARAM FROM advice_his where acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+
				"' AND TO_CHAR(CREATED_DATE,'YYYY-MM-DD HH24:MI:SS') BETWEEN TO_CHAR(SYSDATE-("+(endDate-startDate)+"/100/24/60/60), 'YYYY-MM-DD HH24:MI:SS') AND TO_CHAR(SYSDATE,'YYYY-MM-DD HH24:MI:SS')"
		dbUtility.displayQueryResult('BillingDB',query)
	}
	@Keyword
	def updateMDNstatusInitial(String MDN_No){
		def dbUtility = new DBUtility()

		def subsIDQuery = "select subs_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('subsIDQuery' + subsIDQuery)
		def subsID = dbUtility.queryDB('BillingDB',subsIDQuery )
		KeywordUtil.logInfo('subsID.subs_id[0]' + subsID.subs_id[0])
		def query = "update prod a set a.prod_state = 'G', block_reason = '00000000000000' where a.prod_id='"+subsID.subs_id[0]+"' and prod_state not in ('G','J','B')"
		dbUtility.executeUpdateQuery('BillingDB',query)
	}
	@Keyword
	def updateMDNstatusActive(String MDN_No){
		def dbUtility = new DBUtility()

		def subsIDQuery = "select subs_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('subsIDQuery' + subsIDQuery)
		def subsID = dbUtility.queryDB('BillingDB',subsIDQuery )
		KeywordUtil.logInfo('subsID.subs_id[0]' + subsID.subs_id[0])
		def query = "update prod a set a.prod_state = 'A', block_reason = '00000000000000' where a.prod_id='"+subsID.subs_id[0]+"' and prod_state not in ('G','J','B')"
		int row = dbUtility.executeUpdateQuery('BillingDB',query)
		if(row>0) {
			KeywordUtil.logInfo('Update MDN Status Active Success ' + row + ' row updated')
		}else {
			KeywordUtil.logInfo('Update MDN Status Active Failed ' + row + ' row updated')
		}
	}
	@Keyword
	def updateMDNstatusB1W(String MDN_No){
		def dbUtility = new DBUtility()

		def subsIDQuery = "select subs_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('subsIDQuery' + subsIDQuery)
		def subsID = dbUtility.queryDB('BillingDB',subsIDQuery )
		KeywordUtil.logInfo('subsID.subs_id[0]' + subsID.subs_id[0])
		def query = "update prod a set a.prod_state = 'D', block_reason = '00100000000000' where a.prod_id='"+subsID.subs_id[0]+"' and prod_state not in ('G','J','B')"
		int row = dbUtility.executeUpdateQuery('BillingDB',query)
		if(row>0) {
			KeywordUtil.logInfo('Update MDN Status B1W Success ' + row + ' row updated')
		}else {
			KeywordUtil.logInfo('Update MDN Status B1W Failed ' + row + ' row updated')
		}
	}
	@Keyword
	def updateMDNstatusB2W(String MDN_No){
		def dbUtility = new DBUtility()

		def subsIDQuery = "select subs_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('subsIDQuery' + subsIDQuery)
		def subsID = dbUtility.queryDB('BillingDB',subsIDQuery )
		KeywordUtil.logInfo('subsID.subs_id[0]' + subsID.subs_id[0])
		def query = "update prod a set a.prod_state = 'E', block_reason = '00200000000000' where a.prod_id='"+subsID.subs_id[0]+"' and prod_state not in ('G','J','B')"
		int row = dbUtility.executeUpdateQuery('BillingDB',query)
		if(row>0) {
			KeywordUtil.logInfo('Update MDN Status B2W Success ' + row + ' row updated')
		}else {
			KeywordUtil.logInfo('Update MDN Status B2W Failed ' + row + ' row updated')
		}
	}
	@Keyword
	def getPricePlanPerMDN(String MDN_No){
		def dbUtility = new DBUtility()

		def subsIDQuery = "select subs_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('subsIDQuery' + subsIDQuery)
		def subsID = dbUtility.queryDB('BillingDB',subsIDQuery )
		KeywordUtil.logInfo('subsID.subs_id[0]' + subsID.subs_id[0])
		def ppQuery = "select pp.price_plan_id, pp.price_plan_name, si.eff_date, si.exp_date, si.created_date "+
				"from subs_upp_inst si, price_plan pp, subs_price_plan spp, price_plan_type ppt "+
				"where (1=1) "+
				"and pp.price_plan_id=si.price_plan_id "+
				"and si.subs_id='"+subsID.subs_id[0]+"' "+
				"and ppt.price_plan_type_name='Individual' "+
				"and (si.exp_date>sysdate or si.exp_date is null) "+
				"and spp.price_plan_id=pp.price_plan_id "+
				"and ppt.price_plan_type=spp.price_plan_type "
		dbUtility.displayQueryResult('BillingDB',ppQuery)
	}
	@Keyword
	def updateBalance_notused(String MDN_No){
		def dbUtility = new DBUtility()

		def acctIDQuery = "select acct_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('acctIDQuery' + acctIDQuery)
		def acctID = dbUtility.queryDB('BillingDB',acctIDQuery )
		KeywordUtil.logInfo('acctID.acct_id[0]' + acctID.acct_id[0])
		def query = "update bal set CONSUME_BAL = 300000 where acct_res_id=21 and acct_id="+acctID.acct_id[0]+""

		dbUtility.executeUpdateQuery('BillingMDBTT',query)
	}
	@Keyword
	def runMDBTT(String MDN_No){

		def dbUtility = new DBUtility()

		def acctIDQuery = "select acct_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('acctIDQuery' + acctIDQuery)
		def acctID = dbUtility.queryDB('BillingDB',acctIDQuery )
		KeywordUtil.logInfo('acctID.acct_id[0]' + acctID.acct_id[0])

		def queryBal = "update bal set CONSUME_BAL = 300000 where acct_res_id=21 and acct_id="+acctID.acct_id[0]+""
		def command = "mdbtt"

		def remoteMDBTT = new remoteUtility()

		//remoteMDBTT.runRemoteCommandLine(GlobalVariable.g_Recurring_Server.get('host'), GlobalVariable.g_Recurring_Server.get('username'), GlobalVariable.g_Recurring_Server.get('password'), GlobalVariable.g_Recurring_Server.get('port'), command)

		remoteMDBTT.runRemoteCommandLineMDBTT(GlobalVariable.g_Recurring_Server.get('host'), GlobalVariable.g_Recurring_Server.get('username'), GlobalVariable.g_Recurring_Server.get('password'), GlobalVariable.g_Recurring_Server.get('port'), command, queryBal)

	}
	@Keyword
	def deletePartitionMDBTT(String MDN_No){
		def dbUtility = new DBUtility()
		def acctIDQuery = "select acct_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('acctIDQuery' + acctIDQuery)
		def acctID = dbUtility.queryDB('BillingDB',acctIDQuery )
		KeywordUtil.logInfo('acctID.acct_id[0]' + acctID.acct_id[0])
		def command = "DeletePartition "+acctID.acct_id[0]
		def remoteRecurring = new remoteUtility()
		remoteRecurring.runRemoteSh(GlobalVariable.g_Recurring_Server.get('host'),GlobalVariable.g_Recurring_Server.get('username'),GlobalVariable.g_Recurring_Server.get('password'),GlobalVariable.g_Recurring_Server.get('port'),command)
	}
	@Keyword
	def deleteRecurringMDBTT(String MDN_No){
		def dbUtility = new DBUtility()
		def subsIDQuery = "select subs_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('subsIDQuery' + subsIDQuery)
		def subsID = dbUtility.queryDB('BillingDB',subsIDQuery )
		KeywordUtil.logInfo('subsID.subs_id[0]' + subsID.subs_id[0])
		def command = "DeleteRecurring "+subsID.subs_id[0]
		def remoteRecurring = new remoteUtility()
		remoteRecurring.runRemoteSh(GlobalVariable.g_Recurring_Server.get('host'),GlobalVariable.g_Recurring_Server.get('username'),GlobalVariable.g_Recurring_Server.get('password'),GlobalVariable.g_Recurring_Server.get('port'),command)
	}
	@Keyword
	def deleteACMMDBTT(String MDN_No){
		def dbUtility = new DBUtility()
		def subsIDQuery = "select subs_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('subsIDQuery' + subsIDQuery)
		def subsID = dbUtility.queryDB('BillingDB',subsIDQuery )
		KeywordUtil.logInfo('subsID.subs_id[0]' + subsID.subs_id[0])
		def command = "DeleteACM "+subsID.subs_id[0]
		def remoteRecurring = new remoteUtility()
		remoteRecurring.runRemoteSh(GlobalVariable.g_Recurring_Server.get('host'),GlobalVariable.g_Recurring_Server.get('username'),GlobalVariable.g_Recurring_Server.get('password'),GlobalVariable.g_Recurring_Server.get('port'),command)
	}
	@Keyword
	def removeIPPMDBTT(String MDN_No){
		def dbUtility = new DBUtility()
		//get subs id
		def subsIDQuery = "select subs_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('subsIDQuery' + subsIDQuery)
		def subsID = dbUtility.queryDB('BillingDB',subsIDQuery )
		KeywordUtil.logInfo('subsID.subs_id[0]' + subsID.subs_id[0])
		//get acct id
		def acctIDQuery = "select acct_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('acctIDQuery' + acctIDQuery)
		def acctID = dbUtility.queryDB('BillingDB',acctIDQuery )
		KeywordUtil.logInfo('acctID.acct_id[0]' + acctID.acct_id[0])

		//def command = "RemoveIPP "+subsID.subs_id[0]
		def command = "RemoveIPP "+subsID.subs_id[0]+" "+acctID.acct_id[0]
		def remoteRecurring = new remoteUtility()
		remoteRecurring.runRemoteSh(GlobalVariable.g_Recurring_Server.get('host'),GlobalVariable.g_Recurring_Server.get('username'),GlobalVariable.g_Recurring_Server.get('password'),GlobalVariable.g_Recurring_Server.get('port'),command)
	}
	@Keyword
	def selectPartitionMDBTT(String MDN_No){
		def dbUtility = new DBUtility()
		def subsIDQuery = "select subs_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('subsIDQuery' + subsIDQuery)
		def subsID = dbUtility.queryDB('BillingDB',subsIDQuery )
		KeywordUtil.logInfo('subsID.subs_id[0]' + subsID.subs_id[0])
		def command = "SelectPartition "+subsID.subs_id[0]
		def remoteRecurring = new remoteUtility()
		remoteRecurring.runRemoteSh(GlobalVariable.g_Recurring_Server.get('host'),GlobalVariable.g_Recurring_Server.get('username'),GlobalVariable.g_Recurring_Server.get('password'),GlobalVariable.g_Recurring_Server.get('port'),command)
	}
	@Keyword
	def selectSubsACMCycleMDBTT(String MDN_No){
		def dbUtility = new DBUtility()
		def subsIDQuery = "select subs_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('subsIDQuery' + subsIDQuery)
		def subsID = dbUtility.queryDB('BillingDB',subsIDQuery )
		KeywordUtil.logInfo('subsID.subs_id[0]' + subsID.subs_id[0])
		def command = "SelectSubsACMCycle "+subsID.subs_id[0]
		def remoteRecurring = new remoteUtility()
		remoteRecurring.runRemoteSh(GlobalVariable.g_Recurring_Server.get('host'),GlobalVariable.g_Recurring_Server.get('username'),GlobalVariable.g_Recurring_Server.get('password'),GlobalVariable.g_Recurring_Server.get('port'),command)
	}
	@Keyword
	def selectSubsACMMDBTT(String MDN_No){
		def dbUtility = new DBUtility()
		def subsIDQuery = "select subs_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('subsIDQuery' + subsIDQuery)
		def subsID = dbUtility.queryDB('BillingDB',subsIDQuery )
		KeywordUtil.logInfo('subsID.subs_id[0]' + subsID.subs_id[0])
		def command = "SelectSubsACM "+subsID.subs_id[0]
		def remoteRecurring = new remoteUtility()
		remoteRecurring.runRemoteSh(GlobalVariable.g_Recurring_Server.get('host'),GlobalVariable.g_Recurring_Server.get('username'),GlobalVariable.g_Recurring_Server.get('password'),GlobalVariable.g_Recurring_Server.get('port'),command)
	}
	@Keyword
	def selectSubsACMDailyMDBTT(String MDN_No){
		def dbUtility = new DBUtility()
		def subsIDQuery = "select subs_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('subsIDQuery' + subsIDQuery)
		def subsID = dbUtility.queryDB('BillingDB',subsIDQuery )
		KeywordUtil.logInfo('subsID.subs_id[0]' + subsID.subs_id[0])
		def command = "SelectSubsACMDaily "+subsID.subs_id[0]
		def remoteRecurring = new remoteUtility()
		remoteRecurring.runRemoteSh(GlobalVariable.g_Recurring_Server.get('host'),GlobalVariable.g_Recurring_Server.get('username'),GlobalVariable.g_Recurring_Server.get('password'),GlobalVariable.g_Recurring_Server.get('port'),command)
	}
	@Keyword
	def updateACMCycleValueMDBTT(String MDN_No, String value, String acm_cycle_type_id){
		def dbUtility = new DBUtility()
		def subsIDQuery = "select subs_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('subsIDQuery' + subsIDQuery)
		def subsID = dbUtility.queryDB('BillingDB',subsIDQuery )
		KeywordUtil.logInfo('subsID.subs_id[0]' + subsID.subs_id[0])
		def command = "UpdateACMCycleValue "+subsID.subs_id[0]+" "+value+" "+acm_cycle_type_id
		def remoteRecurring = new remoteUtility()
		remoteRecurring.runRemoteSh(GlobalVariable.g_Recurring_Server.get('host'),GlobalVariable.g_Recurring_Server.get('username'),GlobalVariable.g_Recurring_Server.get('password'),GlobalVariable.g_Recurring_Server.get('port'),command)
	}
	@Keyword
	def updateSubsACMValueMDBTT(String MDN_No, String value, String resourceID){
		def dbUtility = new DBUtility()
		def subsIDQuery = "select subs_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('subsIDQuery' + subsIDQuery)
		def subsID = dbUtility.queryDB('BillingDB',subsIDQuery )
		KeywordUtil.logInfo('subsID.subs_id[0]' + subsID.subs_id[0])
		def command = "UpdateSubsACMValue "+subsID.subs_id[0]+" "+resourceID+" "+value
		def remoteRecurring = new remoteUtility()
		remoteRecurring.runRemoteSh(GlobalVariable.g_Recurring_Server.get('host'),GlobalVariable.g_Recurring_Server.get('username'),GlobalVariable.g_Recurring_Server.get('password'),GlobalVariable.g_Recurring_Server.get('port'),command)
	}
	@Keyword
	def selectACMCycleMDBTT(String MDN_No){
		def dbUtility = new DBUtility()
		def subsIDQuery = "select subs_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('subsIDQuery' + subsIDQuery)
		def subsID = dbUtility.queryDB('BillingDB',subsIDQuery )
		KeywordUtil.logInfo('subsID.subs_id[0]' + subsID.subs_id[0])
		def command = "SelectACMCycle "+subsID.subs_id[0]
		def remoteRecurring = new remoteUtility()
		remoteRecurring.runRemoteSh(GlobalVariable.g_Recurring_Server.get('host'),GlobalVariable.g_Recurring_Server.get('username'),GlobalVariable.g_Recurring_Server.get('password'),GlobalVariable.g_Recurring_Server.get('port'),command)
	}
	@Keyword
	def deletePartitionAcctResIDNotInMDBTT(String MDN_No, String Acct_Res_ID){
		def dbUtility = new DBUtility()
		def acctIDQuery = "select acct_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('acctIDQuery' + acctIDQuery)
		def acctID = dbUtility.queryDB('BillingDB',acctIDQuery )
		KeywordUtil.logInfo('acctID.acct_id[0]' + acctID.acct_id[0])
		def command = "DeletePartitionAcctResIDNotIn "+acctID.acct_id[0]+" "+Acct_Res_ID
		def remoteRecurring = new remoteUtility()
		remoteRecurring.runRemoteSh(GlobalVariable.g_Recurring_Server.get('host'),GlobalVariable.g_Recurring_Server.get('username'),GlobalVariable.g_Recurring_Server.get('password'),GlobalVariable.g_Recurring_Server.get('port'),command)
	}
	@Keyword
	def runAsyncallJob(String MDN_No){
		def dbUtility = new DBUtility()
		def subsIDQuery = "select subs_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('subsIDQuery ' + subsIDQuery)
		def subsID = dbUtility.queryDB('BillingDB',subsIDQuery )
		KeywordUtil.logInfo('subsID.acct_id[0]' + subsID.subs_id[0])
		def command = 'FlexibleQuery "update asyn_call set state=\'A\' where subs_id='+subsID.subs_id[0]+';"'
		def remoteRecurring = new remoteUtility()
		remoteRecurring.runRemoteSh(GlobalVariable.g_Recurring_Server.get('host'),GlobalVariable.g_Recurring_Server.get('username'),GlobalVariable.g_Recurring_Server.get('password'),GlobalVariable.g_Recurring_Server.get('port'),command)
		List<String> commands = new ArrayList<>()
		commands.add('sh jclc.sh AsynCall');
		def result= remoteRecurring.runRemoteShellReturn2('10.17.85.25', 'web', 'web', commands)
		KeywordUtil.logInfo('AsynCall Job Trigerred\n'+result)
	}
	@Keyword
	def updateBalDate(String MDN_No, String type, String res_id, String FormatedDate){
		def dbUtility = new DBUtility()
		def acctIDQuery = "select acct_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('acctIDQuery ' + acctIDQuery)
		def acctID = dbUtility.queryDB('BillingDB',acctIDQuery )
		KeywordUtil.logInfo('subsID.acct_id[0]' + acctID.acct_id[0])
		String date = FormatedDate.replace("-", "")

		if(date.length()!=14) {
			KeywordUtil.logInfo('Invalid date length inputted !!!')
		}else {
			def command = ''
			if(type== '0') {
				KeywordUtil.logInfo('Updating bal of '+MDN_No+' set eff_date to '+FormatedDate+' where res_id '+res_id)
				command = 'FlexibleQuery "update bal set eff_date=\''+date+'\' where acct_id='+acctID.acct_id[0]+' and acct_res_id='+res_id+';"'
			}else {
				KeywordUtil.logInfo('Updating bal of '+MDN_No+' set exp_date to '+FormatedDate+' where res_id '+res_id)
				command = 'FlexibleQuery "update bal set exp_date=\''+date+'\' where acct_id='+acctID.acct_id[0]+' and acct_res_id='+res_id+';"'
			}

			def remoteRecurring = new remoteUtility()
			remoteRecurring.runRemoteSh(GlobalVariable.g_Recurring_Server.get('host'),GlobalVariable.g_Recurring_Server.get('username'),GlobalVariable.g_Recurring_Server.get('password'),GlobalVariable.g_Recurring_Server.get('port'),command)
		}
	}
	@Keyword
	def updateBalance(String MDN_No, String res_id, String gross_bal, String consume_bal, String init_bal, String reserve_bal){
		def dbUtility = new DBUtility()
		def acctIDQuery = "select acct_id from subs s where s.acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"'"
		KeywordUtil.logInfo('acctIDQuery ' + acctIDQuery)
		def acctID = dbUtility.queryDB('BillingDB',acctIDQuery )
		KeywordUtil.logInfo('subsID.acct_id[0]' + acctID.acct_id[0])
		List<String> balance = new ArrayList<String>()
		try{
			if(gross_bal.trim()!='') {
				balance.add('GROSS_BAL = '+gross_bal.toInteger())
			}
			if(consume_bal.trim()!='') {
				balance.add('CONSUME_BAL = '+consume_bal.toInteger())
			}
			if(init_bal.trim()!='') {
				balance.add('INIT_BAL = '+init_bal.toInteger())
			}
			if(reserve_bal.trim()!='') {
				balance.add('RESERVE_BAL = '+reserve_bal.toInteger())
			}
			if(balance.size()>0) {
				String query_balance=''
				for(String i : balance) {
					query_balance=query_balance+i+','
				}
				query_balance=query_balance.substring(0, query_balance.length() - 1)
				def command = 'FlexibleQuery "update bal set '+query_balance+' where acct_id='+acctID.acct_id[0]+' and acct_res_id='+res_id+';"'
				def remoteRecurring = new remoteUtility()
				remoteRecurring.runRemoteSh(GlobalVariable.g_Recurring_Server.get('host'),GlobalVariable.g_Recurring_Server.get('username'),GlobalVariable.g_Recurring_Server.get('password'),GlobalVariable.g_Recurring_Server.get('port'),command)
			}else {
				KeywordUtil.markFailed('Gross ball,Consume_bal,Init_bal,Reserve_bal is empty, please check the input')
			}
		}catch(NumberFormatException e) {
			KeywordUtil.markFailed('Gross ball/Consume_bal/Init_bal/Reserve_bal format is incorrect')
		}
	}
	@Keyword
	def GetLastSequenceBillID() {
		def dbUtility = new DBUtility()
		def last_bill_id = dbUtility.queryDB('BillingDB', "SELECT BILL_ID from bill where BILL_ID BETWEEN 100000 AND 200000 order by BILL_ID DESC FETCH FIRST 1 ROWS ONLY")

		int bill_id = 0
		try {
			bill_id = last_bill_id[0].get('BILL_ID').toInteger()+1
		}catch(Exception e){
			bill_id = 100001
		}
		println 'Bill_ID : '+bill_id
		return bill_id
	}
	@Keyword
	def createBillPostpaid(String MDN, String bill_amount) {
		def dbUtility = new DBUtility()
		def acctID_query = dbUtility.queryDB('BillingDB',"select acct_id from subs s where s.acc_nbr='"+MDN.subSequence(2, MDN.length())+"'" )
		def acctID = ''
		try {
			acctID = acctID_query[0].get('ACCT_ID')
			KeywordUtil.logInfo("Account ID for MDN "+MDN+" : "+acctID)
		}catch(Exception e){
			KeywordUtil.markFailed("Error when retrieving acctID")
		}
		def query = "select BILLING_CYCLE_ID from billing_cycle where billing_cycle_type_id in(SELECT BILLING_CYCLE_TYPE_ID FROM acct WHERE POSTPAID ='Y' AND ACCT_ID="+acctID+") and add_months(sysdate,-1) between cycle_begin_date and cycle_end_date AND ROWNUM=1"
		def bill_cycle_id_query = dbUtility.queryDB('BillingDB', query)
		String bill_cycle_id_old=''
		try {
			bill_cycle_id_old = bill_cycle_id_query[0].get('BILLING_CYCLE_ID')
			KeywordUtil.logInfo("Billing Cycle ID for Previous Month : "+bill_cycle_id_old)
		}catch(Exception e){
			KeywordUtil.markFailed("There is some error when selecting billing cycle_id old month")
		}
		query = "select BILLING_CYCLE_ID from billing_cycle where billing_cycle_type_id in(SELECT BILLING_CYCLE_TYPE_ID FROM acct WHERE POSTPAID ='Y' AND ACCT_ID="+acctID+") and SYSDATE between cycle_begin_date and cycle_end_date AND ROWNUM=1"
		bill_cycle_id_query = dbUtility.queryDB('BillingDB', query)
		String bill_cycle_id_new=''
		try {
			bill_cycle_id_new = bill_cycle_id_query[0].get('BILLING_CYCLE_ID')
			KeywordUtil.logInfo("Billing Cycle ID for Current Month : "+bill_cycle_id_new)
		}catch(Exception e){
			KeywordUtil.markFailed("There is some error when selecting billing cycle_id new month")
		}
		query = "SELECT count(*) FROM bill WHERE ACCT_ID="+acctID+" AND BILLING_CYCLE_ID = "+bill_cycle_id_old
		bill_cycle_id_query = dbUtility.queryDB('BillingDB', query)
		String old_count=''
		try {
			old_count = bill_cycle_id_query[0].get('COUNT(*)')
			KeywordUtil.logInfo("Count BILL_ID of Previous Month : "+old_count)
		}catch(Exception e){
			KeywordUtil.markFailed("There is some error when counting billing cycle_id old month")
		}
		query = "SELECT count(*) FROM bill WHERE ACCT_ID="+acctID+" AND BILLING_CYCLE_ID = "+bill_cycle_id_new
		bill_cycle_id_query = dbUtility.queryDB('BillingDB', query)
		String new_count=''
		try {
			new_count = bill_cycle_id_query[0].get('COUNT(*)')
			KeywordUtil.logInfo("Count BILL_ID of Current Month : "+new_count)
		}catch(Exception e){
			KeywordUtil.markFailed("There is some error when counting billing cycle_id new month")
		}
		def query_update = ''
		if(old_count == '1') {
			query_update = "UPDATE bill SET PRE_BALANCE = 0, DUE = "+bill_amount+", RECV_CHARGE = 0 WHERE ACCT_ID="+acctID+" AND BILLING_CYCLE_ID = "+bill_cycle_id_old
			dbUtility.executeUpdateQuery('BillingDB', query_update)
			KeywordUtil.logInfo("Updating Previous Month Bill of ACCT_ID="+acctID+", PRE_BALANCE=0,DUE="+bill_amount+",RECV_CHARGE=0 success")
		}else if(old_count == '0') {
			int bill_id = GetLastSequenceBillID()
			query_update = "INSERT INTO bill (BILL_ID ,BILL_NBR, ACCT_ID , BILLING_CYCLE_ID,PRE_BALANCE,DUE,ROUTING_ID) VALUES ("+bill_id+","+bill_id+","+acctID+","+bill_cycle_id_old+",0,"+bill_amount+",0)"
			dbUtility.executeUpdateQuery('BillingDB', query_update)
			KeywordUtil.logInfo("Insert Previous Month Bill of ACCT_ID="+acctID+", BILL_ID="+bill_id+" PRE_BALANCE=0,DUE="+bill_amount+",RECV_CHARGE=0 success")
		}else {
			KeywordUtil.markFailed("Error with query update or insert old_count is null")
		}
		if(new_count == '1') {
			query_update = "UPDATE bill SET PRE_BALANCE = "+bill_amount+", DUE = 0, RECV_CHARGE = 0 WHERE ACCT_ID="+acctID+" AND BILLING_CYCLE_ID = "+bill_cycle_id_new
			dbUtility.executeUpdateQuery('BillingDB', query_update)
			KeywordUtil.logInfo("Updating Current Month Bill of ACCT_ID="+acctID+", PRE_BALANCE="+bill_amount+",DUE=0,RECV_CHARGE=0 success")
		}else if(new_count == '0') {
			int bill_id = GetLastSequenceBillID()
			query_update = "INSERT INTO bill (BILL_ID ,BILL_NBR, ACCT_ID , BILLING_CYCLE_ID,PRE_BALANCE,DUE,ROUTING_ID) VALUES ("+bill_id+","+bill_id+","+acctID+","+bill_cycle_id_new+","+bill_amount+",0,0)"
			dbUtility.executeUpdateQuery('BillingDB', query_update)
			KeywordUtil.logInfo("Insert Current Month Bill of ACCT_ID="+acctID+", BILL_ID="+bill_id+" PRE_BALANCE="+bill_amount+",DUE=0,RECV_CHARGE=0 success")
		}else {
			KeywordUtil.markFailed("Error with query update or insert new_count is null")
		}
		KeywordUtil.logInfo("Update Balance MDBTT Main Balance(res_id=1) of ACCT_ID="+acctID+", Gross Balance="+bill_amount+", Reserve Balance = 0, Consume Balance = 0")
		updateBalance(MDN, '3', bill_amount, '0', '', '0')
	}
	@Keyword
	def updateCreditLimit(String MDN, String CL_Amount) {
		def dbUtility = new DBUtility()
		String query_update = "UPDATE CC_SUBS SET CREDIT_LIMIT = '"+CL_Amount+"00' WHERE SUBS_ID IN (SELECT SUBS_ID FROM SUBS WHERE ACC_NBR = '"+MDN.subSequence(2, MDN.length())+"')"
		dbUtility.executeUpdateQuery('BillingDB', query_update)
		KeywordUtil.logInfo("Update Credit Limit MDN :"+MDN+" with Amount:"+CL_Amount)
	}
	@Keyword
	def deleteUsageCL(String MDN) {
		def dbUtility = new DBUtility()
		def subsIDQuery = "select subs_id from subs s where s.acc_nbr='"+MDN.subSequence(2, MDN.length())+"'"
		KeywordUtil.logInfo('subsIDQuery' + subsIDQuery)
		def subsID = dbUtility.queryDB('BillingDB',subsIDQuery )
		String command = 'FlexibleQueryConfirm "delete from cc_subs_acm where SUBS_ID = '+subsID.subs_id[0]+';"'
		def remoteRecurring = new remoteUtility()
		remoteRecurring.runRemoteSh(GlobalVariable.g_Recurring_Server.get('host'),GlobalVariable.g_Recurring_Server.get('username'),GlobalVariable.g_Recurring_Server.get('password'),GlobalVariable.g_Recurring_Server.get('port'),command)
		KeywordUtil.logInfo("Delete Usage ACM Credit Limit for MDN :"+MDN)
	}
	@Keyword
	def updateIPPdate(String MDN, String Service_Code, String Eff_date, String Exp_date) {
		def dbUtility = new DBUtility()
		List<String> ippdate = new ArrayList<String>()
		try{
			if(Eff_date.trim()!='') {
				ippdate.add("EFF_DATE = TO_DATE('"+Eff_date+"','yyyy-mm-dd-hh24-mi-ss')")
			}
			if(Exp_date.trim()!='') {
				ippdate.add("EXP_DATE = TO_DATE('"+Exp_date+"','yyyy-mm-dd-hh24-mi-ss')")
			}
			if(ippdate.size()>0) {
				String query_update=''
				for(String i : ippdate) {
					query_update=query_update+i+','
				}
				query_update=query_update.substring(0, query_update.length() - 1)
				String query_final = "UPDATE SUBS_UPP_INST SET "+query_update+
						" WHERE PRICE_PLAN_ID IN (SELECT PRICE_PLAN_ID FROM PRICE_PLAN WHERE PRICE_PLAN_CODE = '"+Service_Code+"')"+
						" AND SUBS_ID IN (SELECT SUBS_ID FROM SUBS WHERE ACC_NBR = '"+MDN.subSequence(2, MDN.length())+"') and (exp_date>sysdate or exp_date is null)"
				dbUtility.executeUpdateQuery('BillingDB', query_final)
				KeywordUtil.logInfo("Success Update IPP Date MDN :"+MDN+" with Service Code :"+Service_Code+" Effective Date :"+Eff_date+" or Expired Date :"+Exp_date)
			}else {
				KeywordUtil.markFailed('Effective Date and Expired Date is empty, please check the input')
			}
		}catch(NumberFormatException e) {
			KeywordUtil.markFailed('Effective Date or Expired Date format is incorrect')
		}
	}
}