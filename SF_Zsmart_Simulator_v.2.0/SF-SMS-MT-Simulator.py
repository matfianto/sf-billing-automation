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
  print 'Usage: SF-SMS-MT-Simulator.py <Session-Name> <MDN-A> <MDN-B> <ADDR_DATA> <TIME>'
  print 'Input param "default" will set value Session-Name:default MDN-A:8975 MDN-B:628881803809 ADDR_DATA:11628881803809 TIME:default-now'
  sys.exit(2)
SESSION=sys.argv[1]
A_NO=sys.argv[2]
B_NO=sys.argv[3]
ADDR_DATA=sys.argv[4]
TIMEINP=sys.argv[5]

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
 ret=SESSION+";SMSMT;"+str(int(time.time()))
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
def send_sms(session_id,Timestamp,Anumber,Bnumber,ADDR_DATA):
 CCR_avps=[]
 CCR_avps.append(encodeAVP('Session-ID', session_id)) 
 CCR_avps.append(encodeAVP('Origin-Host', 'ipsm.ims.mnc009.mcc510.3gppnetwork.org'))
 CCR_avps.append(encodeAVP('Origin-Realm', 'ims.mnc009.mcc510.3gppnetwork.org'))
 CCR_avps.append(encodeAVP('Destination-Realm', 'zte.com.cn'))
 CCR_avps.append(encodeAVP('Auth-Application-Id', 4))
 CCR_avps.append(encodeAVP('Service-Context-ID', 'ipsmgw.ims@smartfren.com'))
 CCR_avps.append(encodeAVP('CC-Request-Type', 4))
 CCR_avps.append(encodeAVP('CC-Request-Number', 1))
 CCR_avps.append(encodeAVP('User-Name', '123456@partner'))
 CCR_avps.append(encodeAVP('Event-Timestamp', Timestamp))
 CCR_avps.append(encodeAVP('Subscription-ID', [
  encodeAVP('Subscription-ID-Data', Bnumber),
  encodeAVP('Subscription-ID-Type', 0)
 ]))
 CCR_avps.append(encodeAVP('Route-Record', 'ipsm.ims.mnc009.mcc510.3gppnetwork.org'))
 CCR_avps.append(encodeAVP('Requested-Action', 0))
 CCR_avps.append(encodeAVP('Multiple-Services-Credit-Control', [
  encodeAVP('Service-Identifier', 202),
  encodeAVP('Requested-Service-Unit', [
    encodeAVP('CC-Service-Specific-Units', 1)])
 ]))
 CCR_avps.append(encodeAVP('Service-Information', [ 
    encodeAVP('SMS-Information', [
      encodeAVP('Recipient', [
        encodeAVP('Recipient-Address',[
            encodeAVP('Address-Data',ADDR_DATA)
        ])
      ])
    ]),
    encodeAVP('P2PSMS-Information', [
      encodeAVP('SMSC-Address','1235689')
    ]),
    encodeAVP('IMS-Information',[
      encodeAVP('Node-Functionality', 6),
      encodeAVP('Role-of-Node', 1),
      encodeAVP('User-Session-ID', '1098667230@192.168.10.84'),
      encodeAVP('Calling-Party-Address', 'sip:'+Anumber+'@ims.mnc009.mcc510.3gppnetwork.org'),
      encodeAVP('Called-Party-Address', 'sip:+'+Bnumber+'@ims.mnc009.mcc510.3gppnetwork.org'),
      encodeAVP('IMS-Charging-Identifier', '0.274.208-1431513465.18276')
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
TIMEINP=sys.argv[5]
if TIMEINP.lower() == 'default':
 Timestamp=int(time.time())
else:
 Timestamp=int(TIMEINP)
 print 'Input Timestamp',Timestamp
if A_NO.lower() == 'default':
 A_NO = '6288295292653'
if B_NO.lower() == 'default':
 B_NO = '6288295292656'
print '-----------------------------------------------------------------------------------------------------------------------------------------------------------------'
print 'Execution at ',datetime.now()
print 'Sending SMS MT Usage--> Session-Name:',session_id,' ## Calling MDN:',A_NO,' ## Called MDN:',B_NO,' ## ADDR_DATA:',ADDR_DATA,' ## TIME:',datetime.fromtimestamp(Timestamp)
#SMS session_id,Timestamp,Anumber,Bnumber
result_code=send_sms(session_id,Timestamp,A_NO,B_NO,ADDR_DATA)
if result_code == 2001:
 print 'SMS MT Event','success ###',session_id, '### Result Code', result_code 
else :
 print 'SMS MT Event','failed, Please check from by manual run###',session_id, '### Result Code', result_code
print '-----------------------------------------------------------------------------------------------------------------------------------------------------------------'