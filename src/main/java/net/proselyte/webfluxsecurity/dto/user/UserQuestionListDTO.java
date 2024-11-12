package net.proselyte.webfluxsecurity.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.proselyte.webfluxsecurity.dto.QuestionDTO;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserQuestionListDTO {

    private List<QuestionDTO> questions;

}
