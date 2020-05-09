package com.hyunro.practice.springboot.config.auth.dto;

import com.hyunro.practice.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    // SessionUser에는 인증된 사용자 정보만 필요
    // 그 외 나머지 정보는 필요없으니 name, email, picture만 필드로 선언
    // SessionUser클래스는 직렬화를 구현했다, User클래스도 직렬화를 구현해서 User클래스를 쓰면 되지 않느냐?
    // User클래스는 Entity엔티티 이기 때문에 다른 엔티티와 관계를 맺게 될 가능성이 높음.
    // 그 때 자식 객체들까지 직렬화 대상에 들어가 성능이슈, 부수효과 발생 가능.
    // 그래서 Serializable을 구현한 별도 객체 생성성
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
