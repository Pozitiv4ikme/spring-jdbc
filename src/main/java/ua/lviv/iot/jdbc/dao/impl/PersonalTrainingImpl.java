package ua.lviv.iot.jdbc.dao.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.jdbc.dao.PersonalTrainingDao;
import ua.lviv.iot.jdbc.domain.PersonalTraining;

@Repository
@RequiredArgsConstructor
public class PersonalTrainingImpl implements PersonalTrainingDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String CREATE = "INSERT personal_training(name, surname, birthday, phone, gender) VALUES (?, "
        + "?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE personal_training SET name=?, surname=?, birthday=?, phone=?, "
        + "gender=? WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM personal_training";
    private static final String DELETE = "DELETE FROM personal_training WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM personal_training WHERE id=?";

    @Override
    public List<PersonalTraining> findALl() {
        return null;
    }

    @Override
    public Optional<PersonalTraining> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public PersonalTraining create(
        PersonalTraining entity) {
        return null;
    }

    @Override
    public PersonalTraining update(PersonalTraining entity, Integer id) {
        return null;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }
}
