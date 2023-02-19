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
import com.kms.katalon.core.util.KeywordUtil
import groovy.sql.Sql
import java.sql.*


//CustomKeywords.'com.smartfren.API.APIDBUtility.displayQueryResult'('AutomationDB','SELECT id,api_name FROM table_automation_api')
//def queryResult = CustomKeywords.'com.smartfren.API.APIDBUtility.queryDB'('AutomationDB','SELECT id,api_name FROM table_automation_api')
//CustomKeywords.'com.smartfren.API.APISPUtility.automationValidationTable'(queryResult)

CustomKeywords.'com.smartfren.billing.DBUtility.displayQueryResult'('AutomationDB','select * from table_automation_api')
//CustomKeywords.'com.smartfren.billing.DBUtility.displayQueryResult2'('AutomationDB','select * from table_automation_api')
def queryResult = CustomKeywords.'com.smartfren.billing.DBUtility.queryDB'('OnboardingDB','select * from fg_codes')
//CustomKeywords.'com.smartfren.API.APISPUtility.automationValidationTable'(queryResult)




