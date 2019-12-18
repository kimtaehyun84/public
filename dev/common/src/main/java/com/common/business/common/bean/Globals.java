package com.common.business.common.bean;

/**
 * @Package  : common.hyosung.common.business.common.vo
 * @FileName : Globals
 * @Version : 1.0
 * @Date : 2019-04-08
 * @Author : Taehyun Kim
 * @Description : 내부에서 공통으로 사용하는 Key값
 */
public class Globals {
    /** 내부 처리 상태 리턴 : SUCCESS */
    public static final String RESULT_OK = "SUCCESS";

    /**내부 처리 상태 리턴 : FAIL */
    public static final String RESULT_FAIL = "FAIL";

     /** 내부 처리 상태 OK 메세지 */
    public static final String OK_MSG = "Complete!";
    /**내부 처리 에러 메세지 :  */
    public static final String ERROR_MSG = "Internal Error Please Contact Administrator";
    /**세션 만료 에러 메세지 :  */
    public static final String SESSION_FAIL_MSG = "Session has failed";

    /** Error Message List **/
    public static final String ERROR_MSG_LOGIN_LOCK = "User ID has locked\nPlease Contact Administrator";
    public static final String ERROR_MSG_PWD_WRONG = "The password is incorrect.\n ID will be locked if you are wrong more than 5 times";
    public static final String ERROR_MSG_PWD_EXPIRED = "The password has expired\nPlease change password";





    /** Session Key */
    public static final String SESSION_KEY = "session.key.common";
    /** RSA Private Key */
    public static final String RSA_PRIVATE_KEY = "_rsaPrivateKey_";
    /** Sha 256 repeat count */
    public static final String SHA_REPEAT_COUNT = "40";
    public static final String SALT_REPEAT_COUNT = "40";


    public static final String TYPE_JSON = "JSON";
    public static final String TYPE_JSONARRAY = "JSONARRAY";
    public static final String TYPE_STRING = "STRING";
    public static final String TYPE_INTEGER = "INTEGER";
    public static final String TYPE_MAP = "MAP";
    public static final String TYPE_OBJECT = "OBJECT";




}
