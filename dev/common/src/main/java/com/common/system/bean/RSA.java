package com.common.system.bean;

/**
 * @Package : common.hyosung.common.system.utils
 * @FileName : RSA
 * @Version : 1.0
 * @Date : 2019-04-08
 * @Author : Taehyun Kim
 * @Description : RSA 암호화 복호화를 위한 class
 */

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

public class RSA {

    private String publicKeyModulus = "";
    private String publicKeyExponent = "";
    private PrivateKey privateKey = null;



    public static RSA getEncKey() {

        KeyPairGenerator generator;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } //RSA키 제네레이터 생성
        generator.initialize(2048); //키 사이즈

        KeyPair keyPair = generator.genKeyPair();

        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        PublicKey publicKey = keyPair.getPublic(); //공개키
        PrivateKey privateKey = keyPair.getPrivate(); //개인키

        RSAPublicKeySpec publicSpec;
        try {
            publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }
        String publicKeyModulus = publicSpec.getModulus().toString(16);
        String publicKeyExponent = publicSpec.getPublicExponent().toString(16);

        RSA rsa = new RSA();
        rsa.setPrivateKey(privateKey);
        rsa.setPublicKeyExponent(publicKeyExponent);
        rsa.setPublicKeyModulus(publicKeyModulus);

        return rsa;
    }

    public static boolean dec(PrivateKey privateKey, String encString) throws Exception{
        boolean result = false;

        if (privateKey == null) {
            throw new RuntimeException("암호화 비밀키 정보를 찾을 수 없습니다.");
        }
        try {
            decryptRsa(privateKey, encString);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    public static String decryptRsa(PrivateKey privateKey, String securedValue) throws Exception {
        try{
            Cipher cipher = Cipher.getInstance("RSA");
            byte[] encryptedBytes = hexToByteArray(securedValue);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            String decryptedValue = new String(decryptedBytes, "utf-8"); // 문자 인코딩 주의.
            return decryptedValue;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.toString());
            return "fail";
        }
    }

    public static byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() % 2 != 0) {
            return new byte[]{};
        }

        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            byte value = (byte)Integer.parseInt(hex.substring(i, i + 2), 16);
            bytes[(int) Math.floor(i / 2)] = value;
        }

        return bytes;

    }


    public String getPublicKeyModulus() {
        return publicKeyModulus;
    }
    public void setPublicKeyModulus(String publicKeyModulus) {
        this.publicKeyModulus = publicKeyModulus;
    }
    public String getPublicKeyExponent() {
        return publicKeyExponent;
    }
    public void setPublicKeyExponent(String publicKeyExponent) {
        this.publicKeyExponent = publicKeyExponent;
    }
    public PrivateKey getPrivateKey() {
        return privateKey;
    }
    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }


}
