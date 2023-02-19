import sys
import os
import datetime
from binascii import *
from datetime import *
sys.path.append("..")

from libDiameter import *
#from ocs_webservice import *

#Define Param for GPRS here
#GPRS Update --> Timestamp,Anumber,RAT_Type,RatingGroup,User_Equip,ReqUnit,UseUnit,CCTime
if len(sys.argv)-1 < 7:
  print 'Usage: SF-GPRS-Simulator.py <Session-Name> <MDN> <RG> <TIME> <User-Equip> <Cell_ID> <UsageInput> <SGSN>'
  print 'Input param "default" will set value Session-Name:default MDN:6288295291295 TIME:default-now RG:4 User-Equip:867345 0103865801 Cell_ID:1527-38480663 UsageInput:341333 SGSN:202.146.94.126'
  sys.exit(2)
if len(sys.argv)-1 > 7:
  SGSN = sys.argv[8]
else :
  SGSN = '0'
if SGSN.lower() == 'default':
 SGSN = '0'
SESSION=sys.argv[1]
MDNINP=sys.argv[2]
RGINP=sys.argv[3]
TIMEINP=sys.argv[4]
UEQUIPINP=sys.argv[5]
CGIINP=sys.argv[6]
USUINP=sys.argv[7]

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
 ret=SESSION+"DATA;"+str(int(time.time()))
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
 #print 'CER Success from ZTE port ',PORT_ZTE,' is ',result_code

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

#GPRS Initial usage event
def send_gprs_init(session_id,CCRequestNumber,Timestamp,Anumber,RAT_Type,RatingGroup,User_Equip,Cell_ID,SGSN):
 CCR_avps=[]
 CCR_avps.append(encodeAVP('Session-ID', session_id))
 CCR_avps.append(encodeAVP('Origin-Host', 'scp_zeng.zte.com.cn'))
 CCR_avps.append(encodeAVP('Origin-Realm', 'chinatelecom1.com'))
 CCR_avps.append(encodeAVP('Destination-Realm', 'chinatelecom.com'))
 CCR_avps.append(encodeAVP('Auth-Application-Id', 1))
 CCR_avps.append(encodeAVP('Service-Context-ID', 'version1.ccg@chinatelecom.com'))
 CCR_avps.append(encodeAVP('CC-Request-Type', 1))
 CCR_avps.append(encodeAVP('CC-Request-Number', CCRequestNumber))
 CCR_avps.append(encodeAVP('Event-Timestamp', Timestamp))
 CCR_avps.append(encodeAVP('Subscription-ID', [
  encodeAVP('Subscription-ID-Data', Anumber),
  encodeAVP('Subscription-ID-Type', 0)
 ]))
 if SGSN == '0' :
	 CCR_avps.append(encodeAVP('Service-Information', [
	  encodeAVP('PS-Information', [
	  encodeAVP('3GPP-GPRS-Negotiated-QoS-Profile', 'ver.1'),
	  encodeAVP('3GPP-User-Location-Info', Cell_ID),
	  encodeAVP('3GPP-RAT-Type', RAT_Type)	   ])
	  ]))
 else :
	 CCR_avps.append(encodeAVP('Service-Information', [
	  encodeAVP('PS-Information', [
	  encodeAVP('3GPP-GPRS-Negotiated-QoS-Profile', 'ver.1'),
	  encodeAVP('3GPP-User-Location-Info', Cell_ID),
	  encodeAVP('3GPP-RAT-Type', RAT_Type),
	  encodeAVP('SGSN-Address', SGSN)
	   ])
	  ]))
 CCR_avps.append(encodeAVP('Multiple-Services-Credit-Control', [
  encodeAVP('Rating-Group', RatingGroup)
 ]))
 CCR_avps.append(encodeAVP('User-Equipment-Info', [
                encodeAVP('User-Equipment-Info-Type', 0),
                encodeAVP('User-Equipment-Info-Value', User_Equip)
        ]))
 port=2
 avps=send_CCR(CCR_avps, port)
 result_code = findAVP("Result-Code", avps)
 if result_code == 2001 :
                result_code = findAVP("Result-Code", avps)
 return result_code

