package com.hyunro.practice.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileController {
    private final Environment env;

    @GetMapping("/profile")
    public String profile() {
        // env.getActiveProfiles()
        // 현재 실행 중인 ActiveProfile을 모두 가져온다.
        // 즉 real, oauth, real-db등이 활성화되어 있다면(active) 3개가 모두 담긴다.
        // 여기서 real, real1, real2는 모두 배포에 사용될 profile이라 이 중 하나라도 있으면 그 값을 반환하도록 한다.
        // real은 setp2용(CI&CD), 나머지(real1, real2)는 step3용(무중단배포)
        List<String> profiles = Arrays.asList(env.getActiveProfiles());
        List<String> realProfiles = Arrays.asList("real", "real1", "real2");
        String defaultProfile = profiles.isEmpty()? "default" : profiles.get(0);

        return profiles.stream()
                .filter(realProfiles::contains)
                .findAny()
                .orElse(defaultProfile);
    }
}
