package net.proselyte.webfluxsecurity.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import net.proselyte.webfluxsecurity.entity.Question;
import net.proselyte.webfluxsecurity.entity.QuestionList;
import net.proselyte.webfluxsecurity.repository.QuestionListRepository;
import net.proselyte.webfluxsecurity.repository.QuestionRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@AllArgsConstructor
public class QuestionService {

    @PostConstruct
    void init() {
        System.out.println("QuestionService");
    }

    private final QuestionRepository questionRepository;
    private final QuestionListRepository questionListRepository;

    public Mono<QuestionList> addQuestionList(QuestionList questionList) {
        return questionListRepository.save(questionList)
                .flatMap(savedList -> {
                    List<Question> questions = questionList.getQuestions();
                    if (questions != null) {
                        questions.forEach(question -> question.setListId(savedList.getId()));
                        return questionRepository.saveAll(questions).collectList()
                                .map(savedQuestions -> {
                                    savedList.setQuestions(savedQuestions);
                                    return savedList;
                                });
                    }
                    return Mono.just(savedList);
                });
    }

}

