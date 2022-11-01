package ua.lviv.iot.jdbc.controller;

import java.util.List;
import java.util.Optional;
import ua.lviv.iot.jdbc.domain.Exercise;
import ua.lviv.iot.jdbc.domain.projection.ExerciseProjection;

public interface ExerciseController extends GeneralController<Exercise> {
    Optional<List<Exercise>> findByTypeOfMuscleLoadOn(String typeOfMuscleLoadOn);
    Optional<List<ExerciseProjection>> findByPersonalExerciseMachineId(Integer personalExerciseMachineId);
}
