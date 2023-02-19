import sys
import os
import datetime
from binascii import *
from datetime import *
sys.path.append("..")

from libDiameter import *
#from ocs_webservice import *
 
#Define Param for GPRS here
#SDP Param --> session_id,Timestamp,Anumber,Bnumber
if len(sys.argv)-1 < 4:
  print 'Usage: SF-SDP-Simulator.py <Session-Name> <MDN-A> <SHORT-CODE> <APP-ID> <PRICE> <TIME>'
  print 'Input param "default" will set value Session-Name:default MDN-A:6288220204893 SHORT-CODE:3029 APP-ID:000000012465 PRICE:55000 TIME:default-now'
  sys.exit(2)
SESSION=sys.argv[1]
A_NO=sys.argv[2]
B_NO=sys.argv[3]
APPID=sys.argv[4]
PRICE=sys.argv[5]
TIMEINP=sys.argv[6]

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
 ret=SESSION+";SDP;"+str(int(time.time()))
 #ret="galih;SDP;"+str(int(time.time()))
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

#SDP usage event
def send_SDP(session_id,Timestamp,Anumber,Bnumber,APPID,PRICE):
 CCR_avps=[]
 CCR_avps.append(encodeAVP('Session-ID', session_id)) 
 CCR_avps.append(encodeAVP('Origin-Host', 'SCP001.francetelecom.com'))
 CCR_avps.append(encodeAVP('Origin-Realm', 'chinatelecom.com'))
 CCR_avps.append(encodeAVP('Destination-Realm', 'chinatelecom.com'))
 CCR_avps.append(encodeAVP('Auth-Application-Id', 4))
 CCR_avps.append(encodeAVP('Service-Context-ID', 'version1.ismp@zte.com.cn'))
 CCR_avps.append(encodeAVP('CC-Request-Type', 4))
 CCR_avps.append(encodeAVP('CC-Request-Number', 0))
 CCR_avps.append(encodeAVP('Event-Timestamp', Timestamp))
 CCR_avps.append(encodeAVP('Subscription-ID', [
  encodeAVP('Subscription-ID-Data', Anumber),
  encodeAVP('Subscription-ID-Type', 0)
 ]))
 CCR_avps.append(encodeAVP('Requested-Action', 0))
 CCR_avps.append(encodeAVP('Multiple-Service-Indicator', 1))
 CCR_avps.append(encodeAVP('Service-Information', [ 
    encodeAVP('ISMP-Information', [
      encodeAVP('Message-Id','30 36 31 30 31 36 35 39 31 32 30 30 30 30 30 30 30 30 31 30'),
      encodeAVP('Charge-Party-Type',3),
      encodeAVP('SP-Id',APPID[-8:]),
      encodeAVP('Sevice-Enabler-Type',0),
      encodeAVP('Charging-Type',1),
      encodeAVP('Product-Id',"0"),
      encodeAVP('Product-Offer-ID',APPID),
      encodeAVP('Service-Types',2),
      encodeAVP('Content-Id','2'),
      encodeAVP('Media-Type','30 30 30 30 30'),
      encodeAVP('OA-Subscription-Id', [
        encodeAVP('Subscription-ID-Data',Anumber),
        encodeAVP('Subscription-ID-Type',0)
      ]),
      encodeAVP('DA-Subscription-Id', [
        encodeAVP('Subscription-ID-Data',Anumber),
        encodeAVP('Subscription-ID-Type',0)
      ]),
      encodeAVP('Proxy-Service-Enabler',1),
      encodeAVP('Short-code',Bnumber)
    ])
 ]))
 CCR_avps.append(encodeAVP('Requested-Service-Unit', [ 
    encodeAVP('CC-Time', 0),
    encodeAVP('uA-Currency-Code', 0),
    encodeAVP('CC-Total-Octets', 0),    
    encodeAVP('CC-Money', [
        encodeAVP('Unit-Value',[
            encodeAVP('Value-Digits', int(PRICE)),
            encodeAVP('Exponents', -2)
        ])])
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
 A_NO = '6288220204893'
if B_NO.lower() == 'default':
 B_NO = '3029'
if APPID.lower() == 'default':
 APPID = '000000012465'
if PRICE.lower() == 'default':
 PRICE = '55000'
print '-----------------------------------------------------------------------------------------------------------------------------------------------------------------'
print 'Execution at ',datetime.now()
print 'Sending SDP Usage--> Session-Name:',session_id,' ## MDN A:',A_NO,' ## SHORT-CODE:',B_NO,' ## APP-ID:',APPID,' ## PRICE:',PRICE,' ## TIME:',datetime.fromtimestamp(Timestamp)
#SDP session_id,Timestamp,Anumber,Bnumber,APPID,PRICE
result_code=send_SDP(session_id,Timestamp,A_NO,B_NO,APPID,PRICE)
if result_code == 2001:
 print 'SDP Event','success ###',session_id, '### Result Code', result_code 
else :
 print 'SDP Event','failed, Please check from by manual run###',session_id, '### Result Code', result_code
print '-----------------------------------------------------------------------------------------------------------------------------------------------------------------'