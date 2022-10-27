package ua.lviv.iot.jdbc.dao;

import java.util.List;
import java.util.Optional;
import ua.lviv.iot.jdbc.domain.Exercise;
import ua.lviv.iot.jdbc.domain.projection.ExerciseProjection;

public interface ExerciseDao extends GeneralDao<Exercise> {
    Optional<List<Exercise>> findByTypeOfMuscleLoadOn(String typeOfMuscleLoadOn);
    Optional<List<ExerciseProjection>> findByPersonalExerciseMachineId(Integer personalExerciseMachineId);
}
