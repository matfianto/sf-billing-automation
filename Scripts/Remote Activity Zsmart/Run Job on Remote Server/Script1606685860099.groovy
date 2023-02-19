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
import java.util.Properties as Properties
import com.jcraft.jsch.*
import com.jcraft.jsch.JSch as JSch
import com.jcraft.jsch.Session as Session
import java.util.regex.Pattern as Pattern
import java.io.BufferedReader as BufferedReader
import java.io.InputStream as InputStream

def host = "10.17.85.26"
def user = "ocs"
def password = "ocs"
def port = 22
def command = "source ~/.bash_profile; cd bin; pwd ; RecurrRate -e 6 -b 1 -d 20201121 -s 3701686083 -L 5"

CustomKeywords.'com.smartfren.billing.remoteUtility.runRemoteCommandJob'(xhost, xusername, xpassword, xport, xcommand, xendOfLine)
