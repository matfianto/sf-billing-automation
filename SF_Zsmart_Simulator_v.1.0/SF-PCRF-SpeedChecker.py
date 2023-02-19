import sys
import os
import datetime
from binascii import *
from datetime import *
#import paramiko
#import warnings
#warnings.filterwarnings(action='ignore',module='.*hazmat.*')
sys.path.append("..")

from libDiameter import *
#from ocs_webservice import *
 
#Define Param for GPRS here
if len(sys.argv)-1 < 3:
  print 'Usage: SF-PCRF-SpeedChecker.py <initial/terminate> <Session-Name> <MDN>'
  sys.exit(2)
event=sys.argv[1]
SESSION=sys.argv[2]
MDN=sys.argv[3]
#event='terminate'
#SESSION='ludfi2;PCRF;1615288483'
#MDN='6288295294349'
#6288295293106
#Target OCS-OLC Host and Port
HOST='10.17.85.24'
PORT_ZTE=6101

#HOST_UX='10.17.85.22'
#USERNAME_UX='pcrf'
#PASSWORD_UX='pcrf'

#Load dictionary XML 
LoadDictionary("./FULL_DICTIONARY.xml")
#print 'Loaded Dictionary'

#Debug mode to show the AVPs
DEBUG_MODE = 0

#prepare connection to target host
#print '---- Prepare Connection ----'
Conn=Connect(HOST,PORT_ZTE)
#print 'Connected to ',HOST,':',PORT_ZTE

# generated session ID based on Name of event
def create_Session_Id():
 now=datetime.now()
 ret=SESSION+";PCRF;"+str(int(time.time()))
 return ret

#build CER message
def send_cer_message_ZTE():
 CER_avps=[ ]
 #CER_avps.append(encodeAVP('Host-IP-Address', '10.17.85.24'))
 CER_avps.append(encodeAVP('Origin-Host', 'yudho2.zte.com.cn'))
 CER_avps.append(encodeAVP('Origin-Realm', 'zte.com.cn'))
 #CER_avps.append(encodeAVP('Vendor-ID', 0))
 CER_avps.append(encodeAVP('Destination-Host', 'pcrf.zte.com.cn'))
 CER_avps.append(encodeAVP('Destination-Realm', 'zte.com.cn'))
 CER_avps.append(encodeAVP('Auth-Application-Id', 4))

# Create message header (empty)
 CER=HDRItem()
# Set command code
 CER.cmd=dictCOMMANDname2code('Capabilities-Exchange')
# Set Hop-by-Hop and End-to-End
 initializeHops(CER)
# Add AVPs to header and calculate remaining fields
 msg=createReq(CER,CER_avps)
 #print 'Created request with CER AVPs'
# msg now contains CER Request as hex string
# send data
 Conn.send(msg.decode('hex'))
 #print 'Sent request with CER AVPs'
# Receive response
 received = Conn.recv(1024000)
 #print 'Received request with CER AVPs'
 msg=received.encode('hex')
 H=HDRItem()
 stripHdr(H,msg)
 avps=splitMsgAVPs(H.msg)
 cmd=dictCOMMANDcode2name(H.flags,H.cmd)
 if cmd==ERROR:
   print 'Unknown command',H.cmd
 result_code=findAVP("Result-Code",avps)
 #print 'CER Success from ZTE port ',PORT_ZTE,' is ',result_code

#build CCR message
def send_CCR(CCR_avps):
# Create message header (empty)
 CCR=HDRItem()
# Set command code
 CCR.cmd=dictCOMMANDname2code('Credit-Control')
# Set Hop-by-Hop and End-to-End
 initializeHops(CCR)
# Add AVPs to header and calculate remaining fields
 msg=createReq(CCR,CCR_avps)
# msg now contains CER Request as hex string
 S=HDRItem()
 stripHdr(S,msg)
 avpss=splitMsgAVPs(S.msg)
 cmd=dictCOMMANDcode2name(S.flags,S.cmd)
 if DEBUG_MODE == 1 :
	 if cmd==ERROR:
		print 'Unknown command returned from server',S.cmd
	 else:
	  print cmd
	 print "Hop-by-Hop=",S.HopByHop,"End-to-End=",S.EndToEnd,"ApplicationId=",S.appId
	 print "="*30
	 for avp in avpss:
		print "Decoded AVP",decodeAVP(avp)
		print "-"*30 

# send data
 Conn.send(msg.decode('hex'))
 #print 'Sending message'
# Receive response
 received = Conn.recv(102400)
 #print 'Received Response'
