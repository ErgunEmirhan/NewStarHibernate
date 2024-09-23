package SoccerApp.services;

import SoccerApp.utility.ICRUD;
import SoccerApp.utility.ICRUDService;

import java.util.List;
import java.util.Optional;

public class BaseServices<T,ID> implements ICRUDService<T,ID> {
	protected  ICRUD<T, ID> repository;

	
	
	@Override
	public T save(T entity) {
		return repository.save(entity);
	}
	
	@Override
	public Iterable<T> saveAll(Iterable<T> entities) {
		return repository.saveAll(entities);
	}
	
	@Override
	public Boolean deleteById(ID id) {
		return repository.deleteById(id);
	}
	
	@Override
	public Optional<T> findById(ID id) {
		return repository.findById(id);
	}
	
	@Override
	public Boolean existById(ID id) {
		return repository.existById(id);
	}
	
	@Override
	public List<T> findAll() {
		return repository.findAll();
	}
	
	@Override
	public List<T> findByFieldNameAndValue(String fieldName, Object value) {
		return repository.findByFieldNameAndValue(fieldName,value);
	}
	
	@Override
	public List<T> findByFilledFields(T entity) {
		return repository.findByFilledFields(entity);
	}
}