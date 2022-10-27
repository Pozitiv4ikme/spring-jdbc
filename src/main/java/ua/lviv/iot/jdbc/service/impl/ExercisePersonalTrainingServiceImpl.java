package ua.lviv.iot.jdbc.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lviv.iot.jdbc.dao.ExercisePersonalTrainingDao;
import ua.lviv.iot.jdbc.domain.ExercisePersonalTraining;
import ua.lviv.iot.jdbc.domain.projection.ExercisePersonalTrainingProjection;
import ua.lviv.iot.jdbc.service.ExercisePersonalTrainingService;

@Service
@AllArgsConstructor
public class ExercisePersonalTrainingServiceImpl implements ExercisePersonalTrainingService {

    private final ExercisePersonalTrainingDao exercisePersonalTrainingDao;

    @Override
    public List<ExercisePersonalTraining> findALl() {
        return exercisePersonalTrainingDao.findALl();
    }

    @Override
    public ExercisePersonalTraining create(ExercisePersonalTraining entity) {
        return exercisePersonalTrainingDao.create(entity);
    }

    @Override
    public int deleteExerciseForPersonalTraining(Integer exerciseId, Integer personalTrainingId) {
        return exercisePersonalTrainingDao.deleteExerciseForPersonalTraining(exerciseId, personalTrainingId);
    }

    @Override
    public Optional<List<ExercisePersonalTrainingProjection>> findByPersonalTrainingId(Integer personalTrainingId) {
        return exercisePersonalTrainingDao.findByPersonalTrainingId(personalTrainingId);
    }
}
