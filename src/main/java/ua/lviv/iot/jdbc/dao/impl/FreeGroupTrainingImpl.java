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
import ua.lviv.iot.jdbc.dao.FreeGroupTrainingDao;
import ua.lviv.iot.jdbc.domain.City;
import ua.lviv.iot.jdbc.domain.FreeGroupTraining;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FreeGroupTrainingImpl implements FreeGroupTrainingDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String CREATE = "INSERT free_group_training(trainer_id, free_group_program_id) VALUES "
        + "(?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE free_group_training SET trainer_id=?, free_group_program_id=? WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM free_group_training";
    private static final String DELETE = "DELETE FROM free_group_training WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM free_group_training WHERE id=?";
    private static final String FIND_BY_FREE_GROUP_PROGRAM_ID = "SELECT * FROM free_group_training WHERE " 
        + "free_group_program_id=?";
    private static final String FIND_BY_TRAINER_ID = "SELECT * FROM free_group_training WHERE trainer_id=?";

    @Override
    public Optional<List<FreeGroupTraining>> findByTrainerId(Integer trainerId) {
        Optional<List<FreeGroupTraining>> freeGroupTrainings;

        try {
            freeGroupTrainings = Optional.of(jdbcTemplate.query(FIND_BY_TRAINER_ID,
                BeanPropertyRowMapper.newInstance(FreeGroupTraining.class), trainerId));
        } catch (EmptyResultDataAccessException e) {
            freeGroupTrainings = Optional.empty();
        }

        return freeGroupTrainings;
    }

    @Override
    public Optional<List<FreeGroupTraining>> findByFreeGroupProgramId(Integer freeGroupProgramId) {
        Optional<List<FreeGroupTraining>> freeGroupTrainings;
        
        try {
            freeGroupTrainings = Optional.of(jdbcTemplate.query(FIND_BY_FREE_GROUP_PROGRAM_ID,
                BeanPropertyRowMapper.newInstance(FreeGroupTraining.class), freeGroupProgramId));
        } catch(EmptyResultDataAccessException e) {
            freeGroupTrainings = Optional.empty();
        }
        
        return freeGroupTrainings;
    }

    @Override
    public List<FreeGroupTraining> findALl() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(FreeGroupTraining.class));
    }

    @Override
    public Optional<FreeGroupTraining> findById(Integer id) {
        Optional<FreeGroupTraining> freeGroupTraining;

        try {
            freeGroupTraining = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                BeanPropertyRowMapper.newInstance(FreeGroupTraining.class), id));
        } catch (EmptyResultDataAccessException e) {
            freeGroupTraining = Optional.empty();
        }

        return freeGroupTraining;
    }

    @Override
    public FreeGroupTraining create(FreeGroupTraining entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        log.info("There are created rows " + jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, entity.getTrainerId());
            ps.setInt(2, entity.getFreeGroupProgramId());
            return ps;
        }, keyHolder));

        return new FreeGroupTraining(Objects.requireNonNull(keyHolder.getKey()).intValue(), entity.getTrainerId(),
            entity.getFreeGroupProgramId());
    }

    @Override
    public FreeGroupTraining update(FreeGroupTraining entity, Integer id) {
        log.info("There are updated rows " + jdbcTemplate.update(UPDATE, entity.getTrainerId(),
            entity.getFreeGroupProgramId(), id));
        return entity;
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
