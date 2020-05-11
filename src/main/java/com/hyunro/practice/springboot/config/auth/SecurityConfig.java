package com.hyunro.practice.springboot.config.auth;

import com.hyunro.practice.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // 스프링 시큐리티 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().headers().frameOptions().disable()// h-2console화면을 사용하기 위해 해당 옵션들 disable
                .and()
                // authorizeRequests -> URL별 권한 관리를 설정하는 옵션의 시작점 이 부분이 있어야 antMatchers옵션 사용 가능
                .authorizeRequests()
                // andMatchers는 권한 관리 대상 지정 옵션
                // URL, HTTP메소드별로 관리 가능
                // "/"등 지정돤 URL들은 permitAll()옵션을 통해 전체 열람 권한 부여
                // POST메소드이면서 "/api/v1/**"주소를 가진 API는 USER권한을 가진 사람만 가능
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                // anyRequest는 설정되 값 외 나머지 -> 인증된 사용자에게만
                .anyRequest().authenticated()
                .and()
                .logout().logoutSuccessUrl("/") // 로그아웃 기능에 대한 설정 진입점, 로그아웃 성공시 / 주소로 이동
                .and()
                .oauth2Login() // OAuth2로그인 기능에 대한 여러 설정의 진입점
                .userInfoEndpoint() // OAuth2로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                // userService -> 소셜 로그인 성공 시 후속조치를 진행할 UserService인터페이스의 구현체 등록
                // 리소스 서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시
                .userService(customOAuth2UserService);
    }

}
