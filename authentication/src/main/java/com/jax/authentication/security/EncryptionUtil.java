package com.jax.authentication.security;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@Component
public class EncryptionUtil {

    private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
    private static final int TAG_LENGTH_BIT = 128;
    private static final int IV_LENGTH_BYTE = 16;
//	private static final int AES_KEY_BIT = 256;

    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    public static SecretKey getKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
        return secret;
    }

    public static byte[] getRandomNonce() {

        byte[] nonce = new byte[IV_LENGTH_BYTE];
        new SecureRandom().nextBytes(nonce);
        return nonce;
    }


    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public static byte[] encrypt(byte[] pText, SecretKey secret, byte[] iv) throws Exception {

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, secret, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
        byte[] encryptedText = cipher.doFinal(pText);
        return encryptedText;

    }

    public static String encryptWithPrefixIV(String pText, SecretKey secret, byte[] iv) throws Exception {

        byte[] cipherText = encrypt(pText.getBytes(), secret, iv);

        byte[] cipherTextWithIv = ByteBuffer.allocate(iv.length + cipherText.length).put(iv).put(cipherText).array();
        return Base64.getEncoder().encodeToString(cipherTextWithIv);

    }

    public static String decrypt(byte[] cText, SecretKey secret, byte[] iv) throws Exception {

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        cipher.init(Cipher.DECRYPT_MODE, secret, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
        byte[] plainText = cipher.doFinal(cText);
        return new String(plainText, UTF_8);

    }

    public static String decryptWithPrefixIV(String cText, SecretKey secret) throws Exception {

        ByteBuffer bb = ByteBuffer.wrap(Base64.getDecoder().decode(cText));

        byte[] iv = new byte[IV_LENGTH_BYTE];
        bb.get(iv);
        //bb.get(iv, 0, iv.length);

        byte[] cipherText = new byte[bb.remaining()];
        bb.get(cipherText);

        String plainText = decrypt(cipherText, secret, iv);
        return plainText;

    }

//public static String encrypt(String algorithm, String input, SecretKey key,
//    IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
//    InvalidAlgorithmParameterException, InvalidKeyException,
//    BadPaddingException, IllegalBlockSizeException {
//
//    Cipher cipher = Cipher.getInstance(algorithm);
//    cipher.init(Cipher.ENCRYPT_MODE, key, iv);
//    byte[] cipherText = cipher.doFinal(input.getBytes());
//    return Base64.getEncoder()
//        .encodeToString(cipherText);
//}
//
//public static String decrypt(String algorithm, String cipherText, SecretKey key,
//    IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
//    InvalidAlgorithmParameterException, InvalidKeyException,
//    BadPaddingException, IllegalBlockSizeException {
//
//    Cipher cipher = Cipher.getInstance(algorithm);
//    cipher.init(Cipher.DECRYPT_MODE, key, iv);
//    byte[] plainText = cipher.doFinal(Base64.getDecoder()
//        .decode(cipherText));
//    return new String(plainText);
//}

}
