package ua.lviv.iot.jdbc.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lviv.iot.jdbc.dao.CityDao;
import ua.lviv.iot.jdbc.domain.City;
import ua.lviv.iot.jdbc.service.CityService;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityDao cityDao;

    @Override
    public List<City> findALl() {
        return cityDao.findALl();
    }

    @Override
    public Optional<City> findById(Integer id) {
        return cityDao.findById(id);
    }

    @Override
    public City create(City entity) {
        return cityDao.create(entity);
    }

    @Override
    public City update(City entity, Integer id) {
        return cityDao.update(entity, id);
    }

    @Override
    public int delete(Integer id) {
        return cityDao.delete(id);
    }
}
