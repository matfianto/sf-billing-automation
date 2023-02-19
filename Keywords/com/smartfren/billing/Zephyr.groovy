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
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import groovy.json.JsonSlurper
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper


import com.kms.katalon.core.webui.exception.WebElementNotFoundException


class Zephyr {
	@Keyword
	def runTestCase (){
		TestData data = findTestData("Data Files/TestCaseInput/TestCaseExcel")
		if(data.getValue("Test_Step", 1)=="Jira_Project_Properties") {
			GlobalVariable.JiraUser.put('username',data.getValue("param1", 1))
			GlobalVariable.JiraUser.put('password',data.getValue("param2", 1))
			GlobalVariable.JiraProjectProperties.put('projectID',data.getValue("param3", 1))
			GlobalVariable.JiraProjectProperties.put('storyKey',data.getValue("param4", 1))
			GlobalVariable.JiraProjectProperties.put('cycleName',data.getValue("param5", 1))
			GlobalVariable.JiraProjectProperties.put('folderName',data.getValue("param6", 1))
			KeywordUtil.logInfo("Username : "+GlobalVariable.JiraUser.get('username'))
			KeywordUtil.logInfo("Password : "+'*'*GlobalVariable.JiraUser.get('password').length())
			KeywordUtil.logInfo("projectID : "+GlobalVariable.JiraProjectProperties.get('projectID'))
			KeywordUtil.logInfo("storyKey : "+GlobalVariable.JiraProjectProperties.get('storyKey'))
			KeywordUtil.logInfo("cycleName : "+GlobalVariable.JiraProjectProperties.get('cycleName'))
			KeywordUtil.logInfo("folderName : "+GlobalVariable.JiraProjectProperties.get('folderName'))
		}
		KeywordUtil.logInfo("Create or Delete existing folder in Zephyr Execution Cycle")
		createCycleFolder()
		KeywordUtil.logInfo("****************************************************************************************************************************************************************")
		for (def index : (1..data.getRowNumbers())) {
			if (data.getValue("Test_Step", index)=="Start_Event"){
				jiraStatusUpdate(data.getValue("Testcase", index),data.getValue("Description", index),data.getValue("Priority", index),data.getValue("Labels", index),data.getValue("Step Action", index), data.getValue("param1", index), data.getValue("Expected Result", index))
				KeywordUtil.logInfo("****************************************************************************************************************************************************************")
			}
		}
	}

