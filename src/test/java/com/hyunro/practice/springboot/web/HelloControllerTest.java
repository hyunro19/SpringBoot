package com.hyunro.practice.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) // 테스트 진행시 JUnit에 내장된 실행자 외에 다른 실행자 실행. 여기서는 SpringRunner. 즉, 스프링부트 테스트와 JUnit사이에 연결자.
@WebMvcTest
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc; // 웹API를 테스트할 때 사용. 스프링MVC테스트의 시작점. 이 클래스를 통해 HTTP GET, POST등에 대한 테스트 수행

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc를 통해 "/hello"주소로 HTTP GET 요청
                .andExpect(status().isOk()) // Chaining을 통한 mvc.perform의 결과 검증. response status검증.
                .andExpect(content().string(hello)); // 본문 내용 검증.
    }

    @Test
    public void helloDTO가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name) // API를 테스트할 때 사용될 요청 파라미터를 설정(값은 String만 허용. 숫자 등도 문자열로 변경해야)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // JSON응답값을 필드별로 검증할 수 있는 메소드. $를 기준으로 필드명을 명시
                .andExpect(jsonPath("$.amount", is(amount)));
    }

    
}




