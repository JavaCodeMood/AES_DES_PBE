package com.security.des;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Hex;



public class Imooc3DES {
	private static String src = "imooc security 3des";
	public static void main(String[] args) {
		jdk3DES();
	}
	
	private static void jdk3DES(){
		try {
			// ����KEY
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
			//keyGenerator.init(168);
			keyGenerator.init(new SecureRandom());
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();
			// KEY��ת��
			// ʵ����des��Կ���������
			DESedeKeySpec desKeySpec = new DESedeKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
			// ������Կ
			Key convertSecretKey = factory.generateSecret(desKeySpec);
			// ����
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("jdk 3DES�����㷨��" + Hex.encodeHexString(result));
			// ����
			cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
			result = cipher.doFinal(result);
			System.out.println("jdk 3DES�����㷨��" + new String(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void bc3DES(){
		
	}

}
