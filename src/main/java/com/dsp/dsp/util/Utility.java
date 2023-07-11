package com.dsp.dsp.util;

import java.util.Base64;
import java.util.Base64.Encoder;

public class Utility {
	
	public static  String getEncodeData(String decryptData) throws Exception {
		Encoder encoder = Base64.getEncoder();
		String encode = encoder.encodeToString(decryptData.getBytes());
		return encode;
	}
	
	public static  String getDecryptData(String encryptData) throws Exception {
		byte[] decode = Base64.getDecoder().decode(encryptData);
		String decryptCode = new String(decode);
		return decryptCode;
	}

}
