package com.hyunro.practice.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    // MyBatis등에서 Dao라고 불리는 DB Layer접근자
    // JPA에서는 Repository라고 부르며 인터페이스로 생성
    // 인터페이스 생성 후, JpaRepository<Entity클래스, PK타입>를 상속하면,
    // 기본 CRUD메소드가 자동으로 생성
    // @Repository를 추가할 필요도 없다.
    // 단 EntityClass와 함께 위치해야 한다.


    // SpringDataJpa에서 제공하지 않는 메소드는 이처럼 쿼리로 작성해도 된다.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
