package ua.lviv.iot.jdbc.controller;

import java.util.List;
import java.util.Optional;
import ua.lviv.iot.jdbc.domain.Client;
import ua.lviv.iot.jdbc.domain.projection.ClientProjection;

public interface ClientController extends GeneralController<Client>{
    Optional<List<ClientProjection>> findByGender(Integer genderId);
    Optional<List<Client>> findByName(String name);
}
