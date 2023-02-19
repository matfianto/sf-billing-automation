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
import java.io.File as File
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import java.awt.image.BufferedImage as BufferedImage
import java.io.File as File
import java.io.BufferedReader as BufferedReader
import javax.imageio.ImageIO as ImageIO


WebUI.openBrowser('')
WebUI.navigateToUrl('https://10.17.85.25:8090/cvbs/')
WebUI.click(findTestObject('Web Zsmart/1 Pre Home Page/More information procced'))
WebUI.click(findTestObject('Web Zsmart/1 Pre Home Page/Go on to the webpage'))
WebUI.waitForPageLoad(3)
for (int i in 0..2 ) {	
	WebUI.setText(findTestObject('Web Zsmart/2 Login Page/input_edt_username'),xusername)	
	WebUI.setText(findTestObject('Web Zsmart/2 Login Page/input_edt_pwd'),xpassword)	
	String captcha = CustomKeywords.'com.smartfren.billing.utility.getCaptcha'()
	KeywordUtil.logInfo("captcha: "+captcha)
	WebUI.setText(findTestObject('Web Zsmart/2 Login Page/input_edt_code'),captcha)
	WebUI.click(findTestObject("Web Zsmart/2 Login Page/LoginOKButton"))
	WebUI.getUrl()	
	if (WebUI.getUrl().contains("Index.jsp")==true){
		break
	}
	WebUI.click(findTestObject("Web Zsmart/2 Login Page/input_imgLoginBt"))
	i++
}
WebUI.closeBrowser()





