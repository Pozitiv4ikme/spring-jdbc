package ua.lviv.iot.jdbc.dao.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.jdbc.dao.FreeGroupProgramDao;
import ua.lviv.iot.jdbc.domain.FreeGroupProgram;

@Repository
@RequiredArgsConstructor
public class FreeGroupProgramImpl implements FreeGroupProgramDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String CREATE = "INSERT free_group_program(name, surname, birthday, phone, gender) VALUES "
        + "(?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE free_group_program SET name=?, surname=?, birthday=?, phone=?, "
        + "gender=? WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM free_group_program";
    private static final String DELETE = "DELETE FROM free_group_program WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM free_group_program WHERE id=?";

    @Override
    public Optional<List<FreeGroupProgram>> findAllByExercise(String exercise) {
        return Optional.empty();
    }

    @Override
    public List<FreeGroupProgram> findALl() {
        return null;
    }

    @Override
    public Optional<FreeGroupProgram> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public FreeGroupProgram create(
        FreeGroupProgram entity) {
        return null;
    }

    @Override
    public FreeGroupProgram update(FreeGroupProgram entity, Integer id) {
        return null;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }
}
