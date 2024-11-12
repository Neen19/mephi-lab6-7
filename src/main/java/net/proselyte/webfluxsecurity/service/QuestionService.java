package net.proselyte.webfluxsecurity.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import net.proselyte.webfluxsecurity.dto.QuestionDTO;
import net.proselyte.webfluxsecurity.dto.QuestionListDTO;
import net.proselyte.webfluxsecurity.entity.Answer;
import net.proselyte.webfluxsecurity.entity.Question;
import net.proselyte.webfluxsecurity.entity.QuestionList;
import net.proselyte.webfluxsecurity.repository.AnswerRepository;
import net.proselyte.webfluxsecurity.repository.QuestionListRepository;
import net.proselyte.webfluxsecurity.repository.QuestionRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;


@Component
@AllArgsConstructor
public class QuestionService {


    private final QuestionRepository questionRepository;
    private final QuestionListRepository questionListRepository;

    private final AnswerRepository answerRepository;

    private Mono<Answer> saveAnswer(String answer, Long questionId) {

        Answer entity = Answer.builder()
                .answer(answer)
                .questionId(questionId)
                .build();
        return answerRepository.save(entity);
    }

    private Mono<Question> saveQuestion(QuestionDTO dto, Long listId) {
        Question question = Question.builder()
                .question(dto.getQuestion())
                .listId(listId)
                .build();
        return questionRepository.save(question).
                flatMap(savedQuestion -> {
                    dto.getAnswers().forEach(it -> saveAnswer(it, savedQuestion.getId()));
                    return Mono.just(savedQuestion);
                });
    }

    @Transactional
    public Mono<QuestionListDTO> addQuestionList(QuestionListDTO questionList) {


        questionListRepository.save(new QuestionList())
                .flatMap(savedList -> {
                    questionList.getQuestionList().forEach(it -> saveQuestion(it, savedList.getId()));
                    return Mono.just(savedList);
                });


        return Mono.just(questionList);

//        return questionListRepository.save(new QuestionList())
//                .flatMap(savedList -> {
//                    List<Question> questions = questionList.getQuestions();
//                    if (questions != null) {
//                        questions.forEach(question -> question.setListId(savedList.getId()));
//                        return questionRepository.saveAll(questions).collectList()
//                                .map(savedQuestions -> {
//                                    savedList.setQuestions(savedQuestions);
//                                    return savedList;
//                                });
//                    }
//                    return Mono.just(savedList);
//                });
    }

}

