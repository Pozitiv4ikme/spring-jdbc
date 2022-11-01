package ua.lviv.iot.jdbc.service;

import java.util.List;
import java.util.Optional;
import ua.lviv.iot.jdbc.domain.FreeGroupProgram;

public interface FreeGroupProgramService extends GeneralService<FreeGroupProgram> {
    Optional<List<FreeGroupProgram>> findAllByExercise(String exercise);
}
