package ua.lviv.iot.jdbc.controller.impl;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import ua.lviv.iot.jdbc.controller.ClientGymController;
import ua.lviv.iot.jdbc.domain.ClientGym;
import ua.lviv.iot.jdbc.domain.projection.ClientGymProjection;
import ua.lviv.iot.jdbc.service.ClientGymService;

@Controller
@AllArgsConstructor
public class ClientGymControllerImpl implements ClientGymController {

    private final ClientGymService clientGymService;

    @Override
    public ClientGym create(ClientGym entity) {
        return clientGymService.create(entity);
    }

    @Override
    public int deleteByClientId(Integer clientId) {
        return clientGymService.deleteByClientId(clientId);
    }

    @Override
    public List<ClientGym> findALl() {
        return clientGymService.findALl();
    }

    @Override
    public Optional<List<ClientGymProjection>> findClientsByGymId(Integer gymId) {
        return clientGymService.findClientsByGymId(gymId);
    }
}