	@Keyword
	def basicAuthentication() {
		String plainCredentials = GlobalVariable.JiraUser.get('username') + ":" + GlobalVariable.JiraUser.get('password');
		println GlobalVariable.JiraUser.get('username') + ":" + GlobalVariable.JiraUser.get('password')
		String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
		// Create authorization header
		GlobalVariable.ZephyrToken = "Basic " + base64Credentials;
		println GlobalVariable.ZephyrToken
	}
	@Keyword
	def createCycleFolder() {
		basicAuthentication()
		String cycle_id = null
		String version_id = null
		//def jsonResp = checkProjectJira()
		//String project_id = jsonResp.id
		String project_id = GlobalVariable.JiraProjectProperties.get('projectID')
		//KeywordUtil.logInfo("Getting Cycle ID for "+GlobalVariable.JiraProjectProperties.get('cycleName'))
		println "Getting Cycle ID for "+GlobalVariable.JiraProjectProperties.get('cycleName')
		def jsonResp = checkCycleByProjectID(project_id).get("-1").get(0)
		GlobalVariable.JiraProjectProperties.put('projectKey',jsonResp[jsonResp.keySet()[0]].projectKey)
		int i=0
		boolean newCycle = false
		while(i<=jsonResp.size()) {
			try {
				if(jsonResp[jsonResp.keySet()[i]].name == GlobalVariable.JiraProjectProperties.get('cycleName')) {
					cycle_id = jsonResp.keySet()[i]
					version_id = jsonResp[jsonResp.keySet()[i]].versionId
					//GlobalVariable.JiraProjectProperties.put('projectKey',jsonResp[jsonResp.keySet()[i]].projectKey)
					break
				}
			}catch(Exception e){
				KeywordUtil.logInfo("Cycle with name '"+GlobalVariable.JiraProjectProperties.get('cycleName')+"' is not available in projectKey "+GlobalVariable.JiraProjectProperties.get('projectKey'))
				cycle_id = createNewCycle(GlobalVariable.JiraProjectProperties.get('cycleName'), "-1", project_id)
				version_id = "-1"
				newCycle = true
				break
			}
			i++
		}
		//KeywordUtil.logInfo("Getting Cycle ID for "+GlobalVariable.JiraProjectProperties.get('cycleName')+" success")
		//KeywordUtil.logInfo("cycle id : " +cycle_id)
		//KeywordUtil.logInfo("version id : " +version_id)
		//KeywordUtil.logInfo("Checking existing folder '"+GlobalVariable.JiraProjectProperties.get('folderName')+"' in cycleName "+GlobalVariable.JiraProjectProperties.get('cycleName'))
		println "Getting Cycle ID for "+GlobalVariable.JiraProjectProperties.get('cycleName')+" success"
		println "cycle id : " +cycle_id
		println "version id : " +version_id
		String folder_id = null
		if(newCycle) {
			KeywordUtil.logInfo("No Need to Check existing folder in cycleName "+GlobalVariable.JiraProjectProperties.get('cycleName'))
		}else {
			println "Checking existing folder '"+GlobalVariable.JiraProjectProperties.get('folderName')+"' in cycleName "+GlobalVariable.JiraProjectProperties.get('cycleName')
			jsonResp = checkFolderCycle(project_id, cycle_id, version_id)
			i=0
			while(i<=jsonResp.size()) {
				try {
					if(jsonResp[i].folderName == GlobalVariable.JiraProjectProperties.get('folderName')) {
						folder_id = jsonResp[i].folderId
						//KeywordUtil.logInfo("Folder '"+GlobalVariable.JiraProjectProperties.get('folderName')+"' is found in cycleName "+GlobalVariable.JiraProjectProperties.get('cycleName'))
						//KeywordUtil.logInfo("folder id : " +folder_id)
						println "Folder '"+GlobalVariable.JiraProjectProperties.get('folderName')+"' is found in cycleName "+GlobalVariable.JiraProjectProperties.get('cycleName')
						println "folder id : " +folder_id
						break
					}
				}catch(Exception e){
					KeywordUtil.logInfo("Folder '"+GlobalVariable.JiraProjectProperties.get('folderName')+"' is not found in cycleName "+GlobalVariable.JiraProjectProperties.get('cycleName'))
					break
				}
				i++
			}
		}

		if(folder_id == null) {
			//create Folder
			jsonResp = createFolderCycle(project_id, cycle_id, version_id)
			folder_id = jsonResp.id
			//KeywordUtil.logInfo("folder id : " +folder_id)
			println "folder id : " +folder_id
		}else {
			//delete folder
			deleteFolderCycle(project_id, cycle_id, version_id, folder_id)
			//create folder
			jsonResp = createFolderCycle(project_id, cycle_id, version_id)
			folder_id = jsonResp.id
			//KeywordUtil.logInfo("folder id : " +folder_id)
			println "folder id : " +folder_id
		}
		GlobalVariable.ZephyrAutomationProperties.replace('project_id',project_id)
		GlobalVariable.ZephyrAutomationProperties.replace('cycle_id',cycle_id)
		GlobalVariable.ZephyrAutomationProperties.replace('version_id',version_id)
		GlobalVariable.ZephyrAutomationProperties.replace('folder_id',folder_id)
	}
	@Keyword
	def jiraStatusUpdate(String summaryTest, String summaryDesc, String priority, String label, String step, String data, String result) {
		String storyKey=GlobalVariable.JiraProjectProperties.get('storyKey')
		def jsonResp = checkIssueJira(storyKey)
		String issue_id = null
		String issue_key = null
		String step_id = null
		String execution_id = null
		String result_id = null
		String project_id = GlobalVariable.ZephyrAutomationProperties.get('project_id')
		try {
			//KeywordUtil.logInfo("Checking Test Issue summary '"+summaryTest+"' in storyKey "+storyKey+" relation")
			println "Checking Test Issue summary '"+summaryTest+"' in storyKey "+storyKey+" relation"
			int i=0
			while(i<=jsonResp.fields.issuelinks.size()) {
				if(jsonResp.fields.issuelinks[i].outwardIssue.fields.summary == summaryTest) {
					issue_id = jsonResp.fields.issuelinks[i].outwardIssue.id
					issue_key = jsonResp.fields.issuelinks[i].outwardIssue.key
					break
				}
				i++
			}
			//KeywordUtil.logInfo("Test issue summary '"+summaryTest+"' already related with storyKey " +storyKey)
			//KeywordUtil.logInfo("issue id : " +issue_id)
			//KeywordUtil.logInfo("issue key : " +issue_key)
			println "Test issue summary '"+summaryTest+"' already related with storyKey " +storyKey
			println "issue id : " +issue_id
			println "issue key : " +issue_key
			jsonResp = checkTestStep(issue_id, issue_key)
			step_id = jsonResp.stepBeanCollection[0].id
			//KeywordUtil.logInfo("step id : " +step_id)
			//KeywordUtil.logInfo("step name : " +jsonResp.stepBeanCollection[0].step)
			println "step id : " +step_id
			println "step name : " +jsonResp.stepBeanCollection[0].step
		}catch(NullPointerException e) {
			//KeywordUtil.logInfo("Test issue "+summaryTest+" not found in storyKey " +storyKey)
			println "Test issue "+summaryTest+" not found in storyKey " +storyKey
			jsonResp = createNewIssueJira(summaryTest, summaryDesc.replace("\n","\\\\n").replace('"','\\\\"'), priority, label)
			issue_id = jsonResp.id
			issue_key = jsonResp.key
			//KeywordUtil.logInfo("issue id : " +issue_id)
			//KeywordUtil.logInfo("issue key : " +issue_key)
			println "issue id : " +issue_id
			println "issue key : " +issue_key
			createIssueLink(issue_key, storyKey)
			jsonResp = createNewTestStep(issue_id, issue_key, step.replace("\n","\\\\n").replace('"','\\\\"'), data.replace("\n","\\\\n").replace('"','\\\\"'), result.replace("\n","\\\\n").replace('"','\\\\"'))
			step_id = jsonResp.id
			//KeywordUtil.logInfo("step id : " +step_id)
			//KeywordUtil.logInfo("step name : " +step)
			println "step id : " +step_id
			println "step name : " +step
		}
		addTestToCycleFolder(issue_key, GlobalVariable.ZephyrAutomationProperties.get('cycle_id'), project_id, GlobalVariable.ZephyrAutomationProperties.get('version_id'), GlobalVariable.ZephyrAutomationProperties.get('folder_id'))
		jsonResp = checkTestExecution(issue_id, issue_key)
		try {
			int i=0
			//KeywordUtil.logInfo("Checking Test issue "+issue_key+" execution ID on cycleName "+GlobalVariable.JiraProjectProperties.get('cycleName')+" folderName "+GlobalVariable.JiraProjectProperties.get('folderName'))
			println "Checking Test issue "+issue_key+" execution ID on cycleName "+GlobalVariable.JiraProjectProperties.get('cycleName')+" folderName "+GlobalVariable.JiraProjectProperties.get('folderName')
			while(i<=jsonResp.executions.size()) {
				if(jsonResp.executions[i].execution.testCycle == GlobalVariable.JiraProjectProperties.get('cycleName') && jsonResp.executions[i].execution.folderName == GlobalVariable.JiraProjectProperties.get('folderName')) {
					execution_id=jsonResp.executions[i].execution.id
					//KeywordUtil.logInfo("Test issue "+issue_key+" execution ID found")
					//KeywordUtil.logInfo("Execution ID : "+execution_id)
					println "Test issue "+issue_key+" execution ID found"
					println "Execution ID : "+execution_id
					break
				}
				i++
			}
		}catch(NullPointerException e) {
			KeywordUtil.markFailedAndStop("Test issue "+issue_key+" execution ID  not found, Please check again")
		}
		jsonResp = updateStepResult(step_id, issue_id, issue_key, execution_id, '1')
		result_id = jsonResp.id
		//KeywordUtil.logInfo("result id : " +result_id)
		println "result id : " +result_id
		updateTestResult(issue_key, execution_id, '1')
		KeywordUtil.logInfo("Test Issue "+issue_key+" "+summaryTest+" created automatically in Jira and link into cycleName:"+GlobalVariable.JiraProjectProperties.get('cycleName')+" cycleFolder:"+GlobalVariable.JiraProjectProperties.get('folderName'))
	}

