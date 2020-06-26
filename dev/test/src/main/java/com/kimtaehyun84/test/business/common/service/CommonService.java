package com.kimtaehyun84.test.business.common.service;

import java.util.HashMap;

public interface CommonService  {
    public HashMap<String,String> getServerConfig(String customerType) throws Exception;
}
