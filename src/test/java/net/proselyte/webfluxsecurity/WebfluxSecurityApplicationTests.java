package net.proselyte.webfluxsecurity;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import net.proselyte.webfluxsecurity.dto.QuestionDTO;
import net.proselyte.webfluxsecurity.dto.QuestionListDTO;
import net.proselyte.webfluxsecurity.repository.AnswerRepository;
import net.proselyte.webfluxsecurity.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.function.Supplier;

@Slf4j
@SpringBootTest
class WebfluxSecurityApplicationTests {

    Logger logger = LoggerFactory.getLogger(WebfluxSecurityApplicationTests.class);

    @Autowired
    private QuestionService service;

    @Autowired
    private AnswerRepository repository;

    @Test
    void contextLoads() throws JsonProcessingException {

        QuestionListDTO list = QuestionListDTO.builder()
                .questionList(
                        List.of(
                                QuestionDTO.builder()
                                        .question("question")
                                        .variants(List.of("variant"))
                                        .answers(List.of("answer"))
                                        .build()
                        )
                )
                .build();

        service.addQuestionList(list);

    }

    @Test
    void test() throws InterruptedException {

        System.out.println("МОЙ ЛОГ ДОАЫТДОЫТПДОАТДЛПАТДЛВЫТАЛЖВЫТЖЛАТВЖФЛЫТАМЛЖДВЫТЛДТМАДЛЫВТДЛТВЫДАТДЫОЛВА");

        repository.findById(1L).subscribe(
                it -> {
                    System.out.println(it.getAnswer());
                }
        );

        Thread.sleep(5000L);
    }
}
