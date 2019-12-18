package com.common.business.user.service;

import com.common.business.user.vo.UserPwdPolicyVO;

public interface UserService {

    UserPwdPolicyVO getUserPwdPolicy() throws Exception;
}
