package com.common.system.utils;


/**
 * @Package  : com.common.system.utils
 * @FileName : SecurityUtils
 * @Version : 1.0
 * @Date : 2019-10-31
 * @Author : Taehyun Kim
 * @Description :
 * ========================================================================
 * Date              ||  Name              ||  Descripton
 *  2019-10-31       ||  taehyun.kim       ||  신규 생성
 * ========================================================================
 */

import com.common.business.common.bean.Config;
import com.common.system.bean.Hashing;
import com.common.system.bean.RSA;
import com.common.system.service.SecurityService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.PrivateKey;
import java.util.HashMap;

public class SecurityUtils {




        /**
         *********************************************************
         * @Name: createRsaKey
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-04-16
         * @Author : Taehyun Kim
         * @Description : Rsa Public, Private Key 생성
         *********************************************************
         */

        public static HashMap<String, String> createRsaKey(HttpServletRequest request) throws Exception{
            RSA rsa = RSA.getEncKey();

            HttpSession session = request.getSession();
            session.setAttribute("_rsaPrivateKey_", rsa.getPrivateKey());

            HashMap<String, String> map = new HashMap<String, String>();
            map.put("publicKeyModulus", rsa.getPublicKeyModulus());
            map.put("publicKeyExponent", rsa.getPublicKeyExponent());

            return map;
        }
        /**
         *********************************************************
         * @Name: removeRsaKey
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-04-16
         * @Author : Taehyun Kim
         * @Description : Rsa Private Key 삭제
         *********************************************************
         */

        public static  void removeRsaKey(HttpServletRequest request) throws Exception{
            request.getSession().removeAttribute(com.common.business.common.bean.Globals.RSA_PRIVATE_KEY);
        }

        /**
         *********************************************************
         * @Name: decryptRsa
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-04-16
         * @Author : Taehyun Kim
         * @Description : Rsa 암호화문 decrypt
         *********************************************************
         */

        public static String decryptRsa(String value) throws Exception {
            /*HttpSession session = request.getSession();*/
//            PrivateKey privateKey = (PrivateKey)session.getAttribute(com.common.business.common.bean.Globals.RSA_PRIVATE_KEY);
            PrivateKey privateKey = Config.getPrivateKey();
            String deValue = RSA.decryptRsa(privateKey, value);

            return deValue;
        }

        /**
         *********************************************************
         * @Name: encryptSha256Repeat
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-04-16
         * @Author : Taehyun Kim
         * @Description : Sha256 Hashing
         *********************************************************
         */

        public static String encryptSha256Repeat(String value, String salt, String repeatCount) throws Exception {

            String encryptText;
            Hashing hashing = new Hashing();
            String temp = value;
            StringBuffer sb;
            for(int i = 0; i< Integer.parseInt(repeatCount); i++){
                sb = new StringBuffer();
                sb.append(salt);
                sb.append(temp);
                temp = sb.toString();
                temp = hashing.encryptSHA256(temp);

            }
            encryptText = temp;

            return encryptText;
        }
        /**
         *********************************************************
         * @Name: getSaltString
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-04-16
         * @Author : Taehyun Kim
         * @Description : Salt String 생성
         *********************************************************
         */

        public static String getSaltString() throws Exception {
            Hashing hashing = new Hashing();
            return hashing.createSaltString();
        }


}
