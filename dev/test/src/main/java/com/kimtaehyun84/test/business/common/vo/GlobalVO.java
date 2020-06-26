package com.kimtaehyun84.test.business.common.vo;

public class GlobalVO {
	/** 메세지 리턴하는 TYPE 문구 */
    public static final String MSG_TYPE_LABEL = "MSG_TYPE";
	/** 메세지 리턴하는 TEXT 문구 */
    public static final String MSG_TEXT_LABEL = "MSG_TEXT";
	/** 내부처리 정상 리턴:SUCCESS */
    public static final String RESULT_OK = "SUCCESS";
    /** 내부처리 에러:FAIL */
    public static final String RESULT_FAIL = "FAIL";
    /** 내부처리 SKIP */
    public static final String RESULT_SKIP = "SKIP";
    /** 내부처리 에러:SESSION_ClOSED*/
    public static final String RESULT_SESSION_CLOSE = "SESSION_ClOSED";
    /** SESSION KEY:USER */
    public static final String USER_SESSION_KEY = "session.portal.user_id";
    /** OrderCode:CE */
    public static final String ORDER_CODE = "CE";
    /** DELETE FLAG:N */
    public static final String DEL_FLAG = "N";
    /** MAN_STS_70:70 */
    public static final String MAN_STS_70 = "70";
    /** MAN_STS_20:20 */
    public static final String MAN_STS_20 = "20";
    /** MAN_STS_20:EN */
    public static final String DEFAULT_LOCALE = "EN";
    /** default page size:10*/
    public static final Integer DEFAULT_PAGE_SIZE = 2;
	/** 내부처리 정상 리턴:SUCCESS */
    public static final String SEARCH_PAGE_SIZE = "20";
    /** 내부처리 에러:FAIL */
    public static final String SEARCH_PAGE_START = "0";
    /** DB의 Code테이블(COM_CODE_DETL)에서 다국어에 대한 코드그룹:COM_MULTILANG_% */
    public static final String MULTI_LANG_CODE_GROUP_SEARCH = "COM_MULTILANG_%";
    public static final String MULTI_LANG_CODE_GROUP = "COM_MULTILANG_";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    /** DB의 Code테이블(COM_CODE_DETL)에서 다국어에 대한 코드그룹:COM_MULTILANG_% */
    public static final String MSG_UNKOWN = "Unkown Error: Find System Logging";
    public static final String MSG_ID_SUCCESS = "0";
    public static final String MSG_ID_DUPLICATE = "1";
    public static final String MSG_ID_INTEGRITY = "2";
    public static final String MSG_ID_UNKNOWN = "213";
    public static final String MSG_ID_UPDATE_ERROR = "217";

    /** 관리자 EmplNo */
    public static final String ADMIN_EMPL_NO = "0000000";
    public static final String ADMIN_ID = "AstAp";
    public static final String ADMIN_LOCALE = "EN";
    
    public static final String ALARM_WEB_TYPE = "W";
    public static final String ALARM_SEND_FLAG_Y = "Y";
    public static final String ALARM_SEND_FLAG_E = "E";
    
    /**암호화 */
    public static final String SALT_COUNT = "40";
    public static final String keystretching_repeat_count = "40";
    public static final String SECRET = "12345";

    /**
     * 내부 처리 상태 OK 메세지
     */
    public static final String OK_MSG = "Complete!";
    /**
     * 내부 처리 에러 메세지 :
     */
    public static final String ERROR_MSG = "Internal Error Please Contact Administrator";

    /**
     * 세션 만료 에러 메세지 :
     */
    public static final String SESSION_FAIL_MSG = "Session has failed";
    /**
     * RSA Private Key
     */
    public static final String RSA_PRIVATE_KEY = "_rsaPrivateKey_";

    /**
     * Sha 256 repeat count
     */
    public static final String SHA_REPEAT_COUNT = "40";
    public static final String SALT_REPEAT_COUNT = "40";
    public static final int BCRYPT_SALT_COUNT = 10;


    /**
     * Error Message List
     **/
    public static final String ERROR_MSG_LOGIN_LOCK = "User ID has locked.\nPlease Contact Administrator";
    public static final String ERROR_MSG_PWD_WRONG = "The password is incorrect.\n ID will be locked if you are wrong more than 5 times";
    public static final String ERROR_MSG_PWD_EXPIRED = "The password has expired.\nPlease change password";
    public static final String ERROR_MSG_FIRST_LOGIN = "Please change your password.";
    public static final String ERROR_MSG_ID_NOTEXIST = "User ID does not exist";

    public static final String ERROR_MSG_DUPLICATE = "User already exists";

   /** Config of JWT Token */
    public static final long ttlMillis = 30000;
}