#GPRS Update usage event
def send_gprs_update(session_id,CCRequestNumber,Timestamp,Anumber,RAT_Type,RatingGroup,User_Equip,Cell_ID,UseUnit,report_reason,SGSN):
 CCR_avps = []
 CCR_avps.append(encodeAVP('Session-ID', session_id))
 CCR_avps.append(encodeAVP('Origin-Host', 'scp_zeng.zte.com.cn'))
 CCR_avps.append(encodeAVP('Origin-Realm', 'chinatelecom1.com'))
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
 if SGSN == '0' :
	 CCR_avps.append(encodeAVP('Service-Information', [
	  encodeAVP('PS-Information', [
	  encodeAVP('3GPP-User-Location-Info', Cell_ID),
	  encodeAVP('3GPP-RAT-Type', RAT_Type)	   ])
	  ]))
 else :
	 CCR_avps.append(encodeAVP('Service-Information', [
	  encodeAVP('PS-Information', [
	  encodeAVP('3GPP-User-Location-Info', Cell_ID),
	  encodeAVP('3GPP-RAT-Type', RAT_Type),
	  encodeAVP('SGSN-Address', SGSN)
	   ])
	  ]))
 CCR_avps.append(encodeAVP('Multiple-Service-Indicator', 1))
 CCR_avps.append(encodeAVP('Multiple-Services-Credit-Control', [
  encodeAVP('Rating-Group', RatingGroup),
  encodeAVP('Requested-Service-Unit', [
   encodeAVP('CC-Total-Octets', 0)]),
  encodeAVP('Used-Service-Unit', [
   encodeAVP('CC-Total-Octets', UseUnit),
   encodeAVP('Reporting-Reason', report_reason)
    ])
 ]))
 CCR_avps.append(encodeAVP('User-Equipment-Info', [
                encodeAVP('User-Equipment-Info-Type', 0),
                encodeAVP('User-Equipment-Info-Value', User_Equip)
        ]))
 port=2
 avps=send_CCR(CCR_avps, port)
 result_code = findAVP("Result-Code", avps)
 if result_code == 2001 :
                result_code = findAVP("Result-Code", findAVP("Multiple-Services-Credit-Control", avps))
 return result_code

#GPRS terminate usage event
def send_gprs_terminate(session_id,CCRequestNumber,Timestamp,Anumber,RAT_Type,RatingGroup,User_Equip,Cell_ID,SGSN):
 CCR_avps = []
 CCR_avps.append(encodeAVP('Session-ID', session_id))
 CCR_avps.append(encodeAVP('Origin-Host', 'scp_zeng.zte.com.cn'))
 CCR_avps.append(encodeAVP('Origin-Realm', 'chinatelecom1.com'))
 CCR_avps.append(encodeAVP('Destination-Realm', 'chinatelecom.com'))
 CCR_avps.append(encodeAVP('Auth-Application-Id', 1))
 CCR_avps.append(encodeAVP('Service-Context-ID', 'version1.ccg@chinatelecom.com'))
 CCR_avps.append(encodeAVP('CC-Request-Type', 3))
 CCR_avps.append(encodeAVP('CC-Request-Number', CCRequestNumber))
 CCR_avps.append(encodeAVP('Event-Timestamp', Timestamp))
 CCR_avps.append(encodeAVP('Subscription-ID', [
  encodeAVP('Subscription-ID-Data', Anumber),
  encodeAVP('Subscription-ID-Type', 0)
 ]))
 if SGSN == '0' :
	 CCR_avps.append(encodeAVP('Service-Information', [
	  encodeAVP('PS-Information', [
	  encodeAVP('3GPP-User-Location-Info', Cell_ID),
	  encodeAVP('3GPP-RAT-Type', RAT_Type)	   ])
	  ]))
 else :
	 CCR_avps.append(encodeAVP('Service-Information', [
	  encodeAVP('PS-Information', [
	  encodeAVP('3GPP-User-Location-Info', Cell_ID),
	  encodeAVP('3GPP-RAT-Type', RAT_Type),
	  encodeAVP('SGSN-Address', SGSN)
	   ])
	  ]))
 CCR_avps.append(encodeAVP('User-Equipment-Info', [
                encodeAVP('User-Equipment-Info-Type', 0),
                encodeAVP('User-Equipment-Info-Value', User_Equip)
        ]))
 port=2
 avps=send_CCR(CCR_avps, port)
 result_code = findAVP("Result-Code", avps)
 return result_code

