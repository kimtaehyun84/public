package com.hyosung.common.system.utils.bean;

import com.hyosung.common.business.common.bean.Globals;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @Package : com.hyosung.common.system.utils.bean
 * @FileName : Hashing
 * @Version : 1.0
 * @Date : 2019-04-16
 * @Author : Taehyun Kim
 * @Description : Hashing 관련 Class
 */
public class Hashing {

    /**
        *********************************************************
        * @Name: SHA256
        * @Type : Function
        * @Version : 1.0
        * @Date : 2019-04-16
        * @Author : Taehyun Kim
        * @Description : SHA256 Hashing Method
        *********************************************************
        */

    public String encryptSHA256(String plainText) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(plainText.getBytes());
        byte byteData[] = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        StringBuffer hexString = new StringBuffer();
        for (int i=0;i<byteData.length;i++) {
            String hex=Integer.toHexString(0xff & byteData[i]);
            if(hex.length()==1){
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
/**
    *********************************************************
    * @Name: createSaltString
    * @Type : Function
    * @Version : 1.0
    * @Date : 2019-04-16
    * @Author : Taehyun Kim
    * @Description : Salt String 생성
    *********************************************************
    */
    public String createSaltString(){
        String str = new String();
        Random rnd = new Random();
        int repeatCount = Integer.parseInt(Globals.SALT_REPEAT_COUNT);

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i< repeatCount; i++){
            int ri = rnd.nextInt(10);
            float f = rnd.nextFloat();
            boolean b = rnd.nextBoolean();

            if(b){
                sb.append((char)((Math.random() * 26) + 97));
            }
            else{
                sb.append(ri);
            }
        }
        str = sb.toString();
        return str;
    }
}
