package com.caller;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Operation {

	
	 public final String regex_pass="((?=.*\\d)(?=.*[a-zA-Z])(?=.*[~'!@#$%?\\\\/&*\\]|\\[=()}\"{+_:;,.><'-])).{8,}"; // regex password pattern
	 public final String  owasp_email= "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"; //owasp validation for regex
	
	
		/*Check null/blank Method*/
	
	public boolean checkInput(String input) {
		
		
		return input==null || input.isBlank();
		
		
	}
	
	/* Hashing Password Method*/

	public  String hashValue(String password,String salt) throws NoSuchAlgorithmException {
		
		MessageDigest msg=MessageDigest.getInstance("SHA-512");
		msg.update(salt.getBytes());
		
		byte[]hash=msg.digest(password.getBytes(StandardCharsets.UTF_8));
		
		return bytesTostringhex(hash);
		
	}
	
    /* generate a random unique character for salt to hash the password */
	
	public byte[] salt() throws NoSuchAlgorithmException {
		
		SecureRandom rdm=SecureRandom.getInstance("SHA1PRNG");
		
		byte[]value=rdm.generateSeed(16);
		
		return  value;
	}
	
	//method that convert byte to string
	
		private final static char[] hexarray = "0123456789ABCDEF".toCharArray();
		public static  String bytesTostringhex(byte[] bytes) {


		    char[] hexChars= new char[bytes.length * 2];
		    for (int j= 0; j< bytes.length; j++) {
		        int v= bytes[j] & 0xFF;
		        hexChars[j* 2] = hexarray[v >>> 4];
		        hexChars[j* 2 + 1] = hexarray[v & 0x0F];
		   
		    }
		    return new String(hexChars);

		}
		
	public boolean deleteFile(String path) {
		
			File file=new File(path);
			
			if(file.exists()) {
				
				file.delete();
			}
		
		
		
		return false;
	}
	
	
    /* Patter for Regex Expression*/
	
	
	public boolean pattern(String regex, String input) {
		
		
		
		Pattern pattern=Pattern.compile(regex);
		Matcher match=pattern.matcher(input);
		
		
		while (match.find()) {
			
			return true;
		}
		return false;
		
		
	}
	
	public String trimData(String data) {
		
		if (data.length() < 3) {
		    return data;
		} else {
		    return data.substring(0, 400);
		}
		
		
		
	}
		
}
