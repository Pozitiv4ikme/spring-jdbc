package ua.lviv.iot.jdbc.dao.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.jdbc.dao.ExercisePersonalTrainingDao;
import ua.lviv.iot.jdbc.domain.ExercisePersonalTraining;
import ua.lviv.iot.jdbc.domain.projection.ExercisePersonalTrainingProjection;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ExercisePersonalTrainingImpl implements ExercisePersonalTrainingDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String CREATE = "INSERT exercise_personal_training(exercise_id, personal_training_id) VALUES"
        + " (?, ?)";
    private static final String FIND_ALL = "SELECT * FROM exercise_personal_training";
    private static final String DELETE_BY_EXERCISE_ID = "DELETE FROM exercise_personal_training WHERE exercise_id=? "
        + "AND personal_training_id=?";
    private static final String FIND_BY_PERSONAL_TRAINING_ID = """
            SELECT personal_training_id, exercise.id as exercise_id, type as machine_type, type_of_muscle_load_on, complexity
            FROM exercise
            JOIN personal_exercise_machine
            ON exercise.personal_exercise_machine_id = personal_exercise_machine.id
            JOIN exercise_personal_training
            ON exercise.id = exercise_personal_training.exercise_id
            WHERE exercise_personal_training.personal_training_id = ?;
        """;


    @Override
    public List<ExercisePersonalTraining> findALl() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(ExercisePersonalTraining.class));
    }

    @Override
    public ExercisePersonalTraining create(ExercisePersonalTraining entity) {
        log.info("There are created rows " + jdbcTemplate.update(CREATE, entity.getExerciseId(),
            entity.getPersonalTrainingId()));
        return entity;
    }
    @Override
    public int deleteExerciseForPersonalTraining(Integer exerciseId, Integer personalTrainingId) {
        return jdbcTemplate.update(DELETE_BY_EXERCISE_ID, exerciseId, personalTrainingId);
    }

    @Override
    public Optional<List<ExercisePersonalTrainingProjection>> findByPersonalTrainingId(Integer personalTrainingId) {
        Optional<List<ExercisePersonalTrainingProjection>> exercisePersonalTrainingProjections;
        try {
            exercisePersonalTrainingProjections = Optional.of(jdbcTemplate.query(FIND_BY_PERSONAL_TRAINING_ID,
                BeanPropertyRowMapper.newInstance(ExercisePersonalTrainingProjection.class), personalTrainingId));
        } catch (EmptyResultDataAccessException e) {
            exercisePersonalTrainingProjections = Optional.empty();
        }
        return exercisePersonalTrainingProjections;
    }
}
