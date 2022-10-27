package ua.lviv.iot.jdbc.dao.impl;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.jdbc.dao.ClientGymDao;
import ua.lviv.iot.jdbc.domain.Client;
import ua.lviv.iot.jdbc.domain.ClientGym;
import ua.lviv.iot.jdbc.domain.projection.ClientGymProjection;

@Repository
@RequiredArgsConstructor
public class ClientGymImpl implements ClientGymDao {

    private final  JdbcTemplate jdbcTemplate;

    private static final String CREATE = "INSERT client_gym(gym_id, client_id) VALUES (?, ?)";
    private static final String FIND_ALL = "SELECT * FROM client_gym";
    private static final String DELETE = "DELETE FROM client_gym WHERE client_id = ?;";
    private static final String FIND_BY_GYM_ID = """
            SELECT gym_id, name, surname, birthday, phone, type
            FROM (
                SELECT c.id, c.name, c.surname, c.birthday, c.phone, g.type
                FROM client as c
                JOIN gender as g
                ON c.gender_id = g.id
            ) as client_gender
            JOIN client_gym
            ON client_gender.id = client_gym.client_id
            WHERE gym_id = ?;
        """;

    @Override
    public ClientGym create(ClientGym entity) {
        jdbcTemplate.update(CREATE, entity.getGymId(), entity.getClientId());
        return entity;
    }

    @Override
    public int deleteByClientId(Integer clientId) {
        return jdbcTemplate.update(DELETE, clientId);
    }

    @Override
    public List<ClientGym> findALl() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(ClientGym.class));
    }

    @Override
    public Optional<List<ClientGymProjection>> findClientsByGymId(Integer gymId) {
        Optional<List<ClientGymProjection>> clients;

        try {
            clients = Optional.of(jdbcTemplate.query(FIND_BY_GYM_ID,
                BeanPropertyRowMapper.newInstance(ClientGymProjection.class),
                gymId));
        } catch (EmptyResultDataAccessException e) {
            clients = Optional.empty();
        }
        return clients;
    }
}
