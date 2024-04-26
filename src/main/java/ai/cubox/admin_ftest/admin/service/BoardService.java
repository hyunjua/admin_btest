package ai.cubox.admin_ftest.admin.service;

import java.util.List;
import java.util.Map;

public interface BoardService {

    public List<Map<String,Object>> getBoardList(Map<String,Object> map) throws Exception;

    public int insertBoard(Map<String,Object> map) throws Exception;

    public int updateBoard(Map<String,Object> map) throws Exception;

    public int deleteBoard(Map<String,Object> map) throws Exception;

    public Map<String,Object> selectBoardDetail(Map<String,Object> map) throws Exception;
}
