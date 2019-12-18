package com.common.business.common.bean;

import com.common.business.common.service.CommonService;
import com.common.system.bean.RSA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.PrivateKey;

import java.util.HashMap;
import java.util.Map;

public class Config {
    /**
     * @Package  : com.common.business.common.bean
     * @FileName : Config
     * @Version : 1.0
     * @Date : 2019-12-03
     * @Author : Taehyun Kim
     * @Description : Server configuration
     * ========================================================================
     * Date              ||  Name              ||  Descripton
     *  2019-12-03       ||  taehyun.kim       ||  신규 생성
     * ========================================================================
     */
    private static String[] loginType;

    private static String loginEnable;

    private static String shaRepeatCnt;
    private static String publicKeyExponent;
    private static String publicKeyModulus;
    private static PrivateKey privateKey;
    private static String corsDomain;
    private static String corsHeader;


    private static String type;
    public void setType(String type){
        this.type = type;
    }

    private static String logHome;
    public void setLogHome(String logHome){
        this.logHome = logHome;
    }

    private static String logMaxHistoryDate;
    public void setLogMaxHistoryDate(String logMaxHistoryDate){
        this.logMaxHistoryDate = logMaxHistoryDate;
    }


    protected CommonService service;
    public void setCommonService (CommonService service){
        this.service = service;
    }

    Logger logger = LoggerFactory.getLogger(this.getClass());


    public void init() throws Exception{
        /**
        * @Name: init
        * @Type : Function
        * @Version : 1.0
        * @Date : 2019-12-03
        * @Author : Taehyun Kim
        * @Param : []
        * @Return : void
        * @Description : type에 따른 초기 Config 설정
        * ========================================================================
        *  Date              ||  Name              ||  Descripton
        *  2019-12-03       ||  taehyun.kim       ||  신규 생성
        * ========================================================================
        */
        logger.info(Logs.LOG_START);
        logger.info("Initialize Server Configuration");

        if(this.type == null || "".equals(this.type)){
            this.type = "common";
            logger.info("Server Type is null, set server type common");

        }
        logger.info("Server Type : " + this.type);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            resultMap = service.selectServerConfig(this.type);
            if (resultMap != null) {

                String loginTypeString = resultMap.get("loginType") != null ? resultMap.get("loginType").toString() : "";
                this.loginType = loginTypeString.split(";");

                this.loginEnable = resultMap.get("loginEnable") != null ? resultMap.get("loginEnable").toString() : "";
                this.shaRepeatCnt = resultMap.get("shaRepeatCnt") != null ? resultMap.get("shaRepeatCnt").toString() : "";
                this.corsDomain = resultMap.get("corsDomain") != null ? resultMap.get("corsDomain").toString() : "";
                this.corsHeader = resultMap.get("corsHeader") != null ? resultMap.get("corsHeader").toString() : "";

                logger.info("Login Enable : " + this.loginEnable);

                logger.info("Login Type : " + loginTypeString);
                logger.debug("SHA Repeat Count : " + this.shaRepeatCnt);

                logger.info("Log Home : " + this.logHome);
                logger.info("Cors Allowed Domain : " + this.corsDomain);
                logger.info("Cors Allowed Headers : " + this.corsHeader);
                logger.info("Load Configuration Complete");

            }
        }
        catch(Exception e){
            logger.info("Fail to get config from database");
            throw e;
        }
        try{

                logger.info("Start to create RSA key");
                RSA rsa = RSA.getEncKey();
                this.privateKey = rsa.getPrivateKey();
                this.publicKeyModulus = rsa.getPublicKeyModulus();
                this.publicKeyExponent = rsa.getPublicKeyExponent();
                logger.debug("Private Key : " + this.privateKey.getEncoded());
                logger.debug("publicKey Exponent : " + this.publicKeyExponent);
                logger.debug("publicKey Modulus : " + this.publicKeyModulus);
                logger.info("Set RSA key Complete");

        } catch (Exception e) {
            logger.info("Fail to set RSA key");
            throw e;
        }


    }

    public static String[] getLoginType() {
        return loginType;
    }

    public static String getLoginEnable() {
        return loginEnable;
    }

    public static String getLogHome() {
        return logHome;
    }

    public static String getShaRepeatCnt() {
        return shaRepeatCnt;
    }

    public static String getPublicKeyExponent() {
        return publicKeyExponent;
    }

    public static String getPublicKeyModulus() {
        return publicKeyModulus;
    }

    public static PrivateKey getPrivateKey() {
        return privateKey;
    }

    public static String getCorsDomain() {
        return corsDomain;
    }

    public static String getCorsHeader() {
        return corsHeader;
    }
}
