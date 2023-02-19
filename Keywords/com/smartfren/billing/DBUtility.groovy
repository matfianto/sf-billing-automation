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
import java.sql.*;
import groovy.sql.Sql as Sql
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import internal.GlobalVariable

public class DBUtility {

	private DBDetails
	private queryResult
	private sql
	/**
	 * Open and return a connection to database
	 * @param dataFile absolute file path
	 * @return an instance of java.sql.Connection
	 */
	@Keyword
	def connectDB(){
		switch (DBDetails.driver){
			case "org.mariadb.jdbc.Driver":
				sql = Sql.newInstance('jdbc:mysql://'+DBDetails.get('host')+':'+DBDetails.get('port')+'/'+DBDetails.get('dbName'),
				DBDetails.get('user'), DBDetails.get('password'), DBDetails.get('driver'))
				break
			case "org.postgresql.Driver":
				sql = Sql.newInstance('jdbc:postgresql://'+DBDetails.get('host')+':'+DBDetails.get('port')+'/'+DBDetails.get('dbName')+'?currentSchema='+DBDetails.get('schema'),
				DBDetails.get('user'), DBDetails.get('password'), DBDetails.get('driver'))
				break
			case "oracle.jdbc.driver.OracleDriver":
				sql = Sql.newInstance('jdbc:oracle:thin:@//'+DBDetails.get('host')+':'+DBDetails.get('port')+'/'+DBDetails.get('service_name'),
				DBDetails.get('user'), DBDetails.get('password'), DBDetails.get('driver'))
				break
			//This is for MDBTT
			case "com.ztesoft.zsmart.jdbc.qdb.QDBDriver":
				sql = Sql.newInstance('jdbc:qdb:@//'+DBDetails.get('host')+':'+DBDetails.get('port')+'/'+DBDetails.get('service_name'),
				DBDetails.get('user'), DBDetails.get('password'), DBDetails.get('driver'))
				break
		}
	}

	/**
	 * execute a SQL query on database
	 * @param queryString SQL query string
	 * @return a reference to returned data collection, an instance of java.sql.ResultSet
	 */

	//Executing the constructed Query and Saving results in resultset
	@Keyword
	def executeQueryAndDisplay (String queryString) {
		KeywordUtil.logInfo('  RESULT OF QUERY  --> ' + queryString + '\n')
		KeywordUtil.logInfo('-' * 40)
		sql.eachRow(queryString) { resultSet ->
			def meta = resultSet.getMetaData()
			if (meta.columnCount <= 0) return
				for (i in 0..<meta.columnCount) {
					KeywordUtil.logInfo(meta.getColumnLabel(i + 1)+' :' + ' ' + resultSet[i]?.toString())
				}
			KeywordUtil.logInfo('-' * 40)
		}
	}
	@Keyword
	def executeQueryAndDisplay2 (String queryString) {
		String tablePrint = "NETWORK_TYPE\t"+ "NETWORK_TYPE_NAME\t"+ "COMMENTS\n"
		sql.query(queryString) { resultSet ->
			def dbTablePrinter = new DBTablePrinter()
			dbTablePrinter.printResultSet(resultSet)
		}
		//KeywordUtil.logInfo(tablePrint)
	}
	@Keyword
	def executeQueryAndDisplay3 (String queryString, String test_step_desc) {
		String result = test_step_desc+'  RESULT OF QUERY  --> ' + queryString + '\n'+('-' * 40)+'\n'
		sql.eachRow(queryString) { resultSet ->
			def meta = resultSet.getMetaData()
			if (meta.columnCount <= 0) return
				for (i in 0..<meta.columnCount) {
					result = result+meta.getColumnLabel(i + 1)+' :' + ' ' + resultSet[i]?.toString()+'\n'
				}
			result = result+('-' * 40)+'\n'
		}
		KeywordUtil.logInfo(result)
	}
	@Keyword
	def queryDB(String DB, String query){
		setDBDetails(DB)
		connectDB ()
		queryResult = executeQuery (query)
		closeDBConnection()
		return queryResult
	}

	@Keyword
	def displayQueryResult(String DB, String query){
		setDBDetails(DB)
		connectDB ()
		executeQueryAndDisplay (query)
		closeDBConnection()
	}
	@Keyword
	def displayQueryResult2(String DB, String query){
		setDBDetails(DB)
		connectDB ()
		executeQueryAndDisplay2 (query)
		closeDBConnection()
	}
	@Keyword
	def displayQueryResult3(String DB, String query, String test_step_desc){
		setDBDetails(DB)
		connectDB ()
		executeQueryAndDisplay3 (query,test_step_desc)
		closeDBConnection()
	}
	@Keyword
	def countQueryCheck(String DB, String query){
		setDBDetails(DB)
		connectDB ()
		executeQueryAndRetry (query)
		closeDBConnection()
	}
	@Keyword
	def executeQueryAndRetry (String queryString) {
		int count=0
		int max_wait_time=120
		int time_count=0
		while(count == 0) {
			Thread.sleep(5000)
			sql.eachRow(queryString) { resultSet ->
				count = resultSet.getInt("COUNT(*)")
			}
			time_count=time_count+5
			if(time_count >= max_wait_time) {
				break
			}else {
				println 'Total wait time :'+time_count+' second'
			}
		}
	}
	@Keyword
	def setDBDetails (String DB){
		switch (DB){
			case "AutomationDB":
				DBDetails  = GlobalVariable.AutomationDB
				break
			case "SmartPoinDB":
				DBDetails = GlobalVariable.SmartPoinDB
				break
			case "OnboardingDB":
				if(GlobalVariable.BillingDB_Used==1) {
					DBDetails = GlobalVariable.BillingDB_shard1
				}else if(GlobalVariable.BillingDB_Used==2) {
					DBDetails = GlobalVariable.BillingDB_shard2
				}else if(GlobalVariable.BillingDB_Used==3) {
					DBDetails = GlobalVariable.BillingDB_shard3
				}else {
					DBDetails = GlobalVariable.BillingDB_shard1
				}
				break
			case "BillingDB":
				if(GlobalVariable.BillingDB_Used==1) {
					DBDetails = GlobalVariable.BillingDB_shard1
				}else if(GlobalVariable.BillingDB_Used==2) {
					DBDetails = GlobalVariable.BillingDB_shard2
				}else if(GlobalVariable.BillingDB_Used==3) {
					DBDetails = GlobalVariable.BillingDB_shard3
				}else {
					DBDetails = GlobalVariable.BillingDB_shard1
				}
				break
			case "BillingMDBTT":
				DBDetails = GlobalVariable.BillingMDBTT
				break
		}
	}

	@Keyword
	def executeQuery (String query) {

		def results = []
		sql.eachRow(query) {
			results << it.toRowResult()
		}
		println results
		return results
		/*sql.query(queryString) { queryResult ->
		 while (queryResult.next()) {
		 }
		 }*/
	}
	@Keyword
	def closeDBConnection(){
		if (sql != null){
			sql.close()
		}
		sql = null
	}
	@Keyword
	def executeUpdateQuery (String DB,String query){
		setDBDetails(DB)
		connectDB ()
		int row = sql.executeUpdate(query)
		closeDBConnection()
		return row
	}

}
