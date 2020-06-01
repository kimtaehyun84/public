package com.common.system.utils;

import org.springframework.cglib.core.Local;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
    /**
     * @Package : com.common.system.utils
     * @FileName : DateUtils
     * @Version : 1.0
     * @Date : 2019-12-11
     * @Author : Taehyun Kim
     * @Description : 날짜 처리 관련 Utils
     * ========================================================================
     * Date              ||  Name              ||  Descripton
     * 2019-12-11       ||  taehyun.kim       ||  신규 생성
     * ========================================================================
     */


    public static LocalDate stringToDate(String value, String format) throws Exception {
        /**
         * @Name: stringToDate
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-12-11
         * @Author : Taehyun Kim
         * @Param : [value, format]
         * @Return : java.time.LocalDate
         * @Description : String 형식을 LocalDate형식으로 포맷에 맞게 변환
         * ========================================================================
         *  Date              ||  Name              ||  Descripton
         *  2019-12-11       ||  taehyun.kim       ||  신규 생성
         * ========================================================================
         */
        LocalDate localDate = LocalDate.parse(value, DateTimeFormatter.ofPattern(format));

        return localDate;
    }

    public static LocalDateTime stringToDateTime(String value, String format) throws Exception {
        /**
         * @Name: stringToDateTime
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-12-11
         * @Author : Taehyun Kim
         * @Param : [value, format]
         * @Return : java.time.LocalDateTime
         * @Description : String 형식을 LocalDateTime형식으로 포맷에 맞게 변환
         * ========================================================================
         *  Date              ||  Name              ||  Descripton
         *  2019-12-11       ||  taehyun.kim       ||  신규 생성
         * ========================================================================
         */
        LocalDateTime localDateTime = LocalDateTime.parse(value, DateTimeFormatter.ofPattern(format));

        return localDateTime;
    }


    public static String dateToString(LocalDate date, String format) throws Exception {
        /**
         * @Name: dateToString
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-12-11
         * @Author : Taehyun Kim
         * @Param : [date, format]
         * @Return : java.lang.String
         * @Description : LocalDate형식을 String으로 입력한 포맷에 맞게 변환
         * ========================================================================
         *  Date              ||  Name              ||  Descripton
         *  2019-12-11       ||  taehyun.kim       ||  신규 생성
         * ========================================================================
         */
        String newString = date.format(DateTimeFormatter.ofPattern(format));
        return newString;
    }


    public static String getNowDate(String format) throws Exception {
        /**
         * @Name: getNowDate
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-12-11
         * @Author : Taehyun Kim
         * @Param : [format]
         * @Return : java.lang.String
         * @Description : 현재 날짜를 포맷에 맞게 String으로 리턴
         * ========================================================================
         *  Date              ||  Name              ||  Descripton
         *  2019-12-11       ||  taehyun.kim       ||  신규 생성
         * ========================================================================
         */

        String localDate = LocalDate.now().format(DateTimeFormatter.ofPattern(format));


        return localDate;
    }

    public static String getNowDateTime(String format) throws Exception {
        /**
         * @Name: getNowDateTime
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-12-11
         * @Author : Taehyun Kim
         * @Param : [format]
         * @Return : java.lang.String
         * @Description : 현재 날짜시간을 포맷에 맞게 String으로 리턴
         * ========================================================================
         *  Date              ||  Name              ||  Descripton
         *  2019-12-11       ||  taehyun.kim       ||  신규 생성
         * ========================================================================
         */
        String localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
        return localDateTime;
    }

    /**
     * ********************************************************
     *
     * @Name: getTimeDiffBySec
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-16
     * @Author : Taehyun Kim
     * @Description : 현재 시간으로 부터 차이를 초단위로 리턴
     * 현재보다 과거면 양수 미래면 음수로 표현
     * ********************************************************
     */

    public static long getTimeDiffBySec(String value, String format) throws Exception {
        /*logger.debug(value + " , " + format);*/
        LocalDateTime inputDateTime = stringToDateTime(value, format);
        LocalDateTime nowDateTime = LocalDateTime.now();
        /*logger.info("input date : " + inputDate);
        logger.debug("input date : " + inputDate.getTime());
        logger.debug("now date : " + nowDate);
        logger.debug("now date : " + nowDate.getTime());*/
        long timeDiff = ChronoUnit.SECONDS.between(nowDateTime, inputDateTime);


        return timeDiff;
    }

    /**
     * ********************************************************
     *
     * @Name: getTimeDiffByHour
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-16
     * @Author : Taehyun Kim
     * @Parameter : String value, String format
     * @Description : 현재 시간으로 부터 차이를 시간단위로 리턴
     * 현재보다 과거면 양수 미래면 음수로 표현
     * ********************************************************
     */

    public static long getTimeDiffByHour(String value, String format) throws Exception {
        /*logger.debug(value + " , " + format);*/
        LocalDateTime inputDateTime = stringToDateTime(value, format);
        LocalDateTime nowDateTime = LocalDateTime.now();
        /*logger.info("input date : " + inputDate);
        logger.debug("input date : " + inputDate.getTime());
        logger.debug("now date : " + nowDate);
        logger.debug("now date : " + nowDate.getTime());*/
        long timeDiff = ChronoUnit.HOURS.between(nowDateTime, inputDateTime);
        return timeDiff;
    }

    /**
     * ********************************************************
     *
     * @Name: getTimeDiffByDay
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-16
     * @Author : Taehyun Kim
     * @Parameter : String value, String format
     * @Description : 현재 시간으로 부터 차이를 일단위로 리턴
     * 현재보다 과거면 양수 미래면 음수로 표현
     * ********************************************************
     */

    public static long getTimeDiffByDay(String value, String format) throws Exception {

        /*logger.debug(value + " , " + format);*/
        LocalDateTime inputDateTime = stringToDateTime(value, format);
        LocalDateTime nowDateTime = LocalDateTime.now();
        /*logger.info("input date : " + inputDate);
        logger.debug("input date : " + inputDate.getTime());
        logger.debug("now date : " + nowDate);
        logger.debug("now date : " + nowDate.getTime());*/
        long timeDiff = ChronoUnit.DAYS.between(nowDateTime, inputDateTime);
        return timeDiff;
    }

    public static long getDateDiffByDay(String value, String format) throws Exception {
        /**
         * @Name: getDateDiffByDay
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-12-12
         * @Author : Taehyun Kim
         * @Param : [value, format]
         * @Return : long
         * @Description : 현재 시간으로 부터 차이를 일단위로 리턴
         *                현재보다 과거면 양수 미래면 음수로 표현
         * ========================================================================
         *  Date              ||  Name              ||  Descripton
         *  2019-12-12       ||  taehyun.kim       ||  신규 생성
         * ========================================================================
         */
        LocalDate inputDate = stringToDate(value, format);
        LocalDate nowDate = LocalDate.now();

        long timeDiff = ChronoUnit.DAYS.between(inputDate, nowDate);
        return nowDate.isBefore(inputDate) ? (-1 * timeDiff) : timeDiff;
    }
    public static String getNowTime(String dateFormat) {
        /**
         * @Name: getNowTime
         * @Type : Function
         * @Version : 1.0
         * @Date : 2020/05/21
         * @Author : Taehyun.Kim
         * @Param : [dateFormat]
         * @Return : java.lang.String
         * @Description : 입력된 포맷 형태의 현재시간을 반환한다.
         * ========================================================================
         *  Date              ||  Name              ||  Descripton
         *  2020/05/21        ||  Taehyun.Kim       ||  신규 생성
         * ========================================================================
         */
        Calendar cal = GregorianCalendar.getInstance();
        String retStr = "";
        SimpleDateFormat sim = new SimpleDateFormat(dateFormat);
        retStr = sim.format(cal.getTime());
        return retStr;
    }
}
