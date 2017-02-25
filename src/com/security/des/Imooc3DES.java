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
			// 生成KEY
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
			//keyGenerator.init(168);
			keyGenerator.init(new SecureRandom());
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();
			// KEY的转化
			// 实例化des密钥的相关内容
			DESedeKeySpec desKeySpec = new DESedeKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
			// 生成密钥
			Key convertSecretKey = factory.generateSecret(desKeySpec);
			// 加密
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("jdk 3DES加密算法：" + Hex.encodeHexString(result));
			// 解密
			cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
			result = cipher.doFinal(result);
			System.out.println("jdk 3DES解密算法：" + new String(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void bc3DES(){
		
	}

}
