import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil


//CustomKeywords.'com.smartfren.billing.APIUtility.getSubInfoDetail'(xMDN_No)
//CustomKeywords.'com.smartfren.billing.APIUtility.addIPP'(xMDN_No,xServiceCode_No)
//WebUI.delay(5)
//CustomKeywords.'com.smartfren.billing.APIUtility.getSubInfoDetail'(xMDN_No)
//WebUI.delay(20)
//CustomKeywords.'com.smartfren.billing.APIUtility.removeIPP'(xMDN_No, xServiceCode_No)
//WebUI.delay(5)
//CustomKeywords.'com.smartfren.billing.APIUtility.getSubInfoDetail'(xMDN_No)

//String acc_nbr_MDN =  '6288295294326'
//acc_nbr_MDN.subSequence(2, acc_nbr_MDN.length()-1)
//KeywordUtil.logInfo('acc_nbr_MDN' + acc_nbr_MDN.subSequence(2, acc_nbr_MDN.length()))
//String service_code2 = 'VOL100RBA'

/*
String queryInput = "select 'RecurrRate -e '||rpp.recurring_re_type||' -b '"+
"||acc.billing_cycle_type_id||' -d '||'20201230'||' -s '|| s.subs_id ||' -L 5' as Command from subs s, prod p, acct acc, subs_upp_inst si, re_pp_recurring rpp where (1=1)"+
"and s.acc_nbr='"+ acc_nbr_MDN.subSequence(2, acc_nbr_MDN.length())+"' and p.prod_id=s.subs_id and acc.acct_id=s.acct_id and si.subs_id=s.subs_id and si.price_plan_id in "+
"(select price_plan_id from price_plan where price_plan_code='"+service_code2+"')and (si.exp_date>sysdate or si.exp_date is null) and rpp.price_plan_id=si.price_plan_id"
KeywordUtil.logInfo('queryInput :' + queryInput)
CustomKeywords.'com.smartfren.billing.DBUtility.displayQueryResult'('BillingDB',queryInput)

def queryResult = CustomKeywords.'com.smartfren.billing.DBUtility.queryDB'('BillingDB',"select 'RecurrRate -e '||rpp.recurring_re_type||' -b '"+
"||acc.billing_cycle_type_id||' -d '||'20201230'||' -s '|| s.subs_id ||' -L 5' as Command from subs s, prod p, acct acc, subs_upp_inst si, re_pp_recurring rpp where (1=1)"+
"and s.acc_nbr='"+ acc_nbr_MDN.subSequence(2, acc_nbr_MDN.length())+"' and p.prod_id=s.subs_id and acc.acct_id=s.acct_id and si.subs_id=s.subs_id and si.price_plan_id in "+
"(select price_plan_id from price_plan where price_plan_code='"+service_code2+"')and (si.exp_date>sysdate or si.exp_date is null) and rpp.price_plan_id=si.price_plan_id")
KeywordUtil.logInfo('queryResult' + queryResult.COMMAND[0])
def command = "source ~/.bash_profile; cd bin; pwd ; "+queryResult.COMMAND[0]
CustomKeywords.'com.smartfren.billing.remoteUtility.runRemoteCommandJob'("10.17.85.26", "ocs", "ocs", 22, command, "Program RecurrRate end")
*/
//
String RecurringType = '6'
CustomKeywords.'com.smartfren.billing.APIUtility.getSubInfoDetail'(xMDN_No)
CustomKeywords.'com.smartfren.billing.utility.runRecurring'(xMDN_No, xServiceCode_No,xRecurringDate,RecurringType)
CustomKeywords.'com.smartfren.billing.APIUtility.getSubInfoDetail'(xMDN_No)
CustomKeywords.'com.smartfren.billing.utility.recuringCharge'('6288295291140','202012',2)
