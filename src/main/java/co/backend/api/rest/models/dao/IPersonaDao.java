package co.backend.api.rest.models.dao;

import org.springframework.data.repository.CrudRepository;

import co.backend.api.rest.models.entity.Persona;

public interface IPersonaDao extends CrudRepository<Persona, Long> {

}
