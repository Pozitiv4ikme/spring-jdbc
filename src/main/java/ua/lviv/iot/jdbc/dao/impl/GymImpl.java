package ua.lviv.iot.jdbc.dao.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.jdbc.dao.GymDao;
import ua.lviv.iot.jdbc.domain.Gym;

@Repository
@RequiredArgsConstructor
public class GymImpl implements GymDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String CREATE = "INSERT gym(name, surname, birthday, phone, gender) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE gym SET name=?, surname=?, birthday=?, phone=?, gender=? WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM gym";
    private static final String DELETE = "DELETE FROM gym WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM gym WHERE id=?";

    @Override
    public List<Gym> findALl() {
        return null;
    }

    @Override
    public Optional<Gym> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Gym create(Gym entity) {
        return null;
    }

    @Override
    public Gym update(Gym entity, Integer id) {
        return null;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public Optional<List<Gym>> findByCity(Integer cityId) {
        return Optional.empty();
    }
}
