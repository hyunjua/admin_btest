package ai.cubox.admin_ftest.admin.controller;

import ai.cubox.admin_ftest.admin.service.BoardService;
import ai.cubox.admin_ftest.admin.vo.BoardVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private BoardService boardService;

    @GetMapping(value = "/bList.do")
    public String getBoardList(Model model, @RequestParam(value="searchWord", required=false) String searchWord) throws Exception {
        logger.debug("======== getBoardList start ======== {}",searchWord);
        Map<String,Object> inParam = new HashMap<>();
        inParam.put("keyword",searchWord);
        List<Map<String,Object>> boardList = boardService.getBoardList(inParam);
        model.addAttribute("boardList", boardList);
        model.addAttribute("keyword", searchWord);
        logger.debug("======== getBoardList end ========",boardList);
        return "board/boardList";
    }

    @GetMapping(value = "/bDeatail.do")
    public String selectBoardDetail(@RequestParam(value = "boardId", required = false) String boardId, Model model) throws Exception {
        logger.debug("======== selectBoardDetail start ======== {}",boardId);
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("boardId",boardId);
        Map<String,Object> board = boardService.selectBoardDetail(inParam);
        logger.debug("======== selectBoardDetail end ======== {}",board);
        model.addAttribute("board", board);
        return "board/boardDetail";
    };

    @GetMapping(value = "/write.do")
    public String writeBoard() throws Exception {
        return "board/writeBoard";
    };


    @PostMapping(value = "/insertBoard.do")
    public String insertBoard(Model model, BoardVo boardVo) throws Exception {
        logger.debug("======== insertBoard start ======== {}",boardVo);
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("title",boardVo.getTitle());
        inParam.put("writer",boardVo.getwriter());
        inParam.put("content",boardVo.getContent());
        int result = boardService.insertBoard(inParam);
        return "redirect:/board/bList.do";
    };

    @PostMapping(value = "/updateBoard.do")
    public String updateBoard(Model model, BoardVo boardVo) throws Exception {
        logger.debug("======== updateBoard start ======== {}",boardVo);
        Map<String,Object> board = new HashMap<>();
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("boardId",boardVo.getBoardId());
        inParam.put("title",boardVo.getTitle());
        inParam.put("content",boardVo.getContent());
        int result = boardService.updateBoard(inParam);
        if(result > 0) {
            board = boardService.selectBoardDetail(inParam);
            model.addAttribute("board", board);
        }
        return "board/boardDetail :: #formDiv";
    };

    @PostMapping(value = "/deleteBoard.do")
    public String deleteBoard(BoardVo boardVo) throws Exception {
        logger.debug("======== deleteBoard start ======== {}",boardVo);
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("boardId",boardVo.getBoardId());
        int result = boardService.deleteBoard(inParam);
        return "redirect:/board/bList.do";
    };

}
