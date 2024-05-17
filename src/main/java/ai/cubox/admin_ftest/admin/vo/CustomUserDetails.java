package ai.cubox.admin_ftest.admin.vo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import ai.cubox.admin_ftest.admin.vo.LoginVO;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean isEnabled;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private Collection<? extends GrantedAuthority>authorities;


    // 사용자 계정을 필요에 따라 활성화/비활성화하는 메서드들
    // 사용자가 애플리케이션의 리소스에 접근할 수 있도록 권한을 부여하기 위함
    @Override
    public boolean isAccountNonExpired() { // 계정 만료, 권한 부여가 실패해야 하면 false 반환, 성공해야 하면 true 반환
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() { // 계정 잠금
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 자격 증명 만료
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() { // 계정 비활성화
        return isEnabled;
    }

    @Override
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public void setAccountNonExpired(boolean isAccountNonExpired) {
        this.isAccountNonExpired = isAccountNonExpired;
    }

    public void setAccountNonLocked(boolean isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
