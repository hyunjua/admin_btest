package ai.cubox.admin_ftest.admin.service.impl;

import ai.cubox.admin_ftest.admin.mapper.BoardMapper;
import ai.cubox.admin_ftest.admin.mapper.CommonMapper;
import ai.cubox.admin_ftest.admin.service.BoardService;
import ai.cubox.admin_ftest.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserSerivceImpl implements UserService {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public int updatePwd(Map<String, Object> param) throws Exception {
        return commonMapper.updatePwd(param);
    }
}
