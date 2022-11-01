package ua.lviv.iot.jdbc.controller;

import java.util.List;
import java.util.Optional;
import ua.lviv.iot.jdbc.domain.FreeGroupProgram;

public interface FreeGroupProgramController extends GeneralController<FreeGroupProgram>{
    Optional<List<FreeGroupProgram>> findAllByExercise(String exercise);
}
