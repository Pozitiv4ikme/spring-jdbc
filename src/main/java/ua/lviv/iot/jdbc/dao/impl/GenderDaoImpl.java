package ua.lviv.iot.jdbc.dao.impl;

import java.util.List;
import java.util.Optional;
import ua.lviv.iot.jdbc.dao.GenderDao;
import ua.lviv.iot.jdbc.domain.Gender;

public class GenderDaoImpl implements GenderDao {

    @Override
    public List<Gender> findALl() {
        return null;
    }

    @Override
    public Optional<Gender> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Gender create(Gender entity) {
        return null;
    }

    @Override
    public Gender update(Gender entity, Integer id) {
        return null;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }
}
