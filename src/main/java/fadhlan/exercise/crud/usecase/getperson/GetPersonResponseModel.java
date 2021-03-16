package fadhlan.exercise.crud.usecase.getperson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPersonResponseModel {
    private int id;
    private String name;
    private int age;
    private String hobby;
    private String gender;
}
