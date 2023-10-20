package com.img.mockito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.img.mockito.model.Dummy;
import com.img.mockito.service.DummyService;

@RestController
@RequestMapping("/dummy")
public class _Controller {
	
	@Autowired
	private DummyService dummyService;
	
	@PostMapping("/add")
	public Dummy add(@RequestBody Dummy dummy) {
		return dummyService.add(dummy);
	}
	
	@GetMapping("/fetch")
	public List<Dummy> fetch(){
		return dummyService.getDummies();
	}
	@GetMapping("/fetch/{id}")
	public Dummy fetchById(@PathVariable int id){
		return dummyService.fetchById(id);
	}
	
	@PutMapping("/update/{id}")
	public Dummy update(@PathVariable int id,@RequestBody Dummy dummy) {
		return dummyService.update(id, dummy);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		dummyService.delete(id);
		return "deleted";
	}

}
