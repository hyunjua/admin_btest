package ai.cubox.admin_ftest.admin.service.impl;

import ai.cubox.admin_ftest.admin.controller.CommonController;
import ai.cubox.admin_ftest.admin.mapper.CommonMapper;
import ai.cubox.admin_ftest.admin.service.UserService;
import ai.cubox.admin_ftest.admin.vo.CustomUserDetails;
import ai.cubox.admin_ftest.admin.vo.LoginVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 스프링시큐리티 내부적으로 UsernamePasswordAuthenticationFilter가 동작, AuthenticationManager -> AuthenticationProVider에 의해 UserDetailsService를 implement한
        // UserDetailService.loadUserByUsername 이동
        LOGGER.debug("====================loadUserByUsername=======================");
        LoginVO loginVO = new LoginVO();
        loginVO.setUserId(username);
        LoginVO resultVo = commonMapper.selectUser(loginVO);
        if (resultVo != null) {
            CustomUserDetails customUserDetails = new CustomUserDetails();
            customUserDetails.setUsername(resultVo.getUserId());
            customUserDetails.setPassword(resultVo.getUserPw());

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return resultVo.getRole();
                }
            });
            customUserDetails.setAuthorities(authorities);
            customUserDetails.setEnabled(true);
            customUserDetails.setAccountNonExpired(true);
            customUserDetails.setAccountNonLocked(true);
            if(resultVo.getPwUpdtDays() > 90) {
                customUserDetails.setCredentialsNonExpired(false);
            } else {
                customUserDetails.setCredentialsNonExpired(true);
            }
            return customUserDetails;
            // AuthenticationProVider - DaoAuthenticationProvider
            // UserDetailsService는 DB에서 가져온 사용자 정보 (UserDetails)를 사용하여 비밀번호 일치 여부를 확인하여 사용자를 인증
            /*if (!this.passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
                this.logger.debug("Failed to authenticate since password does not match stored value");
                throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
            }*/
        }
        return null;
    }
}