	@Keyword
	def checkProjectJira() {
		RequestObject objectRepo = findTestObject('Object Repository/Zephyr_API/getProjectDetail',[('projectKey') : GlobalVariable.JiraProjectProperties.get('projectKey')])
		ResponseObject objectResp = WS.sendRequest(objectRepo, FailureHandling.OPTIONAL)
		if (objectResp.getStatusCode()==200 || objectResp.getStatusCode()==201) {
			JsonSlurper jsonSlurper = new JsonSlurper()
			Map jsonResp = jsonSlurper.parseText(objectResp.getResponseText())
			return jsonResp
		}else {
			KeywordUtil.logInfo("HEADER\n"+objectResp.getHeaderFields()+"\n\nBODY\n"+objectResp.getResponseBodyContent())
			KeywordUtil.markFailedAndStop("checkProjectJira failed with status code="+objectResp.getStatusCode())
			return null
		}
	}
	@Keyword
	def checkFolderCycle(String projectID, String cycle_id, String version_id) {
		RequestObject objectRepo = findTestObject('Object Repository/Zephyr_API/getFolderByCycleID',[('projectID') : projectID, ('cycle_id') : cycle_id,('versionID') : version_id])
		ResponseObject objectResp = WS.sendRequest(objectRepo, FailureHandling.OPTIONAL)
		if (objectResp.getStatusCode()==200 || objectResp.getStatusCode()==201) {
			JsonSlurper jsonSlurper = new JsonSlurper()
			println objectResp.getResponseText()
			def jsonResp = jsonSlurper.parseText(objectResp.getResponseText())
			println jsonResp
			return jsonResp
		}else {
			KeywordUtil.logInfo("HEADER\n"+objectResp.getHeaderFields()+"\n\nBODY\n"+objectResp.getResponseBodyContent())
			KeywordUtil.markFailedAndStop("checkFolderCycle failed with status code="+objectResp.getStatusCode())
			return null
		}
	}
	@Keyword
	def createFolderCycle(String projectID, String cycle_id, String version_id) {
		//KeywordUtil.logInfo("Creating folder name '"+GlobalVariable.JiraProjectProperties.get('folderName')+"' in cycle name "+GlobalVariable.JiraProjectProperties.get('cycleName'))
		println "Creating folder name '"+GlobalVariable.JiraProjectProperties.get('folderName')+"' in cycle name "+GlobalVariable.JiraProjectProperties.get('cycleName')
		RequestObject objectRepo = findTestObject('Object Repository/Zephyr_API/createFolderCycle',[('projectID') : projectID, ('cycle_id') : cycle_id,('versionID') : version_id, ('folderName') : GlobalVariable.JiraProjectProperties.get('folderName')])
		ResponseObject objectResp = WS.sendRequest(objectRepo, FailureHandling.OPTIONAL)
		if (objectResp.getStatusCode()==200 || objectResp.getStatusCode()==201) {
			//KeywordUtil.logInfo("Create folder name '"+GlobalVariable.JiraProjectProperties.get('folderName')+"' success")
			println "Create folder name '"+GlobalVariable.JiraProjectProperties.get('folderName')+"' success"
			JsonSlurper jsonSlurper = new JsonSlurper()
			Map jsonResp = jsonSlurper.parseText(objectResp.getResponseText())
			return jsonResp
		}else {
			KeywordUtil.logInfo("HEADER\n"+objectResp.getHeaderFields()+"\n\nBODY\n"+objectResp.getResponseBodyContent())
			KeywordUtil.markFailedAndStop("createFolderCycle failed with status code="+objectResp.getStatusCode())
			return null
		}
	}
	@Keyword
	def deleteFolderCycle(String projectID, String cycle_id, String version_id, String folderID) {
		//KeywordUtil.logInfo("Deleting folder name '"+GlobalVariable.JiraProjectProperties.get('folderName')+"' in cycle name "+GlobalVariable.JiraProjectProperties.get('cycleName'))
		println "Deleting folder name '"+GlobalVariable.JiraProjectProperties.get('folderName')+"' in cycle name "+GlobalVariable.JiraProjectProperties.get('cycleName')
		RequestObject objectRepo = findTestObject('Object Repository/Zephyr_API/deleteFolderCycle',[('projectID') : projectID, ('cycle_id') : cycle_id,('versionID') : version_id, ('folderID') : folderID])
		ResponseObject objectResp = WS.sendRequest(objectRepo, FailureHandling.OPTIONAL)
		if (objectResp.getStatusCode()==200 || objectResp.getStatusCode()==201) {
			//KeywordUtil.logInfo("Delete folder name '"+GlobalVariable.JiraProjectProperties.get('folderName')+"' success")
			println "Delete folder name '"+GlobalVariable.JiraProjectProperties.get('folderName')+"' success"
		}else {
			KeywordUtil.logInfo("HEADER\n"+objectResp.getHeaderFields()+"\n\nBODY\n"+objectResp.getResponseBodyContent())
			KeywordUtil.markFailedAndStop("deleteFolderCycle failed with status code="+objectResp.getStatusCode())
		}
	}
	@Keyword
	def checkIssueJira(String storyKey) {
		RequestObject objectRepo = findTestObject('Object Repository/Zephyr_API/getIssueDetail',[('issueKey') : storyKey])
		ResponseObject objectResp = WS.sendRequest(objectRepo, FailureHandling.OPTIONAL)
		if (objectResp.getStatusCode()==200 || objectResp.getStatusCode()==201) {
			JsonSlurper jsonSlurper = new JsonSlurper()
			Map jsonResp = jsonSlurper.parseText(objectResp.getResponseText())
			return jsonResp
		}else {
			KeywordUtil.logInfo("HEADER\n"+objectResp.getHeaderFields()+"\n\nBODY\n"+objectResp.getResponseBodyContent())
			KeywordUtil.markFailedAndStop("checkIssueJira failed with status code="+objectResp.getStatusCode())
			return null
		}
	}
	@Keyword
	def createNewIssueJira(String summaryTest, String summaryDesc, String priority, String label) {
		//KeywordUtil.logInfo('Create new Test Issue in Jira with summary : '+summaryTest)
		String priority_format = priority.substring(0, 1).toUpperCase() + priority.substring(1).toLowerCase()
		println 'Create new Test Issue in Jira with summary : '+summaryTest
		RequestObject objectRepo = findTestObject('Object Repository/Zephyr_API/createIssueJira',[('project') : GlobalVariable.JiraProjectProperties.get('projectKey'), ('summary') : summaryTest, ('description') : summaryDesc, ('assignee') : GlobalVariable.JiraUser.get('username'), ('priority') : priority_format, ('label') : label ])
		ResponseObject objectResp = WS.sendRequest(objectRepo, FailureHandling.OPTIONAL)
		if (objectResp.getStatusCode()==200 || objectResp.getStatusCode()==201) {
			//KeywordUtil.logInfo('Create new Test Issue in Jira success')
			println 'Create new Test Issue in Jira success'
			JsonSlurper jsonSlurper = new JsonSlurper()
			Map jsonResp = jsonSlurper.parseText(objectResp.getResponseText())
			return jsonResp
		}else {
			KeywordUtil.logInfo("HEADER\n"+objectResp.getHeaderFields()+"\n\nBODY\n"+objectResp.getResponseBodyContent())
			KeywordUtil.markFailedAndStop("createNewIssueJira failed with status code="+objectResp.getStatusCode())
			return null
		}
	}
	@Keyword
	def createIssueLink(String issue_key, String storyKey) {
		//KeywordUtil.logInfo('Linking Test issue '+issue_key+' with storyKey '+storyKey)
		println 'Linking Test issue '+issue_key+' with storyKey '+storyKey
		RequestObject objectRepo = findTestObject('Object Repository/Zephyr_API/issueLink',[('testIssue') : issue_key ,('storyIssue') : storyKey ])
		ResponseObject objectResp = WS.sendRequest(objectRepo, FailureHandling.OPTIONAL)
		if (objectResp.getStatusCode()==200 || objectResp.getStatusCode()==201) {
			//KeywordUtil.logInfo('Test Issue '+issue_key+' and storyKey '+storyKey+' linked successfully')
			println 'Test Issue '+issue_key+' and storyKey '+storyKey+' linked successfully'
		}else {
			KeywordUtil.logInfo("HEADER\n"+objectResp.getHeaderFields()+"\n\nBODY\n"+objectResp.getResponseBodyContent())
			KeywordUtil.markFailedAndStop("createIssueLink failed with status code="+objectResp.getStatusCode())
		}
	}
	@Keyword
	def createNewTestStep(String issue_id, String issue_key, String step, String data, String result) {
		//KeywordUtil.logInfo('Create new Test Step in Jira issueKey '+issue_key+' with detail : '+
		//	'\nStep : '+step+'\nData : '+data+'\nResult : '+result)
		println 'Create new Test Step in Jira issueKey '+issue_key+' with detail : '+
				'\nStep : '+step+'\nData : '+data+'\nResult : '+result
		RequestObject objectRepo = findTestObject('Object Repository/Zephyr_API/createTestStep',[('issueID') : issue_id, ('step') : step, ('data') : data, ('result') : result])
		ResponseObject objectResp = WS.sendRequest(objectRepo, FailureHandling.OPTIONAL)
		if (objectResp.getStatusCode()==200 || objectResp.getStatusCode()==201) {
			//KeywordUtil.logInfo('Create new Test Step in Jira success')
			println 'Create new Test Step in Jira success'
			JsonSlurper jsonSlurper = new JsonSlurper()
			Map jsonResp = jsonSlurper.parseText(objectResp.getResponseText())
			return jsonResp
		}else {
			KeywordUtil.logInfo("HEADER\n"+objectResp.getHeaderFields()+"\n\nBODY\n"+objectResp.getResponseBodyContent())
			KeywordUtil.markFailedAndStop("createNewTestStep failed with status code="+objectResp.getStatusCode())
			return null
		}
	}
	@Keyword
	def checkTestStep(String issue_id, String issue_key) {
		//KeywordUtil.logInfo('Check Test Step in Jira issueKey '+issue_key)
		println 'Check Test Step in Jira issueKey '+issue_key
		RequestObject objectRepo = findTestObject('Object Repository/Zephyr_API/getTestStep',[('issueID') : issue_id])
		ResponseObject objectResp = WS.sendRequest(objectRepo, FailureHandling.OPTIONAL)
		if (objectResp.getStatusCode()==200 || objectResp.getStatusCode()==201) {
			//KeywordUtil.logInfo('Check Test Step in Jira success')
			println 'Check Test Step in Jira success'
			JsonSlurper jsonSlurper = new JsonSlurper()
			Map jsonResp = jsonSlurper.parseText(objectResp.getResponseText())
			return jsonResp
		}else {
			KeywordUtil.logInfo("HEADER\n"+objectResp.getHeaderFields()+"\n\nBODY\n"+objectResp.getResponseBodyContent())
			KeywordUtil.markFailedAndStop("checkTestStep failed with status code="+objectResp.getStatusCode())
			return null
		}
	}
	@Keyword
	def checkCycleByProjectID(String project_id) {
		//KeywordUtil.logInfo('Get All Cycle Properties By Project ID '+project_id)
		println 'Get All Cycle Properties By Project ID '+project_id
		RequestObject objectRepo = findTestObject('Object Repository/Zephyr_API/getCycleByProjectID',[('projectID') : project_id])
		ResponseObject objectResp = WS.sendRequest(objectRepo, FailureHandling.OPTIONAL)
		if (objectResp.getStatusCode()==200 || objectResp.getStatusCode()==201) {
			//KeywordUtil.logInfo('Get All Cycle Properties By Project ID '+project_id+' success')
			println 'Get All Cycle Properties By Project ID '+project_id+' success'
			JsonSlurper jsonSlurper = new JsonSlurper()
			Map jsonResp = jsonSlurper.parseText(objectResp.getResponseText())
			return jsonResp
		}else {
			KeywordUtil.logInfo("HEADER\n"+objectResp.getHeaderFields()+"\n\nBODY\n"+objectResp.getResponseBodyContent())
			KeywordUtil.markFailedAndStop("checkCycleByProjectID failed with status code="+objectResp.getStatusCode())
			return null
		}
	}
	@Keyword
	def addTestToCycleFolder(String issue_key, String cycle_id, String project_id, String version_id, String folder_id) {
		//KeywordUtil.logInfo('Adding Test issue '+issue_key+' into cycleName '+GlobalVariable.JiraProjectProperties.get('cycleName')+' and folderName '+GlobalVariable.JiraProjectProperties.get('folderName'))
		println 'Adding Test issue '+issue_key+' into cycleName '+GlobalVariable.JiraProjectProperties.get('cycleName')+' and folderName '+GlobalVariable.JiraProjectProperties.get('folderName')
		RequestObject objectRepo = findTestObject('Object Repository/Zephyr_API/addTestsToCycle',[('issue_key') : issue_key ,('cycle_id') : cycle_id ,('project_id') : project_id ,('version_id') : version_id ,('folder_id') : folder_id ])
		ResponseObject objectResp = WS.sendRequest(objectRepo, FailureHandling.OPTIONAL)
		if (objectResp.getStatusCode()==200 || objectResp.getStatusCode()==201) {
			//KeywordUtil.logInfo('Test Issue '+issue_key+' added successfully into cycleName '+GlobalVariable.JiraProjectProperties.get('cycleName')+' and folderName '+GlobalVariable.JiraProjectProperties.get('folderName'))
			println 'Test Issue '+issue_key+' added successfully into cycleName '+GlobalVariable.JiraProjectProperties.get('cycleName')+' and folderName '+GlobalVariable.JiraProjectProperties.get('folderName')
		}else {
			KeywordUtil.logInfo("HEADER\n"+objectResp.getHeaderFields()+"\n\nBODY\n"+objectResp.getResponseBodyContent())
			KeywordUtil.markFailedAndStop("addTestToCycleFolder failed with status code="+objectResp.getStatusCode())
		}
	}
	@Keyword
	def checkTestExecution(String issue_id, String issue_key) {
		//KeywordUtil.logInfo('Get All ExecutionID for Test Issue '+issue_key)
		println 'Get All ExecutionID for Test Issue '+issue_key
		RequestObject objectRepo = findTestObject('Object Repository/Zephyr_API/getExecutionID',[('issue_id') : issue_id])
		ResponseObject objectResp = WS.sendRequest(objectRepo, FailureHandling.OPTIONAL)
		if (objectResp.getStatusCode()==200 || objectResp.getStatusCode()==201) {
			//KeywordUtil.logInfo('Get All ExecutionID for Test Issue '+issue_key+' success')
			println 'Get All ExecutionID for Test Issue '+issue_key+' success'
			JsonSlurper jsonSlurper = new JsonSlurper()
			Map jsonResp = jsonSlurper.parseText(objectResp.getResponseText())
			return jsonResp
		}else {
			KeywordUtil.logInfo("HEADER\n"+objectResp.getHeaderFields()+"\n\nBODY\n"+objectResp.getResponseBodyContent())
			KeywordUtil.markFailedAndStop("checkTestExecution failed with status code="+objectResp.getStatusCode())
			return null
		}
	}
	@Keyword
	def updateStepResult(String step_id, String issue_id, String issue_key, String execution_id, String status) {
		//KeywordUtil.logInfo('Updating Test Step for Test Issue '+issue_key+' in cycleName '+GlobalVariable.JiraProjectProperties.get('cycleName')+' and cycleFolder '+GlobalVariable.JiraProjectProperties.get('folderName'))
		println 'Updating Test Step for Test Issue '+issue_key+' in cycleName '+GlobalVariable.JiraProjectProperties.get('cycleName')+' and cycleFolder '+GlobalVariable.JiraProjectProperties.get('folderName')
		RequestObject objectRepo = findTestObject('Object Repository/Zephyr_API/createStepResult',[('step_id') : step_id, ('issue_id') : issue_id, ('execution_id') : execution_id, ('status') : status])
		ResponseObject objectResp = WS.sendRequest(objectRepo, FailureHandling.OPTIONAL)
		if (objectResp.getStatusCode()==200 || objectResp.getStatusCode()==201) {
			//KeywordUtil.logInfo('Updating Test Step for Test Issue '+issue_key+' success')
			println 'Updating Test Step for Test Issue '+issue_key+' success'
			JsonSlurper jsonSlurper = new JsonSlurper()
			Map jsonResp = jsonSlurper.parseText(objectResp.getResponseText())
			return jsonResp
		}else {
			KeywordUtil.logInfo("HEADER\n"+objectResp.getHeaderFields()+"\n\nBODY\n"+objectResp.getResponseBodyContent())
			KeywordUtil.markFailedAndStop("updateStepResult failed with status code="+objectResp.getStatusCode())
			return null
		}
	}
	@Keyword
	def updateTestResult(String issue_key, String execution_id, String status) {
		//KeywordUtil.logInfo('Updating status Test Issue '+issue_key+' in cycleName '+GlobalVariable.JiraProjectProperties.get('cycleName')+' and cycleFolder '+GlobalVariable.JiraProjectProperties.get('folderName'))
		println 'Updating status Test Issue '+issue_key+' in cycleName '+GlobalVariable.JiraProjectProperties.get('cycleName')+' and cycleFolder '+GlobalVariable.JiraProjectProperties.get('folderName')
		RequestObject objectRepo = findTestObject('Object Repository/Zephyr_API/updateStatusTest',[('execution_id') : execution_id, ('status') : status])
		ResponseObject objectResp = WS.sendRequest(objectRepo, FailureHandling.OPTIONAL)
		if (objectResp.getStatusCode()==200 || objectResp.getStatusCode()==201) {
			//KeywordUtil.logInfo('Update status Test Issue '+issue_key+' success')
			println 'Update status Test Issue '+issue_key+' success'
		}else {
			KeywordUtil.logInfo("HEADER\n"+objectResp.getHeaderFields()+"\n\nBODY\n"+objectResp.getResponseBodyContent())
			KeywordUtil.markFailedAndStop("updateTestResult failed with status code="+objectResp.getStatusCode())
		}
	}
	@Keyword
	def createNewCycle(String cycle_name, String version_id, String project_id) {
		KeywordUtil.logInfo('Create new Cycle in Jira with name : '+cycle_name)
		RequestObject objectRepo = findTestObject('Object Repository/Zephyr_API/createCycle',[('projectID') : project_id, ('cycle_name') : cycle_name, ('versionID') : version_id ])
		ResponseObject objectResp = WS.sendRequest(objectRepo, FailureHandling.OPTIONAL)
		if (objectResp.getStatusCode()==200 || objectResp.getStatusCode()==201) {
			KeywordUtil.logInfo('Create new Cycle in Jira with name : '+cycle_name+' success')
			JsonSlurper jsonSlurper = new JsonSlurper()
			Map jsonResp = jsonSlurper.parseText(objectResp.getResponseText())
			return jsonResp.id
		}else {
			KeywordUtil.logInfo("HEADER\n"+objectResp.getHeaderFields()+"\n\nBODY\n"+objectResp.getResponseBodyContent())
			KeywordUtil.markFailedAndStop("Create new Cycle in Jira failed with status code="+objectResp.getStatusCode())
			return null
		}
	}
}