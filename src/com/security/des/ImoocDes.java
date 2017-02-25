package com.security.des;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Hex;

public class ImoocDes {
	private static String src = "Javaʵ��DES���ܽ���";
	
	public static void main(String[] args) {
		jdkDES();
	}
	
	public static void jdkDES() {
		try {
			// ����KEY
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
			keyGenerator.init(56);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();
			// KEY��ת��
			// ʵ����des��Կ���������
			DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
			// ������Կ
			Key convertSecretKey = factory.generateSecret(desKeySpec);
			// ����
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("jdk DES�����㷨��" + Hex.encodeHexString(result));
			// ����
			cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
			result = cipher.doFinal(result);
			System.out.println("jdk DES�����㷨��" + new String(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
