package co.backend.api.rest.models.dao;

import org.springframework.data.repository.CrudRepository;

import co.backend.api.rest.models.entity.ActivoFijo;

public interface IActivoFijoDao extends CrudRepository<ActivoFijo, Long> {

	public Iterable<ActivoFijo> findAll();

}
