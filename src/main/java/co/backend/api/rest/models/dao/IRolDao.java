package co.backend.api.rest.models.dao;

import org.springframework.data.repository.CrudRepository;

import co.backend.api.rest.models.entity.Rol;

public interface IRolDao extends CrudRepository<Rol, Long> {

}
