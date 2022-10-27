package ua.lviv.iot.jdbc.dao.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.jdbc.dao.ClientDao;
import ua.lviv.iot.jdbc.domain.Client;
import ua.lviv.iot.jdbc.domain.projection.ClientProjection;

@Repository
@RequiredArgsConstructor
public class ClientDaoImpl implements ClientDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String CREATE = "INSERT INTO client(name, surname, birthday, phone, gender_id) VALUES (?, ?, "
        + "?, "
        + "?, ?)";
    private static final String UPDATE = "UPDATE client SET name=?, surname=?, birthday=?, phone=?, gender_id=? WHERE "
        + "id=?";
    private static final String FIND_ALL = "SELECT * FROM client";
    private static final String DELETE = "DELETE FROM client WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM client WHERE id=?";
    private static final String FIND_BY_GENDER = """
        SELECT name, surname, birthday, phone, gender.type
        FROM client
        JOIN gender
        ON client.gender_id = gender.id
        WHERE client.gender_id = ?;
    """;
    private static final String FIND_BY_NAME = "SELECT * FROM client WHERE name=?";

    @Override
    public Client create(Client client) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, client.getName());
            ps.setString(2, client.getSurname());
            ps.setString(3, client.getBirthday());
            ps.setString(4, client.getPhone());
            ps.setInt(5, client.getGenderId());
            return ps;
        }, keyHolder);

        return new Client(Objects.requireNonNull(keyHolder.getKey()).intValue(), client.getName(), client.getSurname()
            , client.getBirthday(), client.getPhone(), client.getGenderId());
    }

    @Override
    public Client update(Client client, Integer id) {
        jdbcTemplate.update(UPDATE, client.getName(), client.getSurname(), client.getBirthday(),
            client.getPhone(), client.getGenderId(), id);
        return client;
    }

    @Override
    public List<Client> findALl() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Client.class));
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<Client> findById(Integer id) {
        Optional<Client> client;
        try {
            client = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                BeanPropertyRowMapper.newInstance(Client.class), id));
        } catch (EmptyResultDataAccessException e) {
            client = Optional.empty();
        }
        return client;
    }

    @Override
    public Optional<List<ClientProjection>> findByGender(Integer genderId) {
        Optional<List<ClientProjection>> clients;
        try {
            clients = Optional.of(jdbcTemplate.query(FIND_BY_GENDER,
                BeanPropertyRowMapper.newInstance(ClientProjection.class), genderId));
        } catch (EmptyResultDataAccessException e) {
            clients = Optional.empty();
        }
        return clients;
    }

    @Override
    public Optional<List<Client>> findByName(String name) {
        Optional<List<Client>> clients;
        try {
            clients = Optional.of(jdbcTemplate.query(FIND_BY_NAME,
                BeanPropertyRowMapper.newInstance(Client.class), name));
        } catch (EmptyResultDataAccessException e) {
            clients = Optional.empty();
        }
        return clients;
    }
}
