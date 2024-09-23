package SoccerApp.controller;

import SoccerApp.utility.ICRUD;
import SoccerApp.utility.ICRUDService;

import java.util.List;
import java.util.Optional;

public class BaseController <T,ID> implements ICRUD<T,ID> {
	private final ICRUDService<T,ID> service;
	public BaseController(ICRUDService<T,ID> service) {
		this.service = service;
	}
	@Override
	public T save(T entity) {
		return service.save(entity);
	}
	
	@Override
	public Iterable<T> saveAll(Iterable<T> entities) {
		return service.saveAll(entities);
	}
	
	@Override
	public Boolean deleteById(ID id) {
		return service.deleteById(id);
	}
	
	@Override
	public Optional<T> findById(ID id) {
		return service.findById(id);
	}
	
	@Override
	public Boolean existById(ID id) {
		return service.existById(id);
	}
	
	@Override
	public List<T> findAll() {
		return service.findAll();
	}
	
	@Override
	public List<T> findByFieldNameAndValue(String fieldName, Object value) {
		return service.findByFieldNameAndValue(fieldName, value);
	}
	
	@Override
	public List<T> findByFilledFields(T entity) {
		return service.findByFilledFields(entity);
	}
}