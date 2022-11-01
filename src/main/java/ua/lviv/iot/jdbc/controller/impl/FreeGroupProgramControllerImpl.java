package ua.lviv.iot.jdbc.controller.impl;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import ua.lviv.iot.jdbc.controller.FreeGroupProgramController;
import ua.lviv.iot.jdbc.domain.FreeGroupProgram;
import ua.lviv.iot.jdbc.service.FreeGroupProgramService;

@Controller
@AllArgsConstructor
public class FreeGroupProgramControllerImpl implements FreeGroupProgramController {

    private final FreeGroupProgramService freeGroupProgramService;

    @Override
    public Optional<List<FreeGroupProgram>> findAllByExercise(String exercise) {
        return freeGroupProgramService.findAllByExercise(exercise);
    }

    @Override
    public List<FreeGroupProgram> findALl() {
        return freeGroupProgramService.findALl();
    }

    @Override
    public Optional<FreeGroupProgram> findById(Integer id) {
        return freeGroupProgramService.findById(id);
    }

    @Override
    public FreeGroupProgram create(FreeGroupProgram entity) {
        return freeGroupProgramService.create(entity);
    }

    @Override
    public FreeGroupProgram update(FreeGroupProgram entity, Integer id) {
        return freeGroupProgramService.update(entity, id);
    }

    @Override
    public int delete(Integer id) {
        return freeGroupProgramService.delete(id);
    }
}
