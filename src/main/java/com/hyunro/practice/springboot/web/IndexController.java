package com.hyunro.practice.springboot.web;

import com.hyunro.practice.springboot.service.posts.PostsService;
import com.hyunro.practice.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/") // 기본으로 index를 리턴
    public String index(Model model) {
        // Model 서버 템블릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
        model.addAttribute("posts", postsService.findAllDesc());

        return "index";
        // MUSTACHE STARTER가 스트링을 리턴하면 경로/리턴값.mustache 를 자동으로 연결해준다.
        // src/main/resources/templates/index.mustache로 변환하여 ViewResolver가 처리
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
