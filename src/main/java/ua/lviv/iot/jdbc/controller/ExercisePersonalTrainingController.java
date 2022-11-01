package ua.lviv.iot.jdbc.controller;

import java.util.List;
import java.util.Optional;
import ua.lviv.iot.jdbc.domain.ExercisePersonalTraining;
import ua.lviv.iot.jdbc.domain.projection.ExercisePersonalTrainingProjection;

public interface ExercisePersonalTrainingController {
    List<ExercisePersonalTraining> findALl();
    ExercisePersonalTraining create(ExercisePersonalTraining entity);
    public int deleteExerciseForPersonalTraining(Integer exerciseId, Integer personalTrainingId);
    Optional<List<ExercisePersonalTrainingProjection>> findByPersonalTrainingId(Integer personalTrainingId);
}
