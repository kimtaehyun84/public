package com.common.system.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    /**
     *********************************************************
     * @Name: stringToDate
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-16
     * @Author : Taehyun Kim
     * @Description : String 형식을 Date형식으로 포맷에 맞게 변환
     *********************************************************
     */

    public static Date stringToDate(String value, String format) throws Exception {
        Date newDate = new SimpleDateFormat(format).parse(value);

        return newDate;
    }
    /**
     *********************************************************
     * @Name: dateToString
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-16
     * @Author : Taehyun Kim
     * @Description : date형식을 String으로 입력한 포맷에 맞게 변환
     *********************************************************
     */

    public static String dateToString(Date date, String format) throws Exception {
        String newString = new SimpleDateFormat(format).format(date);
        return newString;
    }
    /**
     *********************************************************
     * @Name: getNowDate
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-16
     * @Author : Taehyun Kim
     * @Description : 현재 날짜를 포맷에 맞게 String으로 리턴
     *********************************************************
     */

    public static String getNowDate(String format) throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        String newString = dateFormat.format(date);

        return newString;
    }
    /**
     *********************************************************
     * @Name: getTimeDiffBySec
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-16
     * @Author : Taehyun Kim
     * @Description : 현재 시간으로 부터 차이를 초단위로 리턴
     *현재보다 과거면 양수 미래면 음수로 표현
     *********************************************************
     */

    public static long getTimeDiffBySec(String value, String format) throws Exception {
        /*logger.debug(value + " , " + format);*/
        Date inputDate = stringToDate(value,format);
        Date nowDate = new Date();
        /*logger.info("input date : " + inputDate);
        logger.debug("input date : " + inputDate.getTime());
        logger.debug("now date : " + nowDate);
        logger.debug("now date : " + nowDate.getTime());*/
        long timeDiff = nowDate.getTime() - inputDate.getTime();
        /*logger.debug("time diff : " + timeDiff);*/
        long sec = timeDiff/1000;
        /*logger.debug("sec : " + sec);
        logger.debug("Diff Day : " + sec/3600/24);*/
        return sec;
    }
    /**
     *********************************************************
     * @Name: getTimeDiffByHour
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-16
     * @Author : Taehyun Kim
     * @Parameter : String value, String format
     * @Description : 현재 시간으로 부터 차이를 시간단위로 리턴
     * 현재보다 과거면 양수 미래면 음수로 표현
     *********************************************************
     */

    public static long getTimeDiffByHour(String value, String format) throws Exception {
        return getTimeDiffBySec(value, format)/3600;
    }
    /**
     *********************************************************
     * @Name: getTimeDiffByDay
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-16
     * @Author : Taehyun Kim
     * @Parameter : String value, String format
     * @Description : 현재 시간으로 부터 차이를 일단위로 리턴
     * 현재보다 과거면 양수 미래면 음수로 표현
     *********************************************************
     */

    public static long getTimeDiffByDay(String value, String format) throws Exception {

        return getTimeDiffBySec(value, format)/3600/24;
    }
}
