package ua.lviv.iot.jdbc.dao;

import java.util.List;
import java.util.Optional;
import ua.lviv.iot.jdbc.domain.FreeGroupProgram;

public interface FreeGroupProgramDao extends GeneralDao<FreeGroupProgram> {
    Optional<List<FreeGroupProgram>> findAllByExercise(String exercise);
}
