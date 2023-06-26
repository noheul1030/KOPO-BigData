package Date03;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Encryption {
	
	public static String key = "12345678901234567890123456789012";
	public static String text = "오늘 점심 버거킹";

	public static void main(String[] args) {
		System.out.println(encrypt(key,text));
	}
	

	public static String encrypt (String key, String text) {
		String cipherText= "";
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec ivspec = new IvParameterSpec(Arrays.copyOfRange(key.getBytes("UTF-8"),0,cipher.getBlockSize()));
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("UTF-8"),"AES"),ivspec);
			cipherText = new String(Base64.encodeBase64(cipher.doFinal(text.getBytes("UTF-8"))),"UTF-8");			
		}catch(Exception e) {
			cipherText = "";
			e.printStackTrace();
		}
		return cipherText;
	}

}
