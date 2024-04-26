package ai.cubox.admin_ftest.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    List<Map<String,Object>> getBoardList(Map<String,Object> map);

    int insertBoard(Map<String,Object> map);

    int updateBoard(Map<String,Object> map);

    int deleteBoard(Map<String,Object> map);

    Map<String,Object> selectBoardDetail(Map<String,Object> map);
}