# Parse and display received CCA ANSWER
# print "="*30
# print "THE CCA ANSWER IS:"
 msg=received.encode('hex')
# print "="*30

 H=HDRItem()
 stripHdr(H,msg)
 avps=splitMsgAVPs(H.msg)
 cmd=dictCOMMANDcode2name(H.flags,H.cmd)
# print 'Start checking DEBUG MODE'
 if DEBUG_MODE == 1:
	 if cmd==ERROR:
		print 'Unknown command returned from server',H.cmd
	 else:
		print cmd
	 print "Hop-by-Hop=",H.HopByHop,"End-to-End=",H.EndToEnd,"ApplicationId=",H.appId
	 print "="*30
	 for avp in avps:
		print "Decoded AVP",decodeAVP(avp)
		print "-"*30
 return avps

#GPRS Initial usage event 
def send_pcrf_init(session_id,Anumber):
 CCR_avps=[]
 CCR_avps.append(encodeAVP('Session-ID', session_id)) 
 CCR_avps.append(encodeAVP('Origin-Host', 'yudho2.zte.com.cn'))
 CCR_avps.append(encodeAVP('Origin-Realm', 'zte.com.cn'))
 CCR_avps.append(encodeAVP('Destination-Realm', 'zte.com.cn'))
 CCR_avps.append(encodeAVP('Auth-Application-Id', 16777238))
 CCR_avps.append(encodeAVP('Service-Context-ID', 'version1.ccg@chinatelecom.com'))
 CCR_avps.append(encodeAVP('Destination-Host', 'pcrf.zte.com.cn'))
 CCR_avps.append(encodeAVP('Origin-State-Id', 189))
 CCR_avps.append(encodeAVP('CC-Request-Type', 1))
 CCR_avps.append(encodeAVP('CC-Request-Number', 1))
 CCR_avps.append(encodeAVP('Network-Request-Support', 0))
 CCR_avps.append(encodeAVP('Bearer-Operation', 3))
 CCR_avps.append(encodeAVP('Framed-IP-Address', 'AC102D42'))
 CCR_avps.append(encodeAVP('3GPP-SGSN-MCC-MNC', '51009'))
 CCR_avps.append(encodeAVP('3GPP-User-Location-Info', '0'))
 CCR_avps.append(encodeAVP('Bearer-Usage', 0))
 CCR_avps.append(encodeAVP('Online', 1))
 CCR_avps.append(encodeAVP('Offline', 0))
 CCR_avps.append(encodeAVP('Subscription-ID', [encodeAVP('Subscription-ID-Data', Anumber),encodeAVP('Subscription-ID-Type', 0)
 ]))
 avps=send_CCR(CCR_avps)
 result_code = findAVP("Result-Code", avps)
 APN_UL=''
 APN_DL=''
 if result_code == 2001 :
    try :
        result_avp=[]
        for avp in avps :
            (name,ret)=decodeAVP(avp)
            if name=="QoS-Information" :
                result_avp.append(ret)
        APN_UL = findAVP("APN-Aggregate-Max-Bitrate-UL",result_avp[1])
        APN_DL = findAVP("APN-Aggregate-Max-Bitrate-DL",result_avp[1])
    except:
        for avp in avps :
            (name,ret)=decodeAVP(avp)
            if name=="Charging-Rule-Install" :
                try :
                    APN_UL = findAVP("APN-Aggregate-Max-Bitrate-UL", findAVP("QoS-Information",findAVP("Charging-Rule-Definition",ret)))
                    APN_DL = findAVP("APN-Aggregate-Max-Bitrate-DL",findAVP("QoS-Information",findAVP("Charging-Rule-Definition",ret)))
                except :
                    pass
 return result_code, APN_UL, APN_DL

