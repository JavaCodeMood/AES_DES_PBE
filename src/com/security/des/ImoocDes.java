package com.security.des;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Hex;

public class ImoocDes {
	private static String src = "Java实现DES加密解密";
	
	public static void main(String[] args) {
		jdkDES();
	}
	
	public static void jdkDES() {
		try {
			// 生成KEY
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
			keyGenerator.init(56);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();
			// KEY的转化
			// 实例化des密钥的相关内容
			DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
			// 生成密钥
			Key convertSecretKey = factory.generateSecret(desKeySpec);
			// 加密
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("jdk DES加密算法：" + Hex.encodeHexString(result));
			// 解密
			cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
			result = cipher.doFinal(result);
			System.out.println("jdk DES解密算法：" + new String(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
