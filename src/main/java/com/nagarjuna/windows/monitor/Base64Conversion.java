package com.nagarjuna.windows.monitor;

import org.apache.commons.codec.binary.Base64;

public class Base64Conversion {
	
	public static void main(String[] args) {
		
		String powershellScript = "Get-WmiObject -ComputerName OSI-L-0329 -Class win32_processor | Measure-Object -property LoadPercentage -Average | Select Average";
		// encode data on your side using BASE64
		byte[]   bytesEncoded = Base64.encodeBase64(powershellScript.getBytes());
		System.out.println("ecncoded value is " + new String(bytesEncoded));

		// Decode data on other side, by processing encoded data
		byte[] valueDecoded= Base64.decodeBase64(bytesEncoded);
		System.out.println("Decoded value is " + new String(valueDecoded));
	}

}
