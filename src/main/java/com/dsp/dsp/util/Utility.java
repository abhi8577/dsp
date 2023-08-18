package com.dsp.dsp.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import com.dsp.dsp.dto.FileUploadPathDto;
import com.dsp.dsp.response.Response;

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

	
      public static Response uploadFile(MultipartFile file,String fileName) throws Exception {
		
		System.out.println("inside uploadFile method");
		
		try {
			System.out.println(file.getContentType());
			if(file.isEmpty()) {
	            return Response.response("Upload file was empty", HttpStatus.BAD_REQUEST, null, null);
	
			}
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            double rand=  Math.random();
            String random=String.valueOf(rand);
            random=random.substring(random.indexOf(".")+1);
            
           //For Local
           Path path = Paths.get("C:\\dspUploadFile\\"+fileName+"_"+random+"_"+file.getOriginalFilename());

            //For Rooftop
           // Path path = Paths.get("D:\\DSP-pdf-upload\\"+fileName+"_"+random+"_"+file.getOriginalFilename());

            Path filePath = Files.write(path, bytes);
            FileUploadPathDto filePathDto = new FileUploadPathDto();
            filePathDto.setFilePath(filePath.toString());
            return Response.response("File upload successfully", HttpStatus.OK, filePathDto, null);
            
        } catch (Exception e) {
        	throw e;
        }
		
    }
      
     public static String getRandomNumber() {
    	  String localDateTime = LocalDateTime.now().toString();//2023-07-11T10:31:28.279
  		String localDateTimeReplace = localDateTime.replaceAll("\\-|\\:|[a-zA-z]|\\.", "");
      
  		return localDateTimeReplace;
      }
     
     public static String getUserIPAddress(HttpServletRequest request) {
 		String ipAddress = request.getHeader("X-Forwarded-For");

 		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
 			ipAddress = request.getHeader("Proxy-Client-IP");
 		}
 		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
 			ipAddress = request.getHeader("WL-Proxy-Client-IP");
 		}
 		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
 			ipAddress = request.getRemoteAddr();
 		}
 		System.err.println("IP" + ipAddress);
 		return ipAddress;
 	}
}
