package ua.lviv.iot.jdbc.controller.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ua.lviv.iot.jdbc.controller.ClientController;
import ua.lviv.iot.jdbc.domain.Client;
import ua.lviv.iot.jdbc.domain.projection.ClientProjection;
import ua.lviv.iot.jdbc.service.ClientService;

@Controller
@RequiredArgsConstructor
public class ClientControllerImpl implements ClientController {

    private final ClientService clientService;

    @Override
    public Optional<List<ClientProjection>> findByGender(Integer genderId) {
        return clientService.findByGender(genderId);
    }

    @Override
    public Optional<List<Client>> findByName(String name) {
        return clientService.findByName(name);
    }

    @Override
    public List<Client> findALl() {
        return clientService.findALl();
    }

    @Override
    public Optional<Client> findById(Integer id) {
        return clientService.findById(id);
    }

    @Override
    public Client create(Client entity) {
        return clientService.create(entity);
    }

    @Override
    public Client update(Client entity, Integer id) {
        return clientService.update(entity, id);
    }

    @Override
    public int delete(Integer id) {
        return clientService.delete(id);
    }
}
