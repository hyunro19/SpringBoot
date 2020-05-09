package com.hyunro.practice.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
    // @Target
    // 이 어노테이션이 생성될 수 있는 위치 지정
    // PARAMETER로 지정했으니, 메소드의 파라미터로 선언되 객체에서만 사용할 수 있다.
    // 이 외에도 클래스 선언문에 쓸 수 있는 TYPE등이 있다.

    // @interface
    // 이 파일을 어노테이션 클래스로 지정
}
