package com.common.system.service;

import java.util.Date;

/**
 * @Package : com.hyosung.common.system.utils.service
 * @FileName : UtilService
 * @Version : 1.0
 * @Date : 2019-04-16
 * @Author : Taehyun Kim
 * @Description : UtilService의 인터페이스
 */
public interface UtilService {

    public Date stringToDate(String value, String format) throws Exception;
    public String dateToString(Date date, String format) throws Exception;
    public String getNowDate(String format) throws Exception;
    public long getTimeDiffBySec(String value, String format) throws Exception;
    public long getTimeDiffByHour(String value, String format) throws Exception;
    public long getTimeDiffByDay(String value, String format) throws Exception;

}
