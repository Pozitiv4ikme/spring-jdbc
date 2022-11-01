package ua.lviv.iot.jdbc.dao;

import java.util.List;
import java.util.Optional;
import ua.lviv.iot.jdbc.domain.Trainer;

public interface TrainerDao extends GeneralDao<Trainer> {
    Optional<List<Trainer>> findByGymId(Integer gymId);
    Optional<List<Trainer>> findByName(String name);
}
