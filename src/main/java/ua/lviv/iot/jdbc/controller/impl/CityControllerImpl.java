package ua.lviv.iot.jdbc.controller.impl;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import ua.lviv.iot.jdbc.controller.CityController;
import ua.lviv.iot.jdbc.domain.City;
import ua.lviv.iot.jdbc.service.CityService;

@Controller
@AllArgsConstructor
public class CityControllerImpl implements CityController {

    private final CityService cityService;

    @Override
    public List<City> findALl() {
        return cityService.findALl();
    }

    @Override
    public Optional<City> findById(Integer id) {
        return cityService.findById(id);
    }

    @Override
    public City create(City entity) {
        return cityService.create(entity);
    }

    @Override
    public City update(City entity, Integer id) {
        return cityService.update(entity, id);
    }

    @Override
    public int delete(Integer id) {
        return cityService.delete(id);
    }
}
