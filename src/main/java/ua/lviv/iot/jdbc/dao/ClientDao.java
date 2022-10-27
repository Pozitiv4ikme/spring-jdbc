package ua.lviv.iot.jdbc.dao;

import java.util.List;
import java.util.Optional;
import ua.lviv.iot.jdbc.domain.Client;
import ua.lviv.iot.jdbc.domain.projection.ClientProjection;

public interface ClientDao extends GeneralDao<Client>{
    Optional<List<ClientProjection>> findByGender(Integer genderId);
    Optional<List<Client>> findByName(String name);
}
