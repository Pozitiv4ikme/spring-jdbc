package ua.lviv.iot.jdbc.dao.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.jdbc.dao.FreeGroupTrainingDao;
import ua.lviv.iot.jdbc.domain.FreeGroupTraining;

@Repository
@RequiredArgsConstructor
public class FreeGroupTrainingImpl implements FreeGroupTrainingDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String CREATE = "INSERT free_group_training(name, surname, birthday, phone, gender) VALUES "
        + "(?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE free_group_training SET name=?, surname=?, birthday=?, phone=?, "
        + "gender=? WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM free_group_training";
    private static final String DELETE = "DELETE FROM free_group_training WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM free_group_training WHERE id=?";

    @Override
    public Optional<List<FreeGroupTraining>> findByTrainerId(Integer trainerId) {
        return Optional.empty();
    }

    @Override
    public Optional<List<FreeGroupTraining>> findByFreeGroupProgramId(
        Integer freeGroupProgramId) {
        return Optional.empty();
    }

    @Override
    public List<FreeGroupTraining> findALl() {
        return null;
    }

    @Override
    public Optional<FreeGroupTraining> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public FreeGroupTraining create(
        FreeGroupTraining entity) {
        return null;
    }

    @Override
    public FreeGroupTraining update(FreeGroupTraining entity, Integer id) {
        return null;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }
}
