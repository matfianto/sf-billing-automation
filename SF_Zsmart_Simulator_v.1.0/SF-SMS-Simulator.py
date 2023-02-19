import sys
import os
import datetime
from binascii import *
from datetime import *
sys.path.append("..")

from libDiameter import *
#from ocs_webservice import *
 
#Define Param for GPRS here
#SMS Param --> session_id,Timestamp,Anumber,Bnumber
if len(sys.argv)-1 < 4:
  print 'Usage: SF-SMS-Simulator.py <Session-Name> <MDN-A> <MDN-B> <TIME>'
  print 'Input param "default" will set value Session-Name:default MDN-A:6288295292653 MDN-B:6288295292656 TIME:default-now'
  sys.exit(2)
SESSION=sys.argv[1]
A_NO=sys.argv[2]
B_NO=sys.argv[3]
TIMEINP=sys.argv[4]

#Target OCS-OLC Host and Port
HOST='10.17.85.30'
PORT_ZTE=6112

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
 ret=SESSION+";SMS;"+str(int(time.time()))
 #ret="galih;SMS;"+str(int(time.time()))
 return ret

#build CER message
def send_cer_message_ZTE():
 CER_avps=[ ]
 CER_avps.append(encodeAVP('Host-IP-Address', '10.8.12.82'))
 CER_avps.append(encodeAVP('Origin-Host', '10.8.12.82'))
 CER_avps.append(encodeAVP('Origin-Realm', 'galih.com.cn'))
 CER_avps.append(encodeAVP('Vendor-ID', 0))
 CER_avps.append(encodeAVP('Product-Name', 'zte.com.cn'))
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
 #print 'CER response from ZTE port ',PORT_ZTE,' is ',result_code

#build CCR message
def send_CCR(CCR_avps, port):
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

#SMS usage event
def send_sms(session_id,Timestamp,Anumber,Bnumber):
 CCR_avps=[]
 CCR_avps.append(encodeAVP('Session-ID', session_id)) 
 CCR_avps.append(encodeAVP('Origin-Host', 'SCP001.francetelecom.com'))
 CCR_avps.append(encodeAVP('Origin-Realm', 'francetelecom.com'))
 CCR_avps.append(encodeAVP('Destination-Realm', 'francetelecom.com'))
 CCR_avps.append(encodeAVP('Auth-Application-Id', 4))
 CCR_avps.append(encodeAVP('Service-Context-ID', 'version1.p2psms@chinatelecom.com'))
 CCR_avps.append(encodeAVP('CC-Request-Type', 4))
 CCR_avps.append(encodeAVP('CC-Request-Number', 0))
 CCR_avps.append(encodeAVP('Event-Timestamp', Timestamp))
 CCR_avps.append(encodeAVP('Subscription-ID', [
  encodeAVP('Subscription-ID-Data', Anumber),
  encodeAVP('Subscription-ID-Type', 0)
 ]))
 CCR_avps.append(encodeAVP('Requested-Action', 0))
 CCR_avps.append(encodeAVP('Service-Information', [ 
    encodeAVP('P2PSMS-Information', [
      encodeAVP('OA-Subscription-Id', [
        encodeAVP('Subscription-ID-Data',Anumber),
        encodeAVP('Subscription-ID-Type',0)
      ]),
      encodeAVP('DA-Subscription-Id', [
        encodeAVP('Subscription-ID-Data',Bnumber),
        encodeAVP('Subscription-ID-Type',0)
      ]),
      encodeAVP('SMSC-Address','2996'),
      encodeAVP('SM-Id','490'),
      encodeAVP('SM-Length','456')
    ]),
    encodeAVP('IN-Information',[
      encodeAVP('Calling-CellID-Or-SAI', '0'),
      encodeAVP('MSC-Address', '0')
    ])
 ]))
 port=2
 avps=send_CCR(CCR_avps, port)
 result_code = findAVP("Result-Code", avps)
 if result_code == 2001 :
		result_code = findAVP("Result-Code", avps)
 return result_code

def timestamp(data):
	Timestamp=time.mktime(datetime.strptime(data, "%Y-%m-%d-%H-%M-%S").timetuple())
	return Timestamp



#print 'Send CER message'
send_cer_message_ZTE()

#print 'CER Sent'
		#LoadDictionary("./IN.xml")		
session_id=create_Session_Id()
TIMEINP=sys.argv[4]
if TIMEINP == 'default':
 Timestamp=int(time.time())
else:
 Timestamp=int(TIMEINP)
 print 'Input Timestamp',Timestamp
if A_NO == 'default':
 A_NO = '6288295292653'
if B_NO == 'default':
 B_NO = '6288295292656'
print 'Execution at ',datetime.now()
print 'Sending SMS Usage--> Session-Name:',session_id,' ## MDN A:',A_NO,' ## MDN B:',B_NO,' ## TIME:',datetime.fromtimestamp(Timestamp)
#SMS session_id,Timestamp,Anumber,Bnumber
result_code=send_sms(session_id,Timestamp,A_NO,B_NO)
if result_code == 2001:
 print 'SMS Event','success ###',session_id, '### Result Code', result_code 
else :
 print 'SMS Event','failed, Please check from by manual run###',session_id, '### Result Code', result_code
print '-----------------------------------------------------------------------------------------------------------------------------------------------------------------'