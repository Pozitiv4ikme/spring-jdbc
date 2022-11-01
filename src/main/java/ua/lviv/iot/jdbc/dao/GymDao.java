package ua.lviv.iot.jdbc.dao;

import java.util.List;
import java.util.Optional;
import ua.lviv.iot.jdbc.domain.Gym;

public interface GymDao extends GeneralDao<Gym>{
    Optional<List<Gym>> findByCity(Integer cityId);
}
