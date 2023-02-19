package com.smartfren.mfino;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MFino {
	public String IP = "";
	public String Port = "";

	public void setIP(String IP) {
		this.IP = IP;
	}

	public void setPort(String Port) {
		this.Port = Port;
	}


	public String isoReq(String paramString1, String paramString2, String paramString3) {
		String result = "";
		try {
			String str1 = paramString1;
			String str2 = paramString2;
			String str3 = paramString3;
			byte[] arrayOfByte1 = new byte[4];
			System.out.println("Connecting to server ...");
			Socket localSocket = new Socket(str1, Integer.parseInt(str2));
			int i = str3.length();
			String str4 = Integer.toString(i);
			while (str4.length() < 4)
				str4 = "0" + str4;
			str3 = String.valueOf(str4) + str3;
			System.out.println("Send Message: " + str3);
			localSocket.getOutputStream().write(str3.getBytes());
			localSocket.getInputStream().read(arrayOfByte1);
			int j = Integer.parseInt(new String(arrayOfByte1));
			byte[] arrayOfByte2 = new byte[j];
			localSocket.getInputStream().read(arrayOfByte2);
			System.out.println("Result: " + new String(arrayOfByte2));
			result = new String(arrayOfByte2);
			localSocket.close();
		} catch (NumberFormatException localNumberFormatException) {
			localNumberFormatException.printStackTrace();
		} catch (UnknownHostException localUnknownHostException) {
			localUnknownHostException.printStackTrace();
		} catch (IOException localIOException) {
			localIOException.printStackTrace();
		}
		return result;
	}

	public String RRN() {
		String RRN = "";
		try {
			byte[] mac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
			StringBuilder sb = new StringBuilder();
			if (mac != null)
				for (int i = 0; i < mac.length; i++) {
					sb.append(String.format("%02X%s",
							new Object[] { Byte.valueOf(mac[i]), (i < mac.length - 1) ? "" : "" }));
				}
			if (sb.toString() == null || sb.toString().length() == 0) {
				System.out.println(RRN);
				RRN = "NOFINDMACADD";
			} else {
				RRN = sb.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RRN;
	}

	public String[] AccountInquiry(String MDN, String MerchantType, String Bank_Code, String productIndicator) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
		String date = dateFormat.format(new Date());
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HHmmssMMddMMdd");
		String localDate = localDateFormat.format(new Date());
		MFino mfino = new MFino();
		String RRN = mfino.RRN();

		String MobileNumber = (MDN + "              ").substring(0, 16);
		String ProcessingCode = "380000";
		String BankCode = "03881";
		if (Bank_Code.length()<=3) {
			String bank_code_buffer = "000" + Bank_Code;
			BankCode = "03" + bank_code_buffer.substring(bank_code_buffer.length() - 3);
		}else if(Bank_Code.length()<10) {
			BankCode = "0" + Bank_Code.length() + Bank_Code;
		}

		String Msg = "0200223A400108218008" + ProcessingCode + date + "888888" + localDate + MerchantType
				+ BankCode + RRN + "SMS SMART                               "+productIndicator+"360016" + MobileNumber;

		String response = mfino.isoReq(this.IP, this.Port, Msg);
		System.out.println("Done");
		String[] result = { Msg, response};
		return result;
	}

	public String[] Recharge(String MDN, String MerchantType, String Bank_Code, String Amount_Balance, String productIndicator) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
		String date = dateFormat.format(new Date());
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HHmmssMMddMMdd");
		String localDate = localDateFormat.format(new Date());
		MFino mfino = new MFino();
		String RRN = mfino.RRN();

		String MobileNumber = (MDN + "              ").substring(0, 16);
		String ProcessingCode = "180000";
		String BankCode = "03881";
		if (Bank_Code.length()<=3) {
			String bank_code_buffer = "000" + Bank_Code;
			BankCode = "03" + bank_code_buffer.substring(bank_code_buffer.length() - 3);
		}else if(Bank_Code.length()<10) {
			BankCode = "0" + Bank_Code.length() + Bank_Code;
		}
		
		
		String amount_buffer = "000000000000" + Amount_Balance;
		String Amount = amount_buffer.substring(amount_buffer.length() - 12);

		String Msg = "0200323A400108A18008" + ProcessingCode + Amount + date + "888888" + localDate + MerchantType
				+ BankCode + RRN + "11      SMS SMART                               "+productIndicator+"360016" + MobileNumber;
		String response = mfino.isoReq(this.IP, this.Port, Msg);
		System.out.println("Done");
		String[] result = { Msg, response};
		return result;
	}

	public String[] Payment(String MDN, String MerchantType, String Bank_Code, String Amount_Balance, String productIndicator) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
		String date = dateFormat.format(new Date());
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HHmmssMMddMMdd");
		String localDate = localDateFormat.format(new Date());
		MFino mfino = new MFino();
		String RRN = mfino.RRN();

		String MobileNumber = (MDN + "              ").substring(0, 16);
		String ProcessingCode = "280000";
		String BankCode = "03881";
		if (Bank_Code.length()<=3) {
			String bank_code_buffer = "000" + Bank_Code;
			BankCode = "03" + bank_code_buffer.substring(bank_code_buffer.length() - 3);
		}else if(Bank_Code.length()<10) {
			BankCode = "0" + Bank_Code.length() + Bank_Code;
		}
		String amount_buffer = "000000000000" + Amount_Balance;
		String Amount = amount_buffer.substring(amount_buffer.length() - 12);

		String Msg = "0200323A400108A18008" + ProcessingCode + Amount + date + "888888" + localDate + MerchantType
				+ BankCode + RRN + "11      SMS SMART                               "+productIndicator+"360016" + MobileNumber;
		String response = mfino.isoReq(this.IP, this.Port, Msg);
		System.out.println("Done");
		String[] result = { Msg, response};
		return result;
	}

	public String[] BalTransfer(String MDN_A, String MerchantType, String Bank_Code, String Amount_Balance, String productIndicator, String MDN_B) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
		String date = dateFormat.format(new Date());
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HHmmssMMddMMdd");
		String localDate = localDateFormat.format(new Date());
		MFino mfino = new MFino();
		String RRN = mfino.RRN();

		String MobileNumberA = (MDN_A + "              ").substring(0, 16);
		String ProcessingCode = "400000";
		String BankCode = "03881";
		if (Bank_Code.length()<=3) {
			String bank_code_buffer = "000" + Bank_Code;
			BankCode = "03" + bank_code_buffer.substring(bank_code_buffer.length() - 3);
		}else if(Bank_Code.length()<10) {
			BankCode = "0" + Bank_Code.length() + Bank_Code;
		}
		String amount_buffer = "000000000000" + Amount_Balance;
		String Amount = amount_buffer.substring(amount_buffer.length() - 12);
		String MobileNumberB = (MDN_B + "              ").substring(0, 16);

		String Msg = "02003238400108010008" + ProcessingCode + Amount + date + "888888" + localDate.substring(0, 10)
				+ MerchantType + BankCode + RRN + productIndicator + "032" + MobileNumberA + MobileNumberB;
		String response = mfino.isoReq(this.IP, this.Port, Msg);
		System.out.println("Done");
		String[] result = { Msg, response};
		return result;
	}

	public String[] PackagePurchase(String MDN, String MerchantType, String Bank_Code, String Amount_Balance, String productIndicator, String PricePlan, String Area) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
		String date = dateFormat.format(new Date());
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HHmmssMMddMMdd");
		String localDate = localDateFormat.format(new Date());
		MFino mfino = new MFino();
		String RRN = mfino.RRN();

		String MobileNumber = (MDN + "              ").substring(0, 16);
		String ProcessingCode = "490000";
		String BankCode = "03881";
		if (Bank_Code.length()<=3) {
			String bank_code_buffer = "000" + Bank_Code;
			BankCode = "03" + bank_code_buffer.substring(bank_code_buffer.length() - 3);
		}else if(Bank_Code.length()<10) {
			BankCode = "0" + Bank_Code.length() + Bank_Code;
		}
		String amount_buffer = "000000000000" + Amount_Balance;
		String Amount = amount_buffer.substring(amount_buffer.length() - 12);
		String pp_buffer = "000000000000" + PricePlan;
		String PricePlanId = pp_buffer.substring(pp_buffer.length() - 12);
		String AreaId = (String.valueOf(Area) + "         ").substring(0, 9);

		String Msg = "0200323A400108A18008" + ProcessingCode + Amount + date + "888888" + localDate + MerchantType
				+ BankCode + RRN + "11      SMS SMART                               "+productIndicator+"360037" + MobileNumber
				+ PricePlanId + AreaId;
		
		//"0200323A400108A18008", processingCode, amount, getTDT(), getSTAN(), getTT(), 
        //getTD(), getSD(), merchantCode, bankCode, getRRN(), getCATI(), getCAN(), productIndicator, 
        //getCurrenyCode(), "037" + mobileNumber + pricePlanId + areaId
        
		String response = mfino.isoReq(this.IP, this.Port, Msg);
		System.out.println("Done");
		String[] result = { Msg, response};
		return result;
	}
}