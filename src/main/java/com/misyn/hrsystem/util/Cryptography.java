/*
 * Cryptography
 */
package com.misyn.hrsystem.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 *
 * @author Shanaka
 * encrypt and decrypt methods are going here
 */
public class Cryptography {

    private Cipher dcipher;

    private Cipher ecipher;

    private String encryptionKey = "StargateFX";

    public Cryptography() {
        this.initData();
    }
    
    public Cryptography(String encryptionKey) {
        this.encryptionKey = encryptionKey.trim();
        this.initData();
    }

    /**
     * Initialize data
     */
    private void initData() {
        // 8-bytes Salt
        byte[] salt = {(byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
            (byte) 0x56, (byte) 0x34, (byte) 0xE3, (byte) 0x03};

        // Iteration count
        int iterationCount = 19;

        try {
            KeySpec keySpec = new PBEKeySpec(encryptionKey.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);

            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());

            // Prepare the parameters to the cipthers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

        } catch (InvalidAlgorithmParameterException e) {
            System.out.println("ERROR: Cryptography.initData() " + e.getMessage());
        } catch (InvalidKeySpecException e) {
            System.out.println("ERROR: Cryptography.initData() " + e.getMessage());
        } catch (NoSuchPaddingException e) {
            System.out.println("ERROR: Cryptography.initData() " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("ERROR: Cryptography.initData() " + e.getMessage());
        } catch (InvalidKeyException e) {
            System.out.println("ERROR: Cryptography.initData() " + e.getMessage());
        }
    }

    public String encrypt(String str) {
        try {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");
            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);
            // Encode bytes to base64 to get a string
            return new sun.misc.BASE64Encoder().encode(enc);
        } catch (BadPaddingException e) {
            System.out.println("ERROR: Cryptography.encrypt() " + e.getMessage());
        } catch (IllegalBlockSizeException e) {
            System.out.println("ERROR: Cryptography.encrypt() " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println("ERROR: Cryptography.encrypt() " + e.getMessage());
        }
        return "";
    }

    public String decrypt(String str) {
        try {
            // Decode base64 to get bytes
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
            // Decrypt
            byte[] utf8 = dcipher.doFinal(dec);
            // Decode using utf-8
            return new String(utf8, "UTF8");
        } catch (BadPaddingException e) {
            System.out.println("ERROR: Cryptography.decrypt() " + e.getMessage());
        } catch (IllegalBlockSizeException e) {
            System.out.println("ERROR: Cryptography.decrypt() " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println("ERROR: Cryptography.decrypt() " + e.getMessage());
        } catch (IOException e) {
            System.out.println("ERROR: Cryptography.decrypt() " + e.getMessage());
        }
        return "";
    }
    
    public static void main(String[] args) {
        
        Cryptography c = new Cryptography();
        System.out.println(c.encrypt("123456"));
        System.out.println(c.decrypt("9ZS7jAk2apQ="));
        
    }

}
