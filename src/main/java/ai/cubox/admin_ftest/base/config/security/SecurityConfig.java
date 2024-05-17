package ai.cubox.admin_ftest.base.config.security;

import ai.cubox.admin_ftest.base.config.handler.CustomAuthenticationFailureHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired
  private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (webSecurity -> webSecurity.ignoring()
            .requestMatchers("/static/**", "/assets/**")
    );
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

    httpSecurity
        .authorizeHttpRequests(authz -> authz // 특정한 경로에 어떠한 작업을 하고싶은지 설정
                //.requestMatchers(HttpMethod.OPTIONS, "*").permitAll()
                //.requestMatchers("/static/**", "/assets/**").permitAll() // 정적파일 모두 접근가능
                .requestMatchers("/login.do","/updatePwd.do","/deleteSession.do").permitAll() // 해당 주소는 모두 접근가능하도록 설정
                .requestMatchers("/admin/**").hasRole("ADMIN") // 해당주소는 admin 권한을 가진 경우에만 허용
                .requestMatchers("/my/**").hasAnyRole("ADMIN","USER") 
                .anyRequest().authenticated() // 그외 나머지 경로에는 로그인 사용자만 접근가능
        )
    ;

    httpSecurity.formLogin(form -> form.loginPage("/login.do") // 폼로그인방식사용, 해당방식 주소 가진 로그인페이지 이동
            .loginProcessingUrl("/loginProc.do") // 폼로그인 이동 경로
            .defaultSuccessUrl("/board/bList.do")  // 로그인성공시 이동경로
            .failureHandler(customAuthenticationFailureHandler) // 로그인 실패시 핸들러
            .usernameParameter("userId")
            .passwordParameter("userPw")
            .permitAll()
    );

    httpSecurity.csrf(auth -> auth.disable()); // csrf 공격 방지

    httpSecurity.logout(logout -> logout
            .logoutUrl("/logout.do")
            .logoutSuccessUrl("/login.do")  // 로그아웃 성공 시 이동할 URL 설정
            .invalidateHttpSession(true) // 로그아웃 이후에 세션 삭제 여부
            .deleteCookies("JSESSIONID") // 로그아웃 시 삭제할 쿠키 설정
            .permitAll()  // 로그아웃 페이지는 모든 사용자에게 허용

    );

    return httpSecurity.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() { // 암호화진행
    return new BCryptPasswordEncoder();
  }


}
