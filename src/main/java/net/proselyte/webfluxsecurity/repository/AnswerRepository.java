package net.proselyte.webfluxsecurity.repository;

import net.proselyte.webfluxsecurity.entity.Answer;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends R2dbcRepository<Answer, Long> {
}
