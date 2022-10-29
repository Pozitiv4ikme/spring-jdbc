package ua.lviv.iot.jdbc.dao.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
import ua.lviv.iot.jdbc.dao.FreeGroupProgramDao;
import ua.lviv.iot.jdbc.domain.City;
import ua.lviv.iot.jdbc.domain.ExercisePersonalTraining;
import ua.lviv.iot.jdbc.domain.FreeGroupProgram;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FreeGroupProgramImpl implements FreeGroupProgramDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String CREATE = "INSERT free_group_program(day, exercise) VALUES "
        + "(?, ?)";
    private static final String UPDATE = "UPDATE free_group_program SET day=?, exercise=? WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM free_group_program";
    private static final String DELETE = "DELETE FROM free_group_program WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM free_group_program WHERE id=?";
    private static final String FIND_BY_EXERCISE = "SELECT * FROM free_group_program WHERE exercise=?";

    @Override
    public Optional<List<FreeGroupProgram>> findAllByExercise(String exercise) {
        Optional<List<FreeGroupProgram>> freeGroupPrograms;

        try {
            freeGroupPrograms = Optional.of(jdbcTemplate.query(FIND_BY_EXERCISE,
                BeanPropertyRowMapper.newInstance(FreeGroupProgram.class), exercise));
        } catch (EmptyResultDataAccessException e) {
            freeGroupPrograms = Optional.empty();
        }

        return freeGroupPrograms;
    }

    @Override
    public List<FreeGroupProgram> findALl() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(FreeGroupProgram.class));
    }

    @Override
    public Optional<FreeGroupProgram> findById(Integer id) {
        Optional<FreeGroupProgram> freeGroupProgram;

        try {
            freeGroupProgram = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                BeanPropertyRowMapper.newInstance(FreeGroupProgram.class), id));
        } catch (EmptyResultDataAccessException e) {
            freeGroupProgram = Optional.empty();
        }
        return freeGroupProgram;
    }

    @Override
    public FreeGroupProgram create(FreeGroupProgram entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        log.info("There are created rows " + jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getDay());
            ps.setString(2, entity.getExercise());
            return ps;
        }, keyHolder));

        return new FreeGroupProgram(Objects.requireNonNull(keyHolder.getKey()).intValue(), entity.getDay(),
            entity.getExercise());
    }

    @Override
    public FreeGroupProgram update(FreeGroupProgram entity, Integer id) {
        log.info("There are updated rows " + jdbcTemplate.update(UPDATE, entity.getDay(), entity.getExercise(), id));
        return entity;
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
