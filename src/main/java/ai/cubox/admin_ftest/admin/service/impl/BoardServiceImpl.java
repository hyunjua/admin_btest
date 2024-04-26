package ai.cubox.admin_ftest.admin.service.impl;

import ai.cubox.admin_ftest.admin.mapper.BoardMapper;
import ai.cubox.admin_ftest.admin.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<Map<String, Object>> getBoardList(Map<String,Object> map) {
        return boardMapper.getBoardList(map);
    }

    @Override
    public int insertBoard(Map<String, Object> map) throws Exception {
        return boardMapper.insertBoard(map);
    }

    @Override
    public int updateBoard(Map<String, Object> map) throws Exception {
        return boardMapper.updateBoard(map);
    }

    @Override
    public int deleteBoard(Map<String, Object> map) throws Exception {
        return boardMapper.deleteBoard(map);
    }

    @Override
    public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception {
        return boardMapper.selectBoardDetail(map);
    }

}
