package co.backend.api.rest.models.dao;

import org.springframework.data.repository.CrudRepository;

import co.backend.api.rest.models.entity.Area;

public interface IAreaDao extends CrudRepository<Area, Long> {

}
