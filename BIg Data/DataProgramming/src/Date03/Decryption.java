package Date03;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Decryption {
	
	public static String key = "12345678901234567890123456789012";
	public static String encryptedText = "3zwYXbHKDnlCYRVL/MQEaYbS+6BqcTU5lbgwM3Kd35zp+X3+yBvQqD39iqwXIHWxQcOzzUBZNw5PzsHwkRk39g==";

	public static void main(String[] args) {
		System.out.println(decrypt(key,encryptedText));
	}
	
	public static String decrypt (String key, String encryptedText) {
		String plainText= "";
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec ivspec = new IvParameterSpec(Arrays.copyOfRange(key.getBytes("UTF-8"),0,cipher.getBlockSize()));
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("UTF-8"),"AES"),ivspec);
			plainText = new String(cipher.doFinal(Base64.decodeBase64(encryptedText.getBytes())),"UTF-8");			
		}catch(Exception e) {
			plainText = "";
			e.printStackTrace();
		}
		return plainText;
	}
}