def send_pcrf_terminate(session_id,Anumber):
 CCR_avps=[]
 CCR_avps.append(encodeAVP('Session-ID', session_id)) 
 CCR_avps.append(encodeAVP('Origin-Host', 'yudho2.zte.com.cn'))
 CCR_avps.append(encodeAVP('Origin-Realm', 'zte.com.cn'))
 CCR_avps.append(encodeAVP('Destination-Realm', 'zte.com.cn'))
 CCR_avps.append(encodeAVP('Auth-Application-Id', 16777238))
 CCR_avps.append(encodeAVP('Service-Context-ID', 'version1.ccg@chinatelecom.com'))
 CCR_avps.append(encodeAVP('Destination-Host', 'pcrf.zte.com.cn'))
 CCR_avps.append(encodeAVP('Origin-State-Id', 189))
 CCR_avps.append(encodeAVP('CC-Request-Type', 3))
 CCR_avps.append(encodeAVP('CC-Request-Number', 3))
 CCR_avps.append(encodeAVP('Network-Request-Support', 0))
 CCR_avps.append(encodeAVP('Bearer-Operation', 3))
 CCR_avps.append(encodeAVP('Framed-IP-Address', 'AC102D42'))
 CCR_avps.append(encodeAVP('3GPP-SGSN-MCC-MNC', '51009'))
 CCR_avps.append(encodeAVP('3GPP-User-Location-Info', '0'))
 CCR_avps.append(encodeAVP('Bearer-Usage', 0))
 CCR_avps.append(encodeAVP('Online', 1))
 CCR_avps.append(encodeAVP('Offline', 0))
 CCR_avps.append(encodeAVP('Subscription-ID', [encodeAVP('Subscription-ID-Data', Anumber),encodeAVP('Subscription-ID-Type', 0)
 ]))
 avps=send_CCR(CCR_avps)
 result_code = findAVP("Result-Code", avps)
 if result_code == 2001 :
		result_code = findAVP("Result-Code", avps)
 return result_code

#def get_last_file():
#    stdin, stdout, stderr = client.exec_command("""cd log && ls ULOG*_PDE | tail -1""")
#    error = stderr.read()
#    if error=='':
#		return stdout.read().strip()
#    else :
#		return error
#
#def get_line(session_id, last_file):
#    session=session_id.replace(';','\;')
#    #print """grep -n -e 'Session-Id\s=\s\["""+session+"""\]' -e 'Auth-Application-Id\s=\s\[3902001\]' log/"""+last_file+""" | tail | cut -d ':' -f1"""
#    stdin, stdout, stderr = client.exec_command("""grep -n -e 'Session-Id\s=\s\["""+session+"""\]' -e 'Auth-Application-Id\s=\s\[3902001\]' log/"""+last_file+""" | tail | cut -d ':' -f1""")
#    error = stderr.read()
#    if error=='':
#		return stdout.read().strip()
#    else :
#		return error
#
#def get_text(line_start, last_file):
#    line_end=str(int(line_start)+50)
#    stdin, stdout, stderr = client.exec_command("""cd log && sed -n """+line_start+""","""+line_end+"""p """+last_file)
#    error = stderr.read()
#    if error=='':
#		return stdout.read().strip()
#    else :
#		return error

#print 'Send CER message'
send_cer_message_ZTE()
#print 'CER Sent'	

#client = paramiko.SSHClient()
#client.load_system_host_keys()
#client.set_missing_host_key_policy(paramiko.AutoAddPolicy())
#client.connect(HOST_UX, 22, USERNAME_UX, PASSWORD_UX)

#session_id=create_Session_Id()
session_id=SESSION
#print send_pcrf_terminate('ludfi;PCRF;1615265985','6288295294349')
#exit()
print '------------------------------'
print 'Execution at ',datetime.now()
print 'Sending PCRF Speed Check --> Session-Name:',session_id,' # MDN:',MDN
if event == 'initial' :
    result_code, APN_UL, APN_DL=send_pcrf_init(session_id,MDN)
    if result_code == 2001:
        print 'Event PCRF Speed Check success in Initial  ###',session_id,'### Result Code', result_code
        #last_file = get_last_file()
        #a=get_line(session_id, last_file)
        #data=a.split("\n")
        #b=''
        #for c in range ( 0, len(data)):
        #    print data[c]
        #    if c != len(data)-1 :
        #        if int(data[c].strip())+1== int(data[c+1].strip()) :
        #            b=data[c].strip()
        #
        #if b != '' :
        #    text_raw=get_text(b, last_file)
        #    text_temp=text_raw.split('\n\n')
        print '    APN-Aggregate-Max-Bitrate-UL :',APN_UL
        print '    APN-Aggregate-Max-Bitrate-DL :',APN_DL
    else :
        print 'Event PCRF Speed Check failed in Initial  ###',session_id,'### Result Code', result_code
elif event == 'terminate' :
    result_code2=send_pcrf_terminate(session_id,MDN)
    #result_code2=2001
    if result_code2 == 2001:
        print 'Event PCRF Speed Check success in Terminate  ###',session_id,'### Result Code', result_code2
        
    else :
        print 'Event PCRF Speed Check failed in Terminate  ###',session_id,'### Result Code', result_code2

print '------------------------------'
#client.close()
