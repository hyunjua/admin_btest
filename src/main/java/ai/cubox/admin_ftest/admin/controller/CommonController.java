package ai.cubox.admin_ftest.admin.controller;


import ai.cubox.admin_ftest.admin.service.UserService;
import ai.cubox.admin_ftest.admin.vo.CustomUserDetails;
import ai.cubox.admin_ftest.admin.vo.LoginVO;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
//import org.json.simple.JSONObject;
import javax.sql.DataSource;

@Controller
@RequiredArgsConstructor
public class CommonController {

	//@Value("#{property['Globals.main.listCnt']}")
	//@Value("${Globals.main.listCnt}")
	private String gvMainListCnt;

	//@Value("#{property['Globals.passwd.errCnt']}")
	//@Value("${Globals.passwd.errCnt}")
	private int gvPasswdErrCnt;

	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailsService userDetailsService;

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

	@RequestMapping(value="/login.do")
	public String login(ModelMap model) throws Exception {
		return "common/login";
	}

	@RequestMapping(value="/")
	public String root(ModelMap model) throws Exception {
		return "redirect:/board/bList.do";
	}

	@PostMapping("/loginProc.do")
	public String loginProc(Model model, LoginVO loginVO, HttpServletRequest request) throws Exception {
		/*// 권한에 따라 페이지 이동 결정
		if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			// 관리자인 경우
			return "redirect:/board/bList.do"; // 예시 페이지 경로
		} else if (authorities.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			// 일반 사용자인 경우
			return "redirect:/board/bList.do"; // 예시 페이지 경로
		} else {
			// 권한이 없는 경우 등 적절한 처리
			return "redirect:/board/bList.do"; // 접근 거부 페이지로 이동
		}*/
		LOGGER.debug("================loginProc==================");
		return "redirect:/board/bList.do";
	}

	@RequestMapping(value="/main.do")
	public String main(ModelMap model, HttpSession session, @RequestParam Map<String, Object> param) throws Exception {
		return "cubox/common/board";
	}

	@ResponseBody
	@RequestMapping(value="/updatePwd.do")
	public ModelAndView updatePwd(ModelMap model, HttpSession session, @RequestParam Map<String,Object> param) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");

		UserDetails user = userDetailsService.loadUserByUsername(param.get("userId").toString());
		if(user != null) {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			if(passwordEncoder.matches(param.get("curPasswd").toString(), user.getPassword())) {
				param.put("newPasswd",passwordEncoder.encode(param.get("newPasswd").toString()));
				int result = userService.updatePwd(param);
				modelAndView.addObject("status","success");
				modelAndView.addObject("checkPwdError","N");
			} else {
				modelAndView.addObject("status","success");
				modelAndView.addObject("checkPwdError","Y");
			}
		} else {
			modelAndView.addObject("status","error");
		}
		session.invalidate();
		return modelAndView;
	}


	@ResponseBody
	@RequestMapping(value="/deleteSession.do")
	public ResponseEntity<String> deleteSession(ModelMap model, HttpSession session) throws Exception {
		session.invalidate();
		return ResponseEntity.ok("success");
	}


}
