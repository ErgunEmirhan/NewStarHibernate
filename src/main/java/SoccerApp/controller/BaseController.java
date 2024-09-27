package SoccerApp.controller;

import SoccerApp.utility.ICRUD;
import SoccerApp.utility.ICRUDService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BaseController <T,ID> implements ICRUD<T,ID> {
	private final ICRUDService<T,ID> service;
	public BaseController(ICRUDService<T,ID> service) {
		this.service = service;
	}
	@Override
	public T save(T entity) {
		try {
			return service.save(entity);
		}
		catch (Exception e) {
			e.printStackTrace();
			return entity;
		}
	}
	
	@Override
	public void update(T entity) {
		service.update(entity);
	}
	
	@Override
	public Iterable<T> saveAll(Iterable<T> entities) {
		try {
			return service.saveAll(entities);
		}
		catch (Exception e) {
			e.printStackTrace();
			return entities;
		}
	}
	
	@Override
	public Boolean deleteById(ID id) {
		return service.deleteById(id);
	}
	
	@Override
	public Optional<T> findById(ID id) {
		try {
			return service.findById(id);
		}
		catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
	
	@Override
	public Boolean existById(ID id) {
		return service.existById(id);
	}
	
	@Override
	public List<T> findAll() {
		try {
			return service.findAll();
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	@Override
	public List<T> findByFieldNameAndValue(String fieldName, Object value) {
		try {
			return service.findByFieldNameAndValue(fieldName, value);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	@Override
	public List<T> findByFilledFields(T entity) {
		try {
			return service.findByFilledFields(entity);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
}