package ua.lviv.iot.jdbc.domain.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExercisePersonalTrainingProjection {
    private Integer personalTrainingId;
    private Integer exerciseId;
    private String machineType;
    private String typeOfMuscleLoadOn;
    private Integer complexity;
}
