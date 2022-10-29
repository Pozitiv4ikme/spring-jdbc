package ua.lviv.iot.jdbc.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lviv.iot.jdbc.dao.FreeGroupProgramDao;
import ua.lviv.iot.jdbc.domain.FreeGroupProgram;
import ua.lviv.iot.jdbc.service.FreeGroupProgramService;

@Service
@AllArgsConstructor
public class FreeGroupProgramServiceImpl implements FreeGroupProgramService {

    private final FreeGroupProgramDao freeGroupProgramDao;

    @Override
    public Optional<List<FreeGroupProgram>> findAllByExercise(String exercise) {
        return freeGroupProgramDao.findAllByExercise(exercise);
    }

    @Override
    public List<FreeGroupProgram> findALl() {
        return freeGroupProgramDao.findALl();
    }

    @Override
    public Optional<FreeGroupProgram> findById(Integer id) {
        return freeGroupProgramDao.findById(id);
    }

    @Override
    public FreeGroupProgram create(FreeGroupProgram entity) {
        return freeGroupProgramDao.create(entity);
    }

    @Override
    public FreeGroupProgram update(FreeGroupProgram entity, Integer id) {
        return freeGroupProgramDao.update(entity, id);
    }

    @Override
    public int delete(Integer id) {
        return freeGroupProgramDao.delete(id);
    }
}
