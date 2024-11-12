package net.proselyte.webfluxsecurity.rest;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.proselyte.webfluxsecurity.entity.QuestionList;
import net.proselyte.webfluxsecurity.entity.UserEntity;
import net.proselyte.webfluxsecurity.entity.UserRole;
import net.proselyte.webfluxsecurity.exception.AuthException;
import net.proselyte.webfluxsecurity.exception.UnauthorizedException;
import net.proselyte.webfluxsecurity.security.CustomPrincipal;
import net.proselyte.webfluxsecurity.service.QuestionService;
import net.proselyte.webfluxsecurity.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/question")
public class QuestionController {

    private final QuestionService questionService;
    private final UserService userService;

    @PostConstruct
    void init() {
        System.out.println(questionService);
    }

    @PostMapping("/add")
    public Mono<QuestionList> addQuestion(@RequestBody QuestionList questionList, Authentication authentication) {
        CustomPrincipal principal = (CustomPrincipal) authentication.getPrincipal();
        checkAdmin(userService.getUserById(principal.getId()));
        return questionService.addQuestionList(questionList);
    }

    public Mono<UserEntity> checkAdmin(Mono<UserEntity> userMono) {
        return userMono.flatMap(user -> {
            if (user.getRole().equals(UserRole.ADMIN)) {
                return Mono.just(user);
            } else {
                return Mono.error(new AuthException("User does not have ADMIN role", "ACCESS_DENIED"));
            }
        });
    }

}
