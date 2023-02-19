import sys
import os
import datetime
from binascii import *
from datetime import *
sys.path.append("..")

from libDiameter import *
#from ocs_webservice import *
 
#Define Param for GPRS here
#VOICE Param --> session_id,Anumber,Bnumber,Timestamp,CCTime,ANI
if len(sys.argv)-1 < 5:
  print 'Usage: SF-VOICE-Simulator.py <Session-Name> <MDN-A> <MDN-B> <TIME> <DURATION>'
  print 'Input param "default" will set value Session-Name:default MDN-A:6288295291295 MDN-B:6288295291296 TIME:default-now DURATION:10'
  sys.exit(2)
SESSION=sys.argv[1]
A_NO=sys.argv[2]
B_NO=sys.argv[3]
TIMEINP=sys.argv[4]
DURATION=sys.argv[5]

#Target OCS-OLC Host and Port
HOST='10.16.46.190'
PORT_ZTE=6099

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
 ret=SESSION+";VOICE;"+str(int(time.time()))
 #ret="tas.ims.mnc009.mcc510.3gppnetwork.org;1268500979;"+str(int(time.time()))
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

#VOICE Initial usage event
#send_voice_init(session_id,0,Timestamp,'6288295291295','6288295291297',200)
def send_voice_init(session_id,CCRequestNumber,Timestamp,Anumber,Bnumber):
 CCR_avps=[]
 CCR_avps.append(encodeAVP('Session-ID', session_id)) 
 CCR_avps.append(encodeAVP('Origin-Host', 'SCP001.chinatelecom.com'))
 CCR_avps.append(encodeAVP('Origin-Realm', 'chinatelecom.com'))
 CCR_avps.append(encodeAVP('Destination-Realm', 'chinatelecom.com'))
 CCR_avps.append(encodeAVP('Auth-Application-Id', 4))
 CCR_avps.append(encodeAVP('Service-Context-ID', 'version1.in@chinatelecom.com'))
 CCR_avps.append(encodeAVP('CC-Request-Type', 1))
 CCR_avps.append(encodeAVP('CC-Request-Number', CCRequestNumber))
 CCR_avps.append(encodeAVP('Event-Timestamp', Timestamp))
 CCR_avps.append(encodeAVP('Subscription-ID', [
  encodeAVP('Subscription-ID-Data', Anumber),
  encodeAVP('Subscription-ID-Type', 0)
 ]))
 CCR_avps.append(encodeAVP('Requested-Action', 0))
 CCR_avps.append(encodeAVP('Service-Information', [ 
    encodeAVP('IN-Information', [
      encodeAVP('Calling-Party-Number', Anumber),
      encodeAVP('Called-Party-Number', Bnumber),
      encodeAVP('Service-Key', 1),
      encodeAVP('Calling-Vlr-Number', '086149051'),
      encodeAVP('Calling-CellID-Or-SAI', '0'),
      encodeAVP('Calling-LAI', '086149051'),
      encodeAVP('Called-Vlr-Number', '0861390510'),
      encodeAVP('Called-CellID-Or-SAI', '0861390510'),
      encodeAVP('Called-LAI', '0861390510'),
      encodeAVP('Bearer-Capability', '0'),
      encodeAVP('EventType-BCSM', 2),
      encodeAVP('IMSI', '1234567890123456'),
      encodeAVP('Call-Reference-Number', '123456'),
      encodeAVP('MSC-Address', '0')
    ])
 ]))

 port=2
 avps=send_CCR(CCR_avps, port)
 result_code = findAVP("Result-Code", avps)
 if result_code == 2001 :
		result_code = findAVP("Result-Code", avps)
 return result_code

#GPRS Update usage event 
def send_voice_update(session_id,CCRequestNumber,Timestamp,Anumber,RAT_Type,RatingGroup,User_Equip,ReqUnit,UseUnit,CCTime,IMSI):
 CCR_avps = []
 CCR_avps.append(encodeAVP('Session-ID', session_id))
 CCR_avps.append(encodeAVP('Origin-Host', 'SCP001.chinatelecom.com'))
 CCR_avps.append(encodeAVP('Origin-Realm', 'chinatelecom.com'))
 CCR_avps.append(encodeAVP('Destination-Realm', 'chinatelecom.com'))
 CCR_avps.append(encodeAVP('Auth-Application-Id', 1))
 CCR_avps.append(encodeAVP('Service-Context-ID', 'version1.ccg@chinatelecom.com'))
 CCR_avps.append(encodeAVP('CC-Request-Type', 2))
 CCR_avps.append(encodeAVP('CC-Request-Number', CCRequestNumber))
 CCR_avps.append(encodeAVP('Event-Timestamp', Timestamp))
 CCR_avps.append(encodeAVP('Subscription-ID', [
  encodeAVP('Subscription-ID-Data', Anumber),
  encodeAVP('Subscription-ID-Type', 0)
 ]))
 CCR_avps.append(encodeAVP('Requested-Action', 0))
 CCR_avps.append(encodeAVP('Service-Information', [ 
    encodeAVP('IN-Information', [
      encodeAVP('Calling-Party-Number', Anumber),
      encodeAVP('Called-Party-Number', Bnumber),
      encodeAVP('Service-Key', 1),
      encodeAVP('Calling-Vlr-Number', '086149051'),
      encodeAVP('Calling-CellID-Or-SAI', '0'),
      encodeAVP('Calling-LAI', '086149051'),
      encodeAVP('Called-Vlr-Number', '0861390510'),
      encodeAVP('Called-CellID-Or-SAI', '0861390510'),
      encodeAVP('Called-LAI', '0861390510'),
      encodeAVP('Bearer-Capability', '0'),
      encodeAVP('EventType-BCSM', 2),
      encodeAVP('IMSI', '1234567890123456'),
      encodeAVP('Call-Reference-Number', '123456'),
      encodeAVP('MSC-Address', '0')
    ])
 ]))
 CCR_avps.append(encodeAVP('Used-Service-Unit', [
   encodeAVP('CC-Total-Octets', UseUnit),
   encodeAVP('CC-Time', 0)
 ]))

 port=2
 avps=send_CCR(CCR_avps, port)
 result_code = findAVP("Result-Code", avps)
 if result_code == 2001 :
		result_code = findAVP("Result-Code", findAVP("Multiple-Services-Credit-Control", avps))
 return result_code

