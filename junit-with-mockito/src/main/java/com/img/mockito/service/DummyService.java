package com.img.mockito.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.img.mockito.model.Dummy;
import com.img.mockito.repository.DummyRepository;

@Service
public class DummyService {
	@Autowired
	private DummyRepository repository;
	
	public Dummy add(Dummy dummy) {
		return repository.save(dummy);
	}
	
	public List<Dummy> getDummies(){
		return repository.findAll();
	}
	
	public Dummy update(int id,Dummy dummy) {
		Optional<Dummy> optional=repository.findById(id);
		Dummy dummy2=optional.get();
		dummy2.setName(dummy.getName());
		return repository.save(dummy2);
	}
	
	public void delete(int id) {
		repository.deleteById(id);
	}

	public Dummy fetchById(int id){
		return repository.findById(id).get();
	}
}
