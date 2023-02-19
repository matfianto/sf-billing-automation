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
import com.kms.katalon.core.webui.common.ExecuteCommandAction.StreamGobbler
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import java.util.Properties as Properties
import com.jcraft.jsch.*
import com.jcraft.jsch.JSch as JSch
import com.jcraft.jsch.Session as Session
import java.util.regex.Pattern as Pattern
import java.io.BufferedReader as BufferedReader
import java.io.InputStream as InputStream
import com.smartfren.ssh.*

public class remoteUtility {

	/*
	 * This method created by galih rakasiwa on 20-Nov-2020.
	 * This method to connect to remote OSC testbed and run job command line and stop while log contains some string
	 * This method initially created to support if any billing test case require to run Job on remote server
	 */
	@Keyword
	runRemoteCommandJob (String host, String user, String password, int port, String command, String endOfLine){

		Session sshSession
		try {
			JSch jschSSH = new JSch()
			sshSession = jschSSH.getSession(user, host, 22)
			sshSession.setPassword(password)
			java.util.Properties config = new java.util.Properties()
			config.put("StrictHostKeyChecking", "no")
			sshSession.setConfig(config)
			sshSession.connect(5000)
			KeywordUtil.logInfo("connect to remote server : "+user+"@"+host)
			Channel channel=sshSession.openChannel("shell")
			OutputStream ops = channel.getOutputStream()
			PrintStream ps = new PrintStream(ops, true)
			channel.connect()
			ps.println(command)
			//give commands to be executed inside println.and can have any no of commands sent.
			KeywordUtil.logInfo("Run Job on remote server : "+command)
			InputStream is = channel.getInputStream()
			byte[] bt=new byte[4096]
			int i
			String str
			while(true)
			{
				i=is.read(bt, 0, 4096);
				str = new String(bt, 0, i)
				//KeywordUtil.logInfo("------Job in Progress----- \n"+str)

				if (str.contains(endOfLine)){
					KeywordUtil.logInfo("----Job Completed---- \n endOfline found :"+endOfLine)
					break
				}
				else if (str.lastIndexOf('\n') > 0 && str.substring(str.lastIndexOf('\n')).matches('(?s).*'+user+'@.*\\[/'+user+'.*') == true){
					KeywordUtil.logInfo("----Job Completed WITHOUT end of job line---- : "+endOfLine+"\nPlease check the log")
					//KeywordUtil.markWarning("")
					break
				}
				sleep(3000)
			}
			ps.close()
			channel.disconnect()
			KeywordUtil.logInfo("-----disconnected channel----")
			sleep(1000)
		}
		catch (Exception e)
		{
			KeywordUtil.logInfo("Exception: "+e)
		}
		finally
		{
			sshSession.disconnect()
			KeywordUtil.logInfo("-----disconnected ssh session----")
		}
	}

	/*
	 * This method created by galih rakasiwa on 20-Nov-2020.
	 * This method to connect to remote OSC testbed and run command line and stop while log contains some string
	 * This method initially created to support if any billing test case require to run command on remote server
	 * This method can be run multiple command with semicolon (;) separated sample "whoami;hostname;cd bin;pwd"
	 */
	@Keyword
	runRemoteCommandLine (String host, String user, String password, int port, String command){

		Session sshSession
		//add exit after command line executed
		command +=";exit"
		try {
			JSch jschSSH = new JSch()
			sshSession = jschSSH.getSession(user, host, 22)
			sshSession.setPassword(password)
			java.util.Properties config = new java.util.Properties()
			config.put("StrictHostKeyChecking", "no")
			sshSession.setConfig(config)
			sshSession.connect(5000)
			KeywordUtil.logInfo("connect ssh session to remote server : "+user+"@"+host)
			Channel channel=sshSession.openChannel("shell")
			OutputStream ops = channel.getOutputStream()
			PrintStream ps = new PrintStream(ops, true)
			KeywordUtil.logInfo("connect channel to remote server : "+user+"@"+host)
			KeywordUtil.logInfo("command to be executed : "+command)
			channel.connect()
			ps.println(command)
			ps.close()
			//give commands to be executed inside println.and can have any no of commands sent.
			InputStream is = channel.getInputStream()
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line
			int index = 0
			String output = " "
			while ((line = reader.readLine()) != null) {
				output+=line+"\n"
			}
			KeywordUtil.logInfo("-----Execution Output----- :\n"+output)
			channel.disconnect()
			KeywordUtil.logInfo("-----disconnected channel----")
			sleep(1000)
		}
		catch (Exception e)
		{
			KeywordUtil.logInfo("Exception: "+e)
		}
		finally
		{
			sshSession.disconnect()
			KeywordUtil.logInfo("-----disconnected ssh session----")
		}

	}
	/*
	 * This method created by galih rakasiwa on 20-Nov-2020.
	 * This method to connect to remote OSC testbed and run job command line and stop while log contains some string
	 * This method initially created to support if any billing test case require to run Job on remote server
	 */
	@Keyword
	runUsage (String command){
		Runtime.getRuntime().exec(command)
	}

