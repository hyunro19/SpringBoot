package com.hyunro.practice.springboot.web.dto;

import com.hyunro.practice.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

    // Entity클래스와 유사한 형태임에도 Dto클래스를 추가 생성
    // 절대로 Entity클래스를 Request/Response클래스로 사용해서는 안된다.
    // Entity 클래스는 테이블 생성, 스키마 변경과 직결

    // Dto는 View를 위한 클래스로 자주 변경됨.
}
