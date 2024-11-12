package net.proselyte.webfluxsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("questions")
public class Question {

    private Long listId;
    private String question;
    private List<String> variants;
    private List<String> answers;

    public boolean isCorrect(List<String> answers) {
        return answers != null && answers.containsAll(this.answers);
    }

}
