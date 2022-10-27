package ua.lviv.iot.jdbc.service;


import java.util.List;
import java.util.Optional;
import ua.lviv.iot.jdbc.domain.Client;
import ua.lviv.iot.jdbc.domain.projection.ClientProjection;

public interface ClientService extends GeneralService<Client>{
    Optional<List<ClientProjection>> findByGender(Integer genderId);
    Optional<List<Client>> findByName(String name);
}
