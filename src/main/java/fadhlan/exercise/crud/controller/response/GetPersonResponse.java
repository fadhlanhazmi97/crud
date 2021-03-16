package fadhlan.exercise.crud.controller.response;

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
public class GetPersonResponse {
    private int id;
    private String name;
    private int age;
    private String hobby;
    private String gender;
}