#VOICE terminate usage event 	
def send_voice_terminate(session_id,CCRequestNumber,Timestamp,Anumber,Bnumber,CCTime):
 CCR_avps = []
 CCR_avps.append(encodeAVP('Session-ID', session_id))
 CCR_avps.append(encodeAVP('Origin-Host', 'SCP001.chinatelecom.com'))
 CCR_avps.append(encodeAVP('Origin-Realm', 'chinatelecom.com'))
 CCR_avps.append(encodeAVP('Destination-Realm', 'chinatelecom.com'))
 CCR_avps.append(encodeAVP('Auth-Application-Id', 4))
 CCR_avps.append(encodeAVP('Service-Context-ID', 'version1.in@chinatelecom.com'))
 CCR_avps.append(encodeAVP('CC-Request-Type', 3))
 CCR_avps.append(encodeAVP('CC-Request-Number', CCRequestNumber))
 CCR_avps.append(encodeAVP('Event-Timestamp', Timestamp))
 CCR_avps.append(encodeAVP('Subscription-ID', [
  encodeAVP('Subscription-ID-Data', Anumber),
  encodeAVP('Subscription-ID-Type', 0)
 ]))
 CCR_avps.append(encodeAVP('Requested-Action', 0))
 CCR_avps.append(encodeAVP('Used-Service-Unit', [
    encodeAVP('CC-Time', CCTime)
 ]))
 CCR_avps.append(encodeAVP('Service-Information', [ 
    encodeAVP('IN-Information', [
      encodeAVP('Calling-Party-Number', Anumber),
      encodeAVP('Called-Party-Number', Bnumber),
      encodeAVP('Service-Key', 1),
      encodeAVP('Calling-Vlr-Number', '086149051'),
      encodeAVP('Calling-CellID-Or-SAI', '0'),
      encodeAVP('Calling-LAI', '086149051'),
      encodeAVP('Called-Vlr-Number', '0861390510'),
      encodeAVP('Called-CellID-Or-SAI', '0861390510'),
      encodeAVP('Called-LAI', '0861390510'),
      encodeAVP('Bearer-Capability', '0'),
      encodeAVP('EventType-BCSM', 2),
      encodeAVP('IMSI', '1234567890123456'),
      encodeAVP('Call-Reference-Number', '123456'),
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
if TIMEINP.lower() == 'default':
 Timestamp=int(time.time())
else:
 Timestamp=int(TIMEINP)
 print 'Input Timestamp',Timestamp
if A_NO.lower() == 'default':
 A_NO = '6288295291295'
if B_NO.lower() == 'default':
 B_NO = '6288295291296'
if DURATION.lower() == 'default':
 DURATION = 10
print '-----------------------------------------------------------------------------------------------------------------------------------------------------------------'
print 'Execution at ',datetime.now()
print 'Sending VOICE Usage--> Session-Name:',session_id,' ## MDN A:',A_NO,' ## MDN B:',B_NO,' ## TIME:',datetime.fromtimestamp(Timestamp),' ## DURATION:',DURATION
#Voice Initial session_id,CCRequestNumber,Timestamp,Anumber,Bnumber,RatingGroup,CCTime,IMSI,ANI
result_code=send_voice_init(session_id,0,Timestamp,A_NO,B_NO)
if result_code == 2001:
 #print 'Full Voice Initial Event','success ###',session_id, '### Result Code', result_code
 #Voice Terminate --> session_id,CCRequestNumber,Timestamp,Anumber,Bnumber,RatingGroup,CCTime,IMSI,ANI
 result_code1=send_voice_terminate(session_id,1,Timestamp,A_NO,B_NO,DURATION)
 if result_code1 == 2001:
   print 'Full Voice Until Terminate Event','success ###',session_id, '### Result Code', result_code1
 else :
   print 'Full Voice Terminate Event ','failed, Please check from by manual run###',session_id, '### Result Code', result_code1
else :
 print 'Full Voice Initial Event','failed, Intermediate & Terminate event will be skip###',session_id, '### Result Code', result_code
print '-----------------------------------------------------------------------------------------------------------------------------------------------------------------'