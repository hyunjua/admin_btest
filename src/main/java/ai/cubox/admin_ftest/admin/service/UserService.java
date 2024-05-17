package ai.cubox.admin_ftest.admin.service;

import ai.cubox.admin_ftest.admin.vo.LoginVO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Map;

public interface UserService {

    public int updatePwd(Map<String, Object> param) throws Exception;

}
