package ua.lviv.iot.jdbc.service;

import java.util.List;
import java.util.Optional;

public interface GeneralService<E> {
    List<E> findALl();

    Optional<E> findById(Integer id);

    E create(E entity);

    E update(E entity, Integer id);

    int delete(Integer id);
}
