package ua.lviv.iot.jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exercise {
    private Integer id;
    private Integer numberOfRepetitions;
    private Integer approach;
    private Integer complexity;
    private Integer personalExerciseMachineId;
    private String typeOfMuscleLoadOn;
}
