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
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import groovy.json.JsonSlurper as JsonSlurper
import com.smartfren.mfino.MFino

/*def testString = "SMS Event failed, Please check from by manual run### Usage_SMS;SMS;1608613198 ### Result Code 4012"
def testString2 = "SMS Event failed, Please check from by manual run### Usage_SMS;SMS;1608613198 ### Result Code 4012"
if (testString.contains("Result Code")){
	KeywordUtil.logInfo('Code: '+ testString.substring(testString.lastIndexOf(" ")+1))
}

if (testString2.contains("Result Code 2001")){
	KeywordUtil.logInfo('Code2: '+ testString2.substring(testString2.lastIndexOf(" ")+1))
}*/
//CustomKeywords.'com.smartfren.billing.billingTestCase.runTestCase'()
//def MDN_No = '6288272112581'
//CustomKeywords.'com.smartfren.billing.DBUtility.executeUpdateQuery'('BillingDB',"update om_order set state='E' where order_id IN (select order_id from subs_order where acc_nbr='"+MDN_No.subSequence(2, MDN_No.length())+"' and order_state='P')")
//int start = System.currentTimeMillis();
//WS.delay(100)
//int end = System.currentTimeMillis();
//int loadintime = end - start;
//KeywordUtil.logInfo('loadintime: '+ loadintime.toString())
//CustomKeywords.'com.smartfren.billing.DBUtility.executeUpdateQuery'('BillingDB',"S")
//CustomKeywords.'com.smartfren.billing.utility.createBillPostpaid'('6288295292286', '53467')
//CustomKeywords.'com.smartfren.billing.utility.deleteUsageCL'('6288272113235')

//CustomKeywords.'com.smartfren.billing.APIUtility.packagePurchaseMFino' ('6288234902574', '5011', '15388', '150000', '5100','329074','')

//CustomKeywords.'com.smartfren.billing.Zephyr.jiraStatusUpdate' ('QKB-26', 'defg','add IPP Unlimited','08524123131',result)

//CustomKeywords.'com.smartfren.billing.Zephyr.createCycleFolder'()
//
//CustomKeywords.'com.smartfren.billing.Zephyr.jiraStatusUpdate'('[BO - 1234] Paket unlimited', 'Add IPP Unlimited', '0888131341', result)


//println 'billing cycle id : '+CustomKeywords.'com.smartfren.billing.billingTestCase.getBillingCycleTypeID'('628886003202')

//CustomKeywords.'com.smartfren.billing.usageUtilityVM.runSMSMT'('abc','8975', '628886003202', '11628881803809', 'default',CustomKeywords.'com.smartfren.billing.billingTestCase.getBillingCycleTypeID'('628886003202'))

//CustomKeywords.'com.smartfren.billing.APIUtility.rechargeMFino'('628881850632', '5011', '881', '100000', '5100')

/*def mfino = new MFino()
String Msg = '0200F23E400189E080080000000040000000165484151234565216561000000000030000090717393226328517393209072209090760100366603153360095415808001POS51500123456789011111BANK TEST  -  DIVISI MULTIMEDIA        A360016628810908116    019003      '
String response = mfino.isoReq('10.17.85.25', '8583', Msg);
println response
*/

CustomKeywords.'com.smartfren.billing.utility.updateMDNstatusActive'('6288214000155')

//CustomKeywords.'com.smartfren.billing.APIUtility.activationMDN'('6288214000155')