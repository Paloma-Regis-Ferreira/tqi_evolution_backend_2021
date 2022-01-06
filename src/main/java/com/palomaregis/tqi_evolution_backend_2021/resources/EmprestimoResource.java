package com.palomaregis.tqi_evolution_backend_2021.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palomaregis.tqi_evolution_backend_2021.entities.Emprestimo;
import com.palomaregis.tqi_evolution_backend_2021.services.EmprestimoService;

//Controller

@RestController
@RequestMapping(value = "/emprestimos")
public class EmprestimoResource {
	
	@Autowired
	private EmprestimoService service;
	
	@GetMapping
	public ResponseEntity<List<Emprestimo>> findAll(){
		List<Emprestimo> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Emprestimo> findById(@PathVariable Long id){
		Emprestimo obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
