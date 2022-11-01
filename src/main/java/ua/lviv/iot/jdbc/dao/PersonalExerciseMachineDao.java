package ua.lviv.iot.jdbc.dao;

import java.util.List;
import java.util.Optional;
import ua.lviv.iot.jdbc.domain.PersonalExerciseMachine;

public interface PersonalExerciseMachineDao extends GeneralDao<PersonalExerciseMachine> {
    Optional<List<PersonalExerciseMachine>> findAllByType(String type);
}
