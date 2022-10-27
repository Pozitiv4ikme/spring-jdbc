package ua.lviv.iot.jdbc.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lviv.iot.jdbc.dao.ClientGymDao;
import ua.lviv.iot.jdbc.domain.ClientGym;
import ua.lviv.iot.jdbc.domain.projection.ClientGymProjection;
import ua.lviv.iot.jdbc.service.ClientGymService;

@Service
@AllArgsConstructor
public class ClientGymServiceImpl implements ClientGymService {

    private final ClientGymDao clientGymDao;

    @Override
    public ClientGym create(ClientGym entity) {
        return clientGymDao.create(entity);
    }

    @Override
    public int deleteByClientId(Integer clientId) {
        return clientGymDao.deleteByClientId(clientId);
    }

    @Override
    public List<ClientGym> findALl() {
        return clientGymDao.findALl();
    }

    @Override
    public Optional<List<ClientGymProjection>> findClientsByGymId(Integer gymId) {
        return clientGymDao.findClientsByGymId(gymId);
    }
}
