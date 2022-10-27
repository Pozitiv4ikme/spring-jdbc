package ua.lviv.iot.jdbc.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lviv.iot.jdbc.dao.ClientDao;
import ua.lviv.iot.jdbc.domain.Client;
import ua.lviv.iot.jdbc.domain.projection.ClientProjection;
import ua.lviv.iot.jdbc.service.ClientService;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;

    @Override
    public Optional<List<ClientProjection>> findByGender(Integer genderId) {
        return clientDao.findByGender(genderId);
    }

    @Override
    public Optional<List<Client>> findByName(String name) {
        return clientDao.findByName(name);
    }

    @Override
    public List<Client> findALl() {
        return clientDao.findALl();
    }

    @Override
    public Optional<Client> findById(Integer id) {
        return clientDao.findById(id);
    }

    @Override
    public Client create(Client entity) {
        return clientDao.create(entity);
    }

    @Override
    public Client update(Client entity, Integer id) {
        return clientDao.update(entity, id);
    }

    @Override
    public int delete(Integer id) {
        return clientDao.delete(id);
    }
}
