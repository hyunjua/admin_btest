package ai.cubox.admin_ftest.base.config.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        request.getSession().setAttribute("errorMsg", exception.getMessage());
        request.getSession().setAttribute("errorCode", "1000");
        if (exception instanceof BadCredentialsException) {
            request.getSession().setAttribute("errorMsg", "비밀 번호 에러");
            request.getSession().setAttribute("errorCode", "1001");
        } else if (exception instanceof InternalAuthenticationServiceException) {
            if(exception.getMessage().contains("UserDetailsService returned null")) {
                request.getSession().setAttribute("errorMsg", "해당 ID 가진 사용자 없음");
                request.getSession().setAttribute("errorCode", "1003");
            }
        } else if (exception instanceof CredentialsExpiredException) {
            if(exception.getMessage().contains("자격 증명 유효 기간이 만료")) {
                request.getSession().setAttribute("errorMsg", "비밀번호 변경 90일 초과, 비밀번호를 변경하세요");
                request.getSession().setAttribute("errorCode", "1002");
                request.getSession().setAttribute("userId", request.getParameter("userId"));
            }
        }
        response.sendRedirect(request.getContextPath() + "/login.do");
    }
}
