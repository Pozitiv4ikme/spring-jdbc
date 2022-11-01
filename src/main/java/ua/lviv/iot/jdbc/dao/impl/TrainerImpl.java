package ua.lviv.iot.jdbc.dao.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.jdbc.dao.TrainerDao;
import ua.lviv.iot.jdbc.domain.Trainer;

@Repository
@RequiredArgsConstructor
public class TrainerImpl implements TrainerDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String CREATE = "INSERT trainer(name, surname, birthday, phone, gender) VALUES (?, ?, ?, ?, "
        + "?)";
    private static final String UPDATE = "UPDATE trainer SET name=?, surname=?, birthday=?, phone=?, gender=? WHERE "
        + "id=?";
    private static final String FIND_ALL = "SELECT * FROM trainer";
    private static final String DELETE = "DELETE FROM trainer WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM trainer WHERE id=?";

    @Override
    public List<Trainer> findALl() {
        return null;
    }

    @Override
    public Optional<Trainer> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Trainer create(Trainer entity) {
        return null;
    }

    @Override
    public Trainer update(Trainer entity, Integer id) {
        return null;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public Optional<List<Trainer>> findByGymId(Integer gymId) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Trainer>> findByName(String name) {
        return Optional.empty();
    }
}
