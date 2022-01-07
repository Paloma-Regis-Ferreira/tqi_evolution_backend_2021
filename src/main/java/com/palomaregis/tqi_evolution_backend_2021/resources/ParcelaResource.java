package com.palomaregis.tqi_evolution_backend_2021.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palomaregis.tqi_evolution_backend_2021.entities.Parcela;
import com.palomaregis.tqi_evolution_backend_2021.services.ParcelaService;

//Controller

@RestController
@RequestMapping(value = "/parcelas")
public class ParcelaResource {
	
	@Autowired
	private ParcelaService service;
	
	@GetMapping
	public ResponseEntity<List<Parcela>> findAll(){
		List<Parcela> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Parcela> findById(@PathVariable Long id){
		Parcela obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
