package com.common.system.utils;


/**
 * @Package : com.common.system.utils
 * @FileName : StringUtils
 * @Version : 1.0
 * @Date : 2019-10-31
 * @Author : Taehyun Kim
 * @Description : String 처리 관련 Utils
 * ========================================================================
 * Date              ||  Name              ||  Descripton
 * 2019-10-31       ||  taehyun.kim       ||  신규 생성
 * ========================================================================
 */

import com.common.business.common.bean.Globals;
import com.common.system.service.UtilServiceImpl;


import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StringUtils {

    public static final int IDEOGRAPHIC_SPACE = 12288;
    public static final int MAX_BUFFER_SIZE = 8192;


    public StringUtils() {

    }


    public static JSONArray stringToJsonArray(String jsonString) {
        Object obj = null;
        JSONArray jsonArray = null;
        JSONParser jsonParser = new JSONParser();
        try {
            obj = jsonParser.parse(jsonString);
            jsonArray = (JSONArray) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonArray;
    }


    public static JSONObject stringToJson(String jsonString) {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonParser.parse(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject;

    }

    public static HashMap<String, Object> jsonToMap(JSONObject jsonObject) {
        HashMap<String, Object> resultMap = null;
        try {
            resultMap = new ObjectMapper().readValue(jsonObject.toJSONString(), HashMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }


    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNull(Object object) {
        /**
         * @Name: isNull
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-10-31
         * @Author : Taehyun Kim
         * @Param : [object]
         * @Return : boolean
         * @Description : Object Null check
         * ========================================================================
         *  Date              ||  Name              ||  Descripton
         *  2019-10-31       ||  taehyun.kim       ||  신규 생성
         * ========================================================================
         */
        return object == null || object.toString().trim().length() == 0;
    }

    public static boolean isNull(String string) {
        /**
         * @Name: isNull
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-10-31
         * @Author : Taehyun Kim
         * @Param : [string]
         * @Return : boolean
         * @Description : String null check
         * ========================================================================
         *  Date              ||  Name              ||  Descripton
         *  2019-10-31       ||  taehyun.kim       ||  신규 생성
         * ========================================================================
         */
        return isNull((Object) string);
    }

    public static String rightTrim(String str, String trimString) {
        /**
         * @Name: rightTrim
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-10-31
         * @Author : Taehyun Kim
         * @Param : [str, trimString]
         * @Return : java.lang.String
         * @Description : 오른쪽부터 trimString 까지 trim 수행
         * ========================================================================
         *  Date              ||  Name              ||  Descripton
         *  2019-10-31       ||  taehyun.kim       ||  신규 생성
         * ========================================================================
         */
        return rightTrim(str, trimString, 0);

    }

    public static String rightTrim(String str, int trimIndex) {
        /**
         * @Name: rightTrim
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-10-31
         * @Author : Taehyun Kim
         * @Param : [str, trimIndex]
         * @Return : java.lang.String
         * @Description : 오른쪽부터 trimIndex 만큼 trim 수행
         * ========================================================================
         *  Date              ||  Name              ||  Descripton
         *  2019-10-31       ||  taehyun.kim       ||  신규 생성
         * ========================================================================
         */
        return rightTrim(str, null, trimIndex);
    }


    public static String rightTrim(String str, String trimString, int trimIndex) {
        /**
         * @Name: rightTrim
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-10-31
         * @Author : Taehyun Kim
         * @Param : [str, trimString, trimIndex]
         * @Return : java.lang.String
         * @Description : 오른쪽부터 지정된 문자열 혹은 지정된 index까지 trim 처리
         * ========================================================================
         *  Date              ||  Name              ||  Descripton
         *  2019-10-31       ||  taehyun.kim       ||  신규 생성
         * ========================================================================
         */
        if (str != null && str.length() != 0 && trimIndex >= 0) {
            char[] charArray = str.toCharArray();
            int trimLength = charArray.length;
            int trimPosition = trimLength;
            if (trimString == null) {
                while (trimPosition > trimIndex && (charArray[trimPosition - 1] <= ' ' || charArray[trimPosition - 1] == IDEOGRAPHIC_SPACE || Character.isWhitespace(charArray[trimPosition - 1]))) {
                    --trimPosition;
                }
            } else {
                while (trimPosition > trimIndex && trimString.indexOf(charArray[trimPosition - 1]) != -1) {
                    --trimPosition;
                }
            }

            return trimPosition <= 0 && trimPosition >= trimLength ? str : str.substring(0, trimPosition);
        } else {
            return str;
        }
    }

    public static String rightPad(String str, int size, char padChar) {
        /**
         * @Name: rightPad
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-10-31
         * @Author : Taehyun Kim
         * @Param : [str, size, padChar]
         * @Return : java.lang.String
         * @Description : padchar를 이용하여 size만큼 padding 설정
         * ========================================================================
         *  Date              ||  Name              ||  Descripton
         *  2019-10-31       ||  taehyun.kim       ||  신규 생성
         * ========================================================================
         */
        if (str == null) {
            return null;
        } else {
            int pads = size - str.length();
            if (pads <= 0) {
                return str;
            } else {
                return pads > MAX_BUFFER_SIZE ? rightPad(str, size, String.valueOf(padChar)) : str.concat(repeat(padChar, pads));
            }
        }

    }

    public static String rightPad(String str, int size, String padStr) {
        /**
         * @Name: rightPad
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-10-31
         * @Author : Taehyun Kim
         * @Param : [str, size, padStr]
         * @Return : java.lang.String
         * @Description : rightPad(String str, int size, char padChar)의 모체 method
         * ========================================================================
         *  Date              ||  Name              ||  Descripton
         *  2019-10-31       ||  taehyun.kim       ||  신규 생성
         * ========================================================================
         */
        if (str == null) {
            return null;
        } else {
            if (isEmpty(padStr)) {
                padStr = " ";
            }
            int padLength = padStr.length();
            int strLen = str.length();
            int pads = size - strLen;
            if (pads <= 0) {
                return str;
            } else if (padLength == 1 && pads <= MAX_BUFFER_SIZE) {
                return rightPad(str, size, padStr.charAt(0));
            } else if (pads == padLength) {
                return str.concat(padStr);
            } else if (pads < padLength) {
                return str.concat(padStr.substring(0, pads));
            } else {
                char[] padding = new char[pads];
                char[] padChars = padStr.toCharArray();

                for (int i = 0; i < pads; ++i) {
                    padding[i] = padChars[i % padLength];
                }

                return str.concat(new String(padding));
            }
        }
    }

    public static String repeat(char ch, int repeatCount) {
        /**
         * @Name: repeat
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-10-31
         * @Author : Taehyun Kim
         * @Param : [ch, repeatCount]
         * @Return : java.lang.String
         * @Description : repeatCount만큼 ch를 반복하여 String으로 return
         * ========================================================================
         *  Date              ||  Name              ||  Descripton
         *  2019-10-31       ||  taehyun.kim       ||  신규 생성
         * ========================================================================
         */
        if (repeatCount <= 0) {
            return "";
        } else {
            char[] buf = new char[repeatCount];
            for (int i = repeatCount - 1; i >= 0; --i) {
                buf[i] = ch;
            }

            return new String(buf);
        }
    }

    public static String leftPad(String str, int size, String padStr) {
        if (str == null) {
            return null;
        } else {
            if (isEmpty(padStr)) {
                padStr = " ";
            }
            int padLen = padStr.length();
            int strLen = str.length();
            int pads = size - strLen;
            if (pads <= 0) {
                return str;
            } else if (padLen == 1 && pads <= MAX_BUFFER_SIZE) {
                return leftPad(str, size, padStr.charAt(0));
            } else if (pads == padLen) {
                return padStr.concat(str);
            } else if (pads < padLen) {
                return padStr.substring(0, pads).concat(str);
            } else {
                char[] padding = new char[pads];
                char[] padChars = padStr.toCharArray();

                for (int i = 0; i < pads; ++i) {
                    padding[i] = padChars[i % padLen];
                }

                return (new String(padding)).concat(str);
            }
        }
    }

    public static String leftPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        } else {
            int pads = size - str.length();
            if (pads <= 0) {
                return str;
            } else {
                return pads > MAX_BUFFER_SIZE ? leftPad(str, size, String.valueOf(padChar)) : repeat(padChar, pads).concat(str);
            }
        }
    }


}
