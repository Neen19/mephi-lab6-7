package net.proselyte.webfluxsecurity.repository;

import net.proselyte.webfluxsecurity.entity.QuestionList;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionListRepository extends R2dbcRepository<QuestionList, Long> {
}
