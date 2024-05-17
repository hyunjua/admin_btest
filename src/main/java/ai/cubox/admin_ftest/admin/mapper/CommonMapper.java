package ai.cubox.admin_ftest.admin.mapper;

import ai.cubox.admin_ftest.admin.vo.LoginVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommonMapper {

    LoginVO selectUser(LoginVO loginVO);

    int updatePwd(Map<String,Object> param);

}
