package ua.lviv.iot.jdbc.controller.impl;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import ua.lviv.iot.jdbc.controller.ExercisePersonalTrainingController;
import ua.lviv.iot.jdbc.domain.ExercisePersonalTraining;
import ua.lviv.iot.jdbc.domain.projection.ExercisePersonalTrainingProjection;
import ua.lviv.iot.jdbc.service.ExercisePersonalTrainingService;

@Controller
@AllArgsConstructor
public class ExercisePersonalTrainingControllerImpl implements ExercisePersonalTrainingController {

    private final ExercisePersonalTrainingService exercisePersonalTrainingService;

    @Override
    public List<ExercisePersonalTraining> findALl() {
        return exercisePersonalTrainingService.findALl();
    }

    @Override
    public ExercisePersonalTraining create(ExercisePersonalTraining entity) {
        return exercisePersonalTrainingService.create(entity);
    }

    @Override
    public int deleteExerciseForPersonalTraining(Integer exerciseId, Integer personalTrainingId) {
        return exercisePersonalTrainingService.deleteExerciseForPersonalTraining(exerciseId, personalTrainingId);
    }

    @Override
    public Optional<List<ExercisePersonalTrainingProjection>> findByPersonalTrainingId(Integer personalTrainingId) {
        return exercisePersonalTrainingService.findByPersonalTrainingId(personalTrainingId);
    }
}
