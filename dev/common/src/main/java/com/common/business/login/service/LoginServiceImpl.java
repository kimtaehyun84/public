package com.hyosung.common.business.login.service;

import com.hyosung.common.business.common.bean.Globals;
import com.hyosung.common.business.common.service.CommonService;
import com.hyosung.common.business.common.vo.ResponseResultVO;
import com.hyosung.common.business.login.dao.LoginDAO;
import com.hyosung.common.business.session.service.SessionService;
import com.hyosung.common.system.utils.service.SecurityService;
import com.hyosung.common.system.utils.service.UtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
/**
********************************************************
    *@Package : com.hyosung.common.business.login.service
    *@FileName : LoginServiceImpl
    *@Version :
    *@Date : 2019-04-16
    *@Author : Taehyun Kim
    *@Description : LoginService 의 Implements
********************************************************
*/

@Service("loginService")
public class LoginServiceImpl implements LoginService{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name="loginDAO")
    private LoginDAO loginDAO;

    @Resource(name="securityService")
    private SecurityService securityService;

    @Resource(name="utilService")
    private UtilService utilService;

    @Resource(name="commonService")
    private CommonService commonService;

    /**
     * @Name: checkUser
     * @Type : Function
     * @Version : 1.0
     * @Date : 2019-04-08
     * @Author : Taehyun Kim
     * @Description : 로그인시 User의 정보를 체크한다.
     */



    @Override
    public ResponseResultVO checkUser(HashMap<String, Object> inputParam) throws Exception {

        logger.debug("checkUser start");
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        HashMap<String, Object> newInputParam = new HashMap<String, Object>();
        ResponseResultVO responseResult = new ResponseResultVO();

        userInfo = loginDAO.selectUserInfo(inputParam);

        if (userInfo.size() > 0) { //user 정보가 있는 경우
            logger.debug(userInfo.toString());
            String userNo = userInfo.get("USER_NO").toString();
            String salt = userInfo.get("SALT").toString();

            logger.debug("SHA256 Encrypt");
            String userPwd = securityService.encryptSha256Repeat(inputParam.get("userPwd").toString(), salt, Globals.SHA_REPEAT_COUNT);
            logger.debug("SHA256 Encrypt Password : " + userPwd);

            int failedCount = Integer.parseInt(userInfo.get("FAILED_COUNT").toString());
            String locked = userInfo.get("LOCKED").toString();
            String passwordModifyDate = userInfo.get("PASSWORD_MODIFY_DATE").toString();
            boolean passwordExpired = utilService.getTimeDiffByDay(passwordModifyDate, "yyyy-MM-dd") >  0 ? true : false;


            logger.info("User Info exist");
            if (locked.equals("Y")) {
                logger.info("ID has locked");
                responseResult.setStatus(Globals.RESULT_FAIL);
                responseResult.setMsg(Globals.ERROR_MSG_LOGIN_LOCK);

            } else {
                // password 일치하는 경우
                if (userInfo.get("USER_PWD").equals(userPwd)) {
                    if(passwordExpired){
                        responseResult.setStatus(Globals.RESULT_FAIL);
                        responseResult.setMsg(Globals.ERROR_MSG_PWD_EXPIRED);
                        logger.info("Password has Expired");
                    }
                    else{
                        responseResult.setStatus(Globals.RESULT_OK);
                        responseResult.setMsg(Globals.OK_MSG);
                        responseResult.setBody(userInfo);
                        newInputParam.put("userNo",userNo);
                        newInputParam.put("failedCount", 0);
                        newInputParam.put("locked", locked);
                        newInputParam.put("lastLoginDate", "SUCCESS");
                        logger.debug(newInputParam.toString());
                        loginDAO.updateUserFailedCount(newInputParam);
                        logger.info("login success");
                        commonService.insertAccessLog(userNo,"Login");
                    }

                } else {
                    //password 불일치 하는 경우
                    if (failedCount <= 5) {
                        failedCount++;
                        responseResult.setStatus(Globals.RESULT_FAIL);
                        responseResult.setMsg(Globals.ERROR_MSG_PWD_WRONG);
                        logger.info("password is wrong. failed count : " + failedCount);
                        commonService.insertAccessLog(userNo,"Login Fail, Password Wrong");
                    } else {
                        failedCount++;
                        locked = "Y";
                        responseResult.setStatus(Globals.RESULT_FAIL);
                        responseResult.setMsg(Globals.ERROR_MSG_LOGIN_LOCK);
                        logger.info("password is wrong. User ID has locked");
                        commonService.insertAccessLog(userNo,"Login Fail, User ID has locked");
                    }
                    logger.info("Update failed Count and locked");
                    newInputParam.put("uesrNo",userNo);
                    newInputParam.put("failedCount", failedCount);
                    newInputParam.put("locked", locked);
                    logger.debug(newInputParam.toString());
                    loginDAO.updateUserFailedCount(newInputParam);
                }
            }
        } else {
            logger.info("User Info not exist");
            responseResult.setStatus(Globals.RESULT_FAIL);
            responseResult.setMsg("User ID not exist");
        }
        logger.debug("checkUser end");
        return responseResult;
    }
}
