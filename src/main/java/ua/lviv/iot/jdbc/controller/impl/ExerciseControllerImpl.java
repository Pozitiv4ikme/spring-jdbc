package ua.lviv.iot.jdbc.controller.impl;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import ua.lviv.iot.jdbc.controller.ExerciseController;
import ua.lviv.iot.jdbc.domain.Exercise;
import ua.lviv.iot.jdbc.domain.projection.ExerciseProjection;
import ua.lviv.iot.jdbc.service.ExerciseService;

@Controller
@AllArgsConstructor
public class ExerciseControllerImpl implements ExerciseController {

    private final ExerciseService exerciseService;

    @Override
    public Optional<List<Exercise>> findByTypeOfMuscleLoadOn(String typeOfMuscleLoadOn) {
        return exerciseService.findByTypeOfMuscleLoadOn(typeOfMuscleLoadOn);
    }

    @Override
    public Optional<List<ExerciseProjection>> findByPersonalExerciseMachineId(Integer personalExerciseMachineId) {
        return exerciseService.findByPersonalExerciseMachineId(personalExerciseMachineId);
    }

    @Override
    public List<Exercise> findALl() {
        return exerciseService.findALl();
    }

    @Override
    public Optional<Exercise> findById(Integer id) {
        return exerciseService.findById(id);
    }

    @Override
    public Exercise create(Exercise entity) {
        return exerciseService.create(entity);
    }

    @Override
    public Exercise update(Exercise entity, Integer id) {
        return exerciseService.update(entity, id);
    }

    @Override
    public int delete(Integer id) {
        return exerciseService.delete(id);
    }
}
