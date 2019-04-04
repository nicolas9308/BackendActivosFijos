package co.backend.api.rest.models.dao;

import org.springframework.data.repository.CrudRepository;

import co.backend.api.rest.models.entity.TipoActivo;

public interface ITipoActivoDao extends CrudRepository<TipoActivo, Long> {

}
