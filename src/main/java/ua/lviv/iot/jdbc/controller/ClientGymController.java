package ua.lviv.iot.jdbc.controller;

import java.util.List;
import java.util.Optional;
import ua.lviv.iot.jdbc.domain.ClientGym;
import ua.lviv.iot.jdbc.domain.projection.ClientGymProjection;

public interface ClientGymController {
    ClientGym create(ClientGym entity);
    int deleteByClientId(Integer clientId);
    List<ClientGym> findALl();
    Optional<List<ClientGymProjection>> findClientsByGymId(Integer gymId);
}