	/*
	 * This method created by Johan on 28-Jan-2021.
	 * This method to connect to remote OSC testbed and run command line then run MDBTT command and stop while log contains some string
	 * This method initially created to support if any billing test case require to execute query from MDBTT on remote server
	 * This method can be run multiple command with semicolon (;) separated sample "whoami;hostname;cd bin;pwd"
	 */
	@Keyword
	runRemoteCommandLineMDBTT (String host, String user, String password, int port, String command, String query){

		Session sshSession
		try {
			JSch jschSSH = new JSch()
			sshSession = jschSSH.getSession(user, host, 22)
			sshSession.setPassword(password)
			java.util.Properties config = new java.util.Properties()
			config.put("StrictHostKeyChecking", "no")
			sshSession.setConfig(config)
			sshSession.connect(5000)
			KeywordUtil.logInfo("connect to remote server : "+user+"@"+host)

			Channel channel=sshSession.openChannel("shell")
			//Channel channel=sshSession.openChannel("exec")
			OutputStream ops = channel.getOutputStream()
			PrintStream ps = new PrintStream(ops, true)
			channel.connect()
			ps.println(command)
			//give commands to be executed inside println.and can have any no of commands sent.
			KeywordUtil.logInfo("Run Job on remote server : "+command)
			InputStream is = channel.getInputStream()
			BufferedReader reader = new BufferedReader(new InputStreamReader(is))

			byte[] bt=new byte[4096]
			String output = " "
			while(true)
			{
				/*
				 while(is.available()>0){
				 int i = is.read(bt, 0, 4096)
				 if(i>0) break
				 KeywordUtil.logInfo("Byte :" + new String(bt, 0, i))
				 }
				 */

				String line = reader.readLine()
				if (line!=null){
					output+=line+"\n"
				}
				else{
					break
				}

				KeywordUtil.logInfo("Command line : " + line)

				sleep(3000)
			}
			KeywordUtil.logInfo("-----Execution Output----- :\n"+output)
			ps.close()
			channel.disconnect()
			KeywordUtil.logInfo("-----disconnected channel----")
			sleep(1000)
		}
		catch (Exception e)
		{
			KeywordUtil.logInfo("Exception: "+e)
		}
		finally
		{
			sshSession.disconnect()
			KeywordUtil.logInfo("-----disconnected ssh session----")
		}
	}
	@Keyword
	def runRemoteSh (String host, String user, String password, int port, String param){
		String command = "source ~/.bash_profile ; sh " + GlobalVariable.g_katalon_sh_path + GlobalVariable.g_katalon_sh + " " +param
		Session jschSession = null

		try {

			JSch jsch = new JSch();
			//jsch.setKnownHosts("/home/mkyong/.ssh/known_hosts")
			jschSession = jsch.getSession(user, host, port)
			// uses jsch.setKnownHosts
			jschSession.setConfig("StrictHostKeyChecking", "no")
			// authenticate using password
			jschSession.setPassword(password)
			// 10 seconds timeout session
			jschSession.connect(5000);
			KeywordUtil.logInfo("connect ssh session to remote server : "+user+"@"+host)
			ChannelExec channelExec = (ChannelExec) jschSession.openChannel("exec")
			KeywordUtil.logInfo("command to be executed : "+command)
			// run a shell script
			channelExec.setCommand(command)
			//channelExec.setCommand("cat /ocs/katalon/katalon_out.txt")
			// display errors to System.err
			channelExec.setErrStream(System.err)
			InputStream is = channelExec.getInputStream()
			// 5 seconds timeout channel
			channelExec.connect(5000)
			// read the result from remote server
			byte[] tmp = new byte[4096];
			while (true) {
				while (is.available() > 0) {
					int i = is.read(tmp, 0, 4096);
					if (i < 0) break;
					//System.out.print(new String(tmp, 0, i))
					KeywordUtil.logInfo(new String(tmp, 0, i))
				}
				if (channelExec.isClosed()) {
					if (is.available() > 0) continue;
					if (channelExec.getExitStatus()==0){
						KeywordUtil.logInfo("Script running successfully with exit status: "+channelExec.getExitStatus())
					}
					//System.out.println("exit-status: "+ channelExec.getExitStatus())
					break;
				}
				try {
					Thread.sleep(1000)
				} catch (Exception ee) {
				}
			}
			channelExec.disconnect()
		} catch (JSchException | IOException e) {
			e.printStackTrace()
		} finally {
			if (jschSession != null) {
				jschSession.disconnect()
			}
		}
	}
	/*
	 * This method created by Ludfi Eka Lesmana on 09-Mar-2021.
	 * This method to connect to PCRF environment log
	 * This method initially created to support if package information checker
	 */
	@Keyword
	runRemoteCommandReturn (String host, String username, String password, String command){
		SSHConnectorExec ssh = new SSHConnectorExec(host, username, password);
		String result="";
		if (ssh.createNewSession()) {
			try {
				result = ssh.sendCommand(command);
			} catch (NoSuchElementException ex) {
			}
			ssh.closeSession();
		}
		return result;
	}
	/*
	 * This method created by Ludfi Eka Lesmana on 12-Mar-2021.
	 * This method to execute remote task
	 * This method initially created to support running python file on server
	 */
	@Keyword
	runRemoteShellReturn (String host, String username, String password, List<String> commands){
		try {
			SSHConnectorShell ssh = new SSHConnectorShell(host, username, password);
			String text = ssh.run(commands);
			StringTokenizer token = new StringTokenizer(text, "\n");
			String line;
			String result=''
			int flag = 0
			while (token.hasMoreTokens()) {
				line = token.nextToken();
				if (line.contains("------------------------------")) {
					flag++
				}
				if (flag == 1) {
					result=result+line+'\n'
				}else if(flag == 2) {
					result=result+line
					break
				}
			}
			if(result == '') {
				return text
			}
			return result;
		}
		catch (Exception e){
			KeywordUtil.logInfo("Exception: "+e)
			return null;
		}
	}

