package com.common.system.service;

import com.common.business.common.bean.Globals;
import com.common.system.bean.Hashing;
import com.common.system.bean.RSA;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.PrivateKey;
import java.util.HashMap;

/**
 * @Package : com.common.system.utils.service
 * @FileName :
 * @Version :
 * @Date : 2019-04-16
 * @Author : Taehyun Kim
 * @Description :
 */

@Service("securityService")
public class SecurityServiceImpl implements SecurityService {
    /**
     * ********************************************************
     *
     * @Name: createRsaKey
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-16
     * @Author : Taehyun Kim
     * @Description : Rsa Public, Private Key 생성
     * ********************************************************
     */
    @Override
    public HashMap<String, String> createRsaKey(HttpServletRequest request) throws Exception {
        RSA rsa = RSA.getEncKey();

        HttpSession session = request.getSession();
        session.setAttribute("_rsaPrivateKey_", rsa.getPrivateKey());

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("publicKeyModulus", rsa.getPublicKeyModulus());
        map.put("publicKeyExponent", rsa.getPublicKeyExponent());

        return map;
    }

    /**
     * ********************************************************
     *
     * @Name: removeRsaKey
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-16
     * @Author : Taehyun Kim
     * @Description : Rsa Private Key 삭제
     * ********************************************************
     */
    @Override
    public void removeRsaKey(HttpServletRequest request) throws Exception {
        request.getSession().removeAttribute(Globals.RSA_PRIVATE_KEY);
    }

    /**
     * ********************************************************
     *
     * @Name: decryptRsa
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-16
     * @Author : Taehyun Kim
     * @Description : Rsa 암호화문 decrypt
     * ********************************************************
     */
    @Override
    public String decryptRsa(HttpServletRequest request, String value) throws Exception {
        HttpSession session = request.getSession();
        PrivateKey privateKey = (PrivateKey) session.getAttribute(Globals.RSA_PRIVATE_KEY);

        String deValue = RSA.decryptRsa(privateKey, value);

        return deValue;
    }

    /**
     * ********************************************************
     *
     * @Name: encryptSha256Repeat
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-16
     * @Author : Taehyun Kim
     * @Description : Sha256 Hashing
     * ********************************************************
     */
    @Override
    public String encryptSha256Repeat(String value, String salt, String repeatCount) throws Exception {

        String encryptText;
        Hashing hashing = new Hashing();
        String temp = value;
        StringBuffer sb;
        for (int i = 0; i < Integer.parseInt(repeatCount); i++) {
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
     * ********************************************************
     *
     * @Name: getSaltString
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-16
     * @Author : Taehyun Kim
     * @Description : Salt String 생성
     * ********************************************************
     */
    @Override
    public String getSaltString() throws Exception {
        Hashing hashing = new Hashing();
        return hashing.createSaltString();
    }
}
