package com.hyunro.practice.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 롬복 Annotation 선택(코드간결화) // 클래스 내 모든 필드의 Getter메소드 자동 생성
@NoArgsConstructor // 롬복 Annotation 선택(코드간결화) // 기본 생성자 자동 추가, public Post(){}와 같은 효과
@Entity // JPA Annotation 필수,테이블과 링크될 클래스임을 표시. 흔히 카멜표기법을 소문자_소문자_소문자 식으로 네이밍
public class Posts { // 실제 DB의 테이블과 매칭될 클래스, Entity클래스 라고도 함
    // JPA를 사용할 경우, DB 데이터에 작업할 경우, 실제 쿼리를 날리기 보다 이 Entity클래스의 수정을 통해 작업

    @Id // 해당 테이블의 PK필드라는 표시
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK생성 규칙을 나타낸다. GenerationType.IDENTITY하면 Auto-Increment
    private Long id;

    @Column(length = 500, nullable = false) // 굳이 선언하지 않아도 됨, 기본값 외에 추가 변경 옵션이 있을 경우 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스 생성 // 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // Entity클래스에서는 절대 Setter 메소드를 만들지 않는다.
    // 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가.

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
