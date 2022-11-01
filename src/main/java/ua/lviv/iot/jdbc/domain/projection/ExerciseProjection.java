package ua.lviv.iot.jdbc.domain.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseProjection {
    private Integer id;
    private Integer numberOfRepetitions;
    private Integer approach;
    private Integer complexity;
    private String type;
    private String typeOfMuscleLoadOn;
}
