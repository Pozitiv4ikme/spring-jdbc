package ua.lviv.iot.jdbc.dao.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.jdbc.dao.CityDao;
import ua.lviv.iot.jdbc.domain.City;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CityDaoImpl implements CityDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String CREATE = "INSERT INTO city(name) VALUES (?)";
    private static final String UPDATE = "UPDATE city SET name=? WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM city";
    private static final String DELETE = "DELETE FROM city WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM city WHERE id=?";

    @Override
    public City create(City entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        log.info("There are created rows " + jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getName());
            return ps;
        }, keyHolder));

        return new City(Objects.requireNonNull(keyHolder.getKey()).intValue(), entity.getName());
    }

    @Override
    public City update(City entity, Integer id) {
        log.info("There are updated rows " + jdbcTemplate.update(UPDATE, entity.getName(), id));
        return entity;
    }

    @Override
    public List<City> findALl() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(City.class));
    }

    @Override
    public Optional<City> findById(Integer id) {
        Optional<City> city;
        try {
            city = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                BeanPropertyRowMapper.newInstance(City.class), id));
        } catch (EmptyResultDataAccessException e) {
            city = Optional.empty();
        }
        return city;
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
