package com.security.aes;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class ImoocAes {
	private static String src = "Java,Hello world!";
	
	public static void main(String[] args) {
		jdkAES();
	}
	
	public static void jdkAES(){
		try {
			//����key
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			//������Կ����
			keyGenerator.init(128);
			//������Կ����
			SecretKey secretKey = keyGenerator.generateKey();
			//��ȡ��Կ
			byte[] keyBytes = secretKey.getEncoded();
			//keyת��
			Key key = new SecretKeySpec(keyBytes,"AES");
			//����
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			//��ʼ��������Ϊ����
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("jdk aes encrypt: " + Base64.encodeBase64String(result));
			
			//��ʼ��,����Ϊ���� 
			cipher.init(Cipher.DECRYPT_MODE, key);
			result = cipher.doFinal(result);
			System.out.println("jdk aes desrypt:" + new String(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void bcAES(){
		
	}

}
