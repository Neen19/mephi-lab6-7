package net.proselyte.webfluxsecurity.mapper;

import net.proselyte.webfluxsecurity.dto.QuestionDTO;
import net.proselyte.webfluxsecurity.entity.Question;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionDTO map(Question question);

    Question map(QuestionDTO question);


}
