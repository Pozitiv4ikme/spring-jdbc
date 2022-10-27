package ua.lviv.iot.jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalExerciseMachine {
    private Integer id;
    private String type;
    private Integer clientWeight;
    private Integer clientHeight;
    private Integer clientShoulderWidth;
    private Integer clientLegLength;
    private Integer clientAmountOfFatInBody;
    private String clientStateOfHealth;
}
