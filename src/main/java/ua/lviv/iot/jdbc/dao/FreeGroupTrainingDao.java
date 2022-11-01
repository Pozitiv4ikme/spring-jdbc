package ua.lviv.iot.jdbc.dao;

import java.util.List;
import java.util.Optional;
import ua.lviv.iot.jdbc.domain.FreeGroupTraining;

public interface FreeGroupTrainingDao extends GeneralDao<FreeGroupTraining> {
    Optional<List<FreeGroupTraining>> findByTrainerId(Integer trainerId);
    Optional<List<FreeGroupTraining>> findByFreeGroupProgramId(Integer freeGroupProgramId);
}