	@Keyword
	runRemoteShellReturn2 (String host, String username, String password, List<String> commands){
		try {
			SSHConnectorShell ssh = new SSHConnectorShell(host, username, password);
			String text = ssh.run(commands);
			StringTokenizer token = new StringTokenizer(text, "\n");
			String line;
			String result=''
			int flag = 0
			while (token.hasMoreTokens()) {
				line = token.nextToken();
				if (line.contains("#################################")) {
					flag++
				}
				if (flag == 1) {
					result=result+line+'\n'
				}else if(flag == 2) {
					result=result+line
					break
				}
			}
			if(result == '') {
				return text
			}
			return result;
		}
		catch (Exception e){
			KeywordUtil.logInfo("Exception: "+e)
			return null;
		}
	}
	
	@Keyword
	runRemoteShellReturn3 (String host, String username, String password, List<String> commands){
		try {
			SSHConnectorShellPrivate ssh = new SSHConnectorShellPrivate(host, username, password);
			String text = ssh.run(commands);
			StringTokenizer token = new StringTokenizer(text, "\n");
			String line;
			String result=''
			int flag = 0
			while (token.hasMoreTokens()) {
				line = token.nextToken();
				if (line.contains("------------------------------")) {
					flag++
				}
				if (flag == 1) {
					result=result+line+'\n'
				}else if(flag == 2) {
					result=result+line
					break
				}
			}
			if(result == '') {
				return text
			}
			return result;
		}
		catch (Exception e){
			KeywordUtil.logInfo("Exception: "+e)
			return null;
		}
	}
}
