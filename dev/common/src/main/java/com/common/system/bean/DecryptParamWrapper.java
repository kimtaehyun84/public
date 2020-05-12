package com.common.system.bean;


import com.common.business.common.bean.Config;
import com.common.business.common.bean.Globals;
import com.common.system.utils.SecurityUtils;
import com.common.system.utils.StringUtils;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static org.springframework.util.StringUtils.*;

public class DecryptParamWrapper extends HttpServletRequestWrapper {

    private final Charset encoding;

    private String body = null;
    private String bodyString = null;
    Config config = new Config();


    public DecryptParamWrapper(HttpServletRequest request) throws Exception {
        /**
         * @Name: DecryptParamWrapper
         * @Type : Function
         * @Version : 1.0
         * @Date : 2019-12-18
         * @Author : Taehyun Kim
         * @Param : [request]
         * @Return :
         * @Description : request parameter의 암호화된 값을 복호화
         * ========================================================================
         *  Date              ||  Name              ||  Descripton
         *  2019-12-18       ||  taehyun.kim       ||  신규 생성
         * ========================================================================
         */
        super(request);

        Logger logger = LoggerFactory.getLogger(this.getClass());

        String characterEncoding = request.getCharacterEncoding();
        if (isEmpty(characterEncoding)) {
            characterEncoding = StandardCharsets.UTF_8.name();
        }
        this.encoding = Charset.forName(characterEncoding);

        StringBuilder stringBuilder = new StringBuilder();

        BufferedReader bufferedReader = null;

        try {

            InputStream inputStream = request.getInputStream();

            if (inputStream != null) {

                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }

            } else {
                stringBuilder.append("");
            }
            bodyString = stringBuilder.toString();


            if (bodyString != null && !"".equals(bodyString)) {
                logger.debug("Parameter : " + bodyString);
                body = SecurityUtils.decryptRsa(bodyString);
                logger.debug("Decrypted Param : " + body);
            } else {
                body = bodyString;
            }


        } catch (Exception ex) {
            logger.error("Exception in DecryptParamFilter " + ex);


        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    logger.error("Exception in DecryptParamFilter " + ex);
                }
            }
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());

        ServletInputStream servletInputStream = new ServletInputStream() {

            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
        return servletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        return this.body;
    }
}
