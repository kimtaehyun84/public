package com.kimtaehyun84.test.system.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;

public class BeanUtils {
    private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);
    public static HashMap<String, String> beanToHashMap(Object bean){

        Class<?> cls = bean.getClass();
        HashMap<String,String> resultMap = new HashMap<>();
        for(Field field : cls.getDeclaredFields()){
            field.setAccessible(true);

            Object value = null;
            String key;

            try {
                value = field.get(bean);
            } catch (IllegalArgumentException e) {
                logger.error("Exception position : CollectionsUtil - putValues(Object bean, Map<String, Object> map, String prefix)");
            } catch (IllegalAccessException e) {
                logger.error("Exception position : CollectionsUtil - putValues(Object bean, Map<String, Object> map, String prefix)");
            }

            key = field.getName();
            if(isNull(value)){
                resultMap.put(key,"");
            }
            else{
                resultMap.put(key,value.toString());
            }
        }
        return resultMap;
    }

    public static boolean isNull(Object object) {
        return object == null || object.toString().trim().length() == 0;
    }
}
