package ua.lviv.iot.jdbc.dao.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.jdbc.dao.PersonalExerciseMachineDao;
import ua.lviv.iot.jdbc.domain.PersonalExerciseMachine;

@Repository
@RequiredArgsConstructor
public class PersonalExerciseMachineImpl implements PersonalExerciseMachineDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String CREATE = "INSERT personal_exercise_machine(name, surname, birthday, phone, gender) "
        + "VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE personal_exercise_machine SET name=?, surname=?, birthday=?, phone=?,"
        + " gender=? WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM personal_exercise_machine";
    private static final String DELETE = "DELETE FROM personal_exercise_machine WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM personal_exercise_machine WHERE id=?";

    @Override
    public List<PersonalExerciseMachine> findALl() {
        return null;
    }

    @Override
    public Optional<PersonalExerciseMachine> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public PersonalExerciseMachine create(
        PersonalExerciseMachine entity) {
        return null;
    }

    @Override
    public PersonalExerciseMachine update(PersonalExerciseMachine entity, Integer id) {
        return null;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public Optional<List<PersonalExerciseMachine>> findAllByType(String type) {
        return Optional.empty();
    }
}