def timestamp(data):
        Timestamp=time.mktime(datetime.strptime(data, "%Y-%m-%d-%H-%M-%S").timetuple())
        return Timestamp

def dec2Hex(cell_id):
	cell_split=cell_id.split('-')
	c1=int(cell_split[0])
	c2=int(cell_split[1])
	cc1=str(hex(c1).split('x')[1]).zfill(4)
	cc2=str(hex(c2).split('x')[1]).zfill(8)
	hexaCell='8215f090'+cc1+'15f090'+cc2
	
	return hexaCell


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
if MDNINP.lower() == 'default':
 MDNINP = '6288295291295'
if RGINP.lower() == 'default':
 RGINP = 4
if UEQUIPINP.lower() == 'default':
 UEQUIPINP = '867345 0103865801'
if CGIINP.lower() == 'default':
 CELL_ID = '0'
else:
 CELL_ID = dec2Hex(CGIINP)
if USUINP.lower() == 'default':
 USU = int(341333)
else:
 USU = int(USUINP)
if SGSN.lower() == 'default':
 SGSN = '0'
print '-----------------------------------------------------------------------------------------------------------------------------------------------------------------'
print 'Execution at ',datetime.now()
print 'Sending DATA Usage --> Session-Name:',session_id,' # MDN:',MDNINP,' # TIME:',datetime.fromtimestamp(Timestamp),' # RG:',RGINP,' # User-Equip:',UEQUIPINP,' # Location:',CGIINP,' # SGSN Address:',SGSN,' # UsageInput:',USU
#GPRS Initial session_id,CCRequestNumber,Timestamp,Anumber,RAT_Type,RatingGroup,User_Equip
#result_code0=send_gprs_before(session_id,1,Timestamp,MDNINP,'6',RGINP,UEQUIPINP,CELL_ID,0,0,0)
result_code=send_gprs_init(session_id,0,Timestamp,MDNINP,'6',RGINP,UEQUIPINP,CELL_ID,SGSN)
if result_code == 2001:
 #print 'Full GPRS Initial Event','success ###',session_id, '### Result Code', result_code
 #GPRS Update --> session_id,CCRequestNumber,Timestamp,Anumber,RAT_Type,RatingGroup,User_Equip,ReqUnit,UseUnit,CCTime
 result_code1=send_gprs_update(session_id,1,Timestamp,MDNINP,'6',RGINP,UEQUIPINP,CELL_ID,USU,3,SGSN)
 if result_code1 == 2001:
   #print 'Full GPRS Update Event 1','success ###',session_id, '### Result Code', result_code1
   result_code2=send_gprs_update(session_id,2,Timestamp,MDNINP,'6',RGINP,UEQUIPINP,CELL_ID,USU,2,SGSN)
   if result_code2 == 2001:
    #print 'Full GPRS Update Event 3','success ###',session_id, '### Result Code', result_code3
    #GPRS Terminate --> session_id,CCRequestNumber,Timestamp,Anumber,RAT_Type,RatingGroup,User_Equip,UseUnit,CCTime
    #print 'Full GPRS Update Event 2','success ###',session_id, '### Result Code', result_code2
    result_code3=send_gprs_terminate(session_id,3,Timestamp,MDNINP,'6',RGINP,UEQUIPINP,CELL_ID,SGSN)
    if result_code3 == 2001:
      print 'Full GPRS Until Terminate Event ','success ###',session_id, '### Result Code', result_code3
    else :
      print 'Full GPRS Terminate Event ','failed with Initial GPRS Session ID ###',session_id, '### Result Code', result_code3
   else :
    print 'Full GPRS Update Final','failed, Next event (update or terminate) will be skip###',session_id, '### Result Code', result_code2
 else :
   print 'Full GPRS Update ','failed, Next event (update or terminate) will be skip###',session_id, '### Result Code', result_code1
else :
 print 'Full GPRS Initial Event','failed, Intermediate & Terminate event will be skip###',session_id, '### Result Code', result_code
print '-----------------------------------------------------------------------------------------------------------------------------------------------------------------'
