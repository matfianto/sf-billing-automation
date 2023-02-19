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
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.smartfren.mfino.MFino
import groovy.json.JsonSlurper
import com.kms.katalon.core.util.KeywordUtil


String plainCredentials = GlobalVariable.JiraUser.get('username') + ":" + GlobalVariable.JiraUser.get('password');
println GlobalVariable.JiraUser.get('username') + ":" + GlobalVariable.JiraUser.get('password')
String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
// Create authorization header
GlobalVariable.ZephyrToken = "Basic " + base64Credentials;
println GlobalVariable.ZephyrToken

//RequestObject objectRepo = findTestObject('Object Repository/Zephyr_API/createIssueJira',[('summary') : '[TCS] - [BO-16000] - Topup with denom Rp. 30.000' ,('assignee') : GlobalVariable.JiraUser.get('username') ])
//ResponseObject objectResp = WS.sendRequestAndVerify(objectRepo, FailureHandling.OPTIONAL)

RequestObject objectRepo = findTestObject('Object Repository/Zephyr_API/getIssueDetail',[('issueKey') : 'QKB-26'])
ResponseObject objectResp = WS.sendRequestAndVerify(objectRepo, FailureHandling.OPTIONAL)

KeywordUtil.logInfo("HEADER\n"+objectResp.getHeaderFields()+"\n\nBODY\n"+objectResp.getResponseBodyContent())
JsonSlurper jsonSlurper = new JsonSlurper()
def jsonResp = jsonSlurper.parseText(objectResp.getResponseText())
String issue_id = null
String issue_key = null
String summary = '[TCS] - [BO-16001] - Topup with denom Rp. 40.000'
try {
	int i=0
	while(i<100) {
		if(jsonResp.fields.issuelinks[i].outwardIssue.fields.summary == summary) {
			issue_id = jsonResp.fields.issuelinks[i].outwardIssue.id
			issue_key = jsonResp.fields.issuelinks[i].outwardIssue.key
			break
		}
		i++
	}
	KeywordUtil.logInfo("issue id : " +issue_id)
	KeywordUtil.logInfo("issue key : " +issue_key)
}catch(NullPointerException e) {
	objectRepo = findTestObject('Object Repository/Zephyr_API/createIssueJira',[('summary') : '[TCS] - [BO-16001] - Topup with denom Rp. 40.000' ,('assignee') : GlobalVariable.JiraUser.get('username') ])
	objectResp = WS.sendRequestAndVerify(objectRepo, FailureHandling.OPTIONAL)
	jsonResp = jsonSlurper.parseText(objectResp.getResponseText())
	issue_id = jsonResp.id
	issue_key = jsonResp.key
	KeywordUtil.logInfo("issue id : " +jsonResp.id)
	KeywordUtil.logInfo("issue key : " +jsonResp.key)
	objectRepo = findTestObject('Object Repository/Zephyr_API/issueLink',[('testIssue') : issue_key ,('storyIssue') : 'QKB-26' ])
	objectResp = WS.sendRequestAndVerify(objectRepo, FailureHandling.OPTIONAL)
	KeywordUtil.logInfo("HEADER\n"+objectResp.getHeaderFields()+"\n\nBODY\n"+objectResp.getResponseBodyContent())
}
