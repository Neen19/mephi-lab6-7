package net.proselyte.webfluxsecurity.repository;

import net.proselyte.webfluxsecurity.entity.Question;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface QuestionRepository extends R2dbcRepository<Question, Long> {

    @Query("SELECT * FROM questions WHERE id IN (:ids)")
    Flux<Question> findAllByIds(List<Long> ids);

    Flux<Question> findByListId(Long listId);

}
