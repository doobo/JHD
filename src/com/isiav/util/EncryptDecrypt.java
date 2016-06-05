package com.isiav.util;

import java.lang.reflect.Method;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Java���ܽ��ܹ���.
 * JavaSE/JavaEE/Android������
 * 
 * @author longshu 2016��4��13��
 */
public class EncryptDecrypt {
	// ���贴������
	private EncryptDecrypt() {
	}

	/**
	 * SHA1����Bit����
	 * @param source byte����
	 * @return ���ܺ��byte����
	 */
	public static byte[] SHA1Bit(byte[] source) {
		try {
			MessageDigest sha1Digest = MessageDigest.getInstance("SHA-1");
			sha1Digest.update(source);
			byte targetDigest[] = sha1Digest.digest();
			return targetDigest;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * SHA1�����ַ�������
	 * @param source Ҫ���ܵ��ַ���
	 * @return ���ܺ���ַ���
	 */
	public static String SHA1(String source) {
		return byte2HexStr(SHA1Bit(source.getBytes()));
	}

	/**
	 * MD5����Bit����
	 * @param source byte����
	 * @return ���ܺ��byte����
	 */
	public static byte[] MD5Bit(byte[] source) {
		try {
			// ���MD5ժҪ�㷨�� MessageDigest����
			MessageDigest md5Digest = MessageDigest.getInstance("MD5");
			// ʹ��ָ�����ֽڸ���ժҪ
			md5Digest.update(source);
			// �������
			return md5Digest.digest();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * MD5�����ַ���,32λ��
	 * @param source Ҫ���ܵ�����
	 * @return ���ܺ������
	 */
	public static String MD5(String source) {
		return byte2HexStr(MD5Bit(source.getBytes()));
	}

	/**
	 * BASE64����
	 * @param source Ҫ������ַ���
	 * @return ��������ַ���
	 */
	public static String encodeBASE64(String source) {
		Class<?> clazz = null;
		Method encodeMethod = null;
		try {// ����ʹ�õ�������
			clazz = Class.forName("org.apache.commons.codec.binary.Base64");
			encodeMethod = clazz.getMethod("encodeBase64", byte[].class);
//			System.out.println("encodeBASE64-->" + clazz);
//			System.out.println("encodeMethod-->" + encodeMethod);
			// ���䷽�� ��̬����ִ���������
			return new String((byte[]) encodeMethod.invoke(null, source.getBytes()));
		} catch (ClassNotFoundException e) {
			String vm = System.getProperty("java.vm.name");
//			System.out.println(vm);
			try {
				if ("Dalvik".equals(vm)) {// Android
					clazz = Class.forName("android.util.Base64");
					// byte[] Base64.encode(byte[] input,int flags)
					encodeMethod = clazz.getMethod("encode", byte[].class, int.class);
//					System.out.println("encodeBASE64-->" + clazz);
//					System.out.println("encodeMethod-->" + encodeMethod);
					return new String((byte[]) encodeMethod.invoke(null, source.getBytes(), 0));
				} else {// JavaSE/JavaEE
					clazz = Class.forName("sun.misc.BASE64Encoder");
					encodeMethod = clazz.getMethod("encode", byte[].class);
//					System.out.println("encodeBASE64-->" + clazz);
//					System.out.println("encodeMethod-->" + encodeMethod);
					return (String) encodeMethod.invoke(clazz.newInstance(), source.getBytes());
				}
			} catch (ClassNotFoundException e1) {
				return null;
			} catch (Exception e1) {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		/*
		 * Android
		 * android.util.Base64
		 */
		// return Base64.encodeToString(source, Base64.DEFAULT);
		// return new String(Base64.encode(source.getBytes(), Base64.DEFAULT));

		/*
		 * JavaSE/JavaEE
		 */
		// sun.misc.BASE64Encoder
		// BASE64Encoder encoder = new BASE64Encoder();
		// return encoder.encode(source.getBytes());

		// org.apache.commons.codec.binary.Base64
		// return new String(Base64.encodeBase64(source.getBytes()));
	}

	/**
	 * BASE64����
	 * @param encodeSource ��������ַ���
	 * @return ����ǰ���ַ���
	 */
	public static String decodeBASE64(String encodeSource) {
		Class<?> clazz = null;
		Method decodeMethod = null;
		try {// ����ʹ�õ�������
			clazz = Class.forName("org.apache.commons.codec.binary.Base64");
			decodeMethod = clazz.getMethod("decodeBase64", byte[].class);
//			System.out.println("decodeBASE64-->" + clazz);
//			System.out.println("decodeMethod-->" + decodeMethod);
			// ���䷽�� ��̬����ִ���������
			return new String((byte[]) decodeMethod.invoke(null, encodeSource.getBytes()));
		} catch (ClassNotFoundException e) {
			String vm = System.getProperty("java.vm.name");
//			System.out.println(vm);
			try {
				if ("Dalvik".equals(vm)) {// Android
					clazz = Class.forName("android.util.Base64");
					// byte[] Base64.decode(byte[] input, int flags)
					decodeMethod = clazz.getMethod("decode", byte[].class, int.class);
//					System.out.println("decodeBASE64-->" + clazz);
//					System.out.println("decodeMethod-->" + decodeMethod);
					return new String((byte[]) decodeMethod.invoke(null, encodeSource.getBytes(), 0));
				} else { // JavaSE/JavaEE
					clazz = Class.forName("sun.misc.BASE64Decoder");
					decodeMethod = clazz.getMethod("decodeBuffer", String.class);
//					System.out.println("decodeBASE64-->" + clazz);
//					System.out.println("decodeMethod-->" + decodeMethod);
					return new String((byte[]) decodeMethod.invoke(clazz.newInstance(), encodeSource));
				}
			} catch (ClassNotFoundException e1) {
				return null;
			} catch (Exception e1) {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		/*
		 * Android
		 * android.util.Base64
		 */
		// return new
		// String(Base64.decode(encodeSource.getBytes(),Base64.DEFAULT));

		/*
		 * JavaSE/JavaEE
		 */
		// sun.misc.BASE64Decoder
		// try {
		// BASE64Decoder decoder = new BASE64Decoder();
		// return new String(decoder.decodeBuffer(encodeSource));
		// } catch (IOException e) {
		// throw new RuntimeException(e);
		// }

		// org.apache.commons.codec.binary.Base64
		// return new String(Base64.decodeBase64(encodeSource.getBytes()));
	}

	/**
	 * AES����
	 * @param content �����ܵ�����
	 * @param password ��������
	 * @return
	 */
	public static byte[] encryptBitAES(byte[] content, String password) {
		try {
			Cipher encryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// ����������
			encryptCipher.init(Cipher.ENCRYPT_MODE, getKey(password));// ��ʼ��
			byte[] result = encryptCipher.doFinal(content);
			return result; // ����
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * AES����
	 * @param content ����������
	 * @param password ������Կ
	 * @return
	 */
	public static byte[] decryptBitAES(byte[] content, String password) {
		try {
			Cipher decryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// ����������
			decryptCipher.init(Cipher.DECRYPT_MODE, getKey(password));// ��ʼ��
			byte[] result = decryptCipher.doFinal(content);
			return result; // ���ܽ��
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * AES�ַ�������
	 * @param content �����ܵ�����
	 * @param password ��������
	 * @return
	 */
	public static String encryptAES(String content, String password) {
		return byte2HexStr(encryptBitAES(content.getBytes(), password));
	}

	/**
	 * AES�ַ�������
	 * @param content ����������
	 * @param password ������Կ
	 * @return
	 */
	public static String decryptAES(String content, String password) {
		return new String(decryptBitAES(hexStr2Bytes(content), password));
	}

	/**
	 * ��ָ���ַ���������Կ
	 * @param password ���ɸ���Կ���ַ���
	 * @return ���ɵ���Կ
	 * @throws NoSuchAlgorithmException
	 */
	private static Key getKey(String password) throws NoSuchAlgorithmException {
		SecureRandom secureRandom = new SecureRandom(password.getBytes());
		// ����KEY
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, secureRandom);
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		// ת��KEY
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		return key;
	}

	/**
	 * ��byte����ת��Ϊ��ʾ16����ֵ���ַ���.
	 * �磺byte[]{8,18}ת��Ϊ��0812 
	 * �� byte[] hexStr2Bytes(String strIn) ��Ϊ�����ת������.
	 * @param bytes ��Ҫת����byte����
	 * @return ת������ַ���
	 */
	public static String byte2HexStr(byte[] bytes) {
		int bytesLen = bytes.length;
		// ÿ��byte�������ַ����ܱ�ʾ�������ַ����ĳ��������鳤�ȵ�����
		StringBuffer hexString = new StringBuffer(bytesLen * 2);
		for (int i = 0; i < bytesLen; i++) {
			// ��ÿ���ֽ���0xFF���������㣬Ȼ��ת��Ϊ10���ƣ�Ȼ�������Integer��ת��Ϊ16����
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() < 2) {
				hexString.append(0);// ���Ϊ1λ ǰ�油��0
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

	/**
	 * ����ʾ16����ֵ���ַ���ת��Ϊbyte����,
	 * �� String byte2HexStr(byte[] bytes) ��Ϊ�����ת������.
	 * @param bytes
	 * @return ת�����byte����
	 */
	public static byte[] hexStr2Bytes(String strIn) {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		// �����ַ���ʾһ���ֽڣ������ֽ����鳤�����ַ������ȳ���2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}
//	public static void main(String[] args) {
//		System.out.println(SHA1(SHA1("188588qg")));
//		System.out.println(SHA1("188588qg"));
//		System.out.println(MD5("188588qg"));
//		System.out.println(encryptAES("�ֿ�˰��", "123456"));
//		System.out.println(decryptAES("b58cb014622da36512b3f6348bf1a5df", "123456"));
//		System.out.println(encodeBASE64("�ֿ�˰�������˼��"));
//		System.out.println(decodeBASE64("tda/28uwv+6/qMCttsDBosu8v7w="));
//	}
}
