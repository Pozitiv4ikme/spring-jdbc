package ua.lviv.iot.jdbc.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lviv.iot.jdbc.dao.ExerciseDao;
import ua.lviv.iot.jdbc.domain.Exercise;
import ua.lviv.iot.jdbc.domain.projection.ExerciseProjection;
import ua.lviv.iot.jdbc.service.ExerciseService;

@Service
@AllArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseDao exerciseDao;

    @Override
    public Optional<List<Exercise>> findByTypeOfMuscleLoadOn(String typeOfMuscleLoadOn) {
        return exerciseDao.findByTypeOfMuscleLoadOn(typeOfMuscleLoadOn);
    }

    @Override
    public Optional<List<ExerciseProjection>> findByPersonalExerciseMachineId(Integer personalExerciseMachineId) {
        return exerciseDao.findByPersonalExerciseMachineId(personalExerciseMachineId);
    }

    @Override
    public List<Exercise> findALl() {
        return exerciseDao.findALl();
    }

    @Override
    public Optional<Exercise> findById(Integer id) {
        return exerciseDao.findById(id);
    }

    @Override
    public Exercise create(Exercise entity) {
        return exerciseDao.create(entity);
    }

    @Override
    public Exercise update(Exercise entity, Integer id) {
        return exerciseDao.update(entity, id);
    }

    @Override
    public int delete(Integer id) {
        return exerciseDao.delete(id);
    }
}
