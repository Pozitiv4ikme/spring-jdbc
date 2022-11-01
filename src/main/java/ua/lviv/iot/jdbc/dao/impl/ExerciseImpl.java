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
import ua.lviv.iot.jdbc.dao.ExerciseDao;
import ua.lviv.iot.jdbc.domain.Exercise;
import ua.lviv.iot.jdbc.domain.projection.ExerciseProjection;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ExerciseImpl implements ExerciseDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String CREATE = "INSERT exercise(number_of_repetitions, approach, complexity, "
        + "personal_exercise_machine_id, type_of_muscle_load_on) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE exercise SET number_of_repetitions=?, approach=?, complexity=?, "
        + "personal_exercise_machine_id=?, type_of_muscle_load_on=? WHERE "
        + "id=?";
    private static final String FIND_ALL = "SELECT * FROM exercise";
    private static final String DELETE = "DELETE FROM exercise WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM exercise WHERE id=?";
    private static final String FIND_BY_TYPE_OF_MUSCLE_LOAD_ON = "SELECT * FROM exercise WHERE "
        + "type_of_muscle_load_on=?";
    private static final String FIND_BY_PERSONAL_EXERCISE_MACHINE_ID = """
            SELECT exercise.id, number_of_repetitions, approach, complexity, personal_exercise_machine.type, type_of_muscle_load_on
            FROM exercise
            JOIN personal_exercise_machine
            ON exercise.personal_exercise_machine_id = personal_exercise_machine.id
            WHERE exercise.personal_exercise_machine_id = ?;
        """;

    @Override
    public Optional<List<Exercise>> findByTypeOfMuscleLoadOn(String typeOfMuscleLoadOn) {
        Optional<List<Exercise>> exercises;

        try {
            exercises = Optional.of(jdbcTemplate.query(FIND_BY_TYPE_OF_MUSCLE_LOAD_ON,
                BeanPropertyRowMapper.newInstance(Exercise.class), typeOfMuscleLoadOn));
        } catch (EmptyResultDataAccessException e) {
            exercises = Optional.empty();
        }

        return exercises;
    }

    @Override
    public Optional<List<ExerciseProjection>> findByPersonalExerciseMachineId(Integer personalExerciseMachineId) {
        Optional<List<ExerciseProjection>> exerciseProjections;

        try {
            exerciseProjections = Optional.of(jdbcTemplate.query(FIND_BY_PERSONAL_EXERCISE_MACHINE_ID,
                BeanPropertyRowMapper.newInstance(ExerciseProjection.class), personalExerciseMachineId));
        } catch (EmptyResultDataAccessException e) {
            exerciseProjections = Optional.empty();
        }

        return exerciseProjections;
    }

    @Override
    public List<Exercise> findALl() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Exercise.class));
    }

    @Override
    public Optional<Exercise> findById(Integer id) {
        Optional<Exercise> exercise;

        try {
            exercise = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                BeanPropertyRowMapper.newInstance(Exercise.class), id));
        } catch (EmptyResultDataAccessException e) {
            exercise = Optional.empty();
        }
        return exercise;
    }

    @Override
    public Exercise create(Exercise entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        log.info("There are created rows " + jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, entity.getNumberOfRepetitions());
            ps.setInt(2, entity.getApproach());
            if (entity.getComplexity() == null) {
                ps.setNull(3, Types.INTEGER);
            } else {
                ps.setInt(3, entity.getComplexity());
            }
            ps.setInt(4, entity.getPersonalExerciseMachineId());
            ps.setString(5, entity.getTypeOfMuscleLoadOn());
            return ps;
        }, keyHolder));

        return new Exercise(Objects.requireNonNull(keyHolder.getKey()).intValue(), entity.getNumberOfRepetitions(),
            entity.getApproach(), entity.getComplexity(), entity.getPersonalExerciseMachineId(), entity.getTypeOfMuscleLoadOn());
    }

    @Override
    public Exercise update(Exercise entity, Integer id) {
        log.info("There are updated rows " + jdbcTemplate.update(UPDATE, entity.getNumberOfRepetitions(),
            entity.getApproach(), entity.getComplexity(), entity.getPersonalExerciseMachineId(),
            entity.getTypeOfMuscleLoadOn(), id));

         return entity;
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
