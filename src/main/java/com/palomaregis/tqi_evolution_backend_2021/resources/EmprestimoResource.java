package com.palomaregis.tqi_evolution_backend_2021.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping
	public ResponseEntity<Emprestimo> insert(@RequestBody Emprestimo obj){
		obj = service.solicitarEmprestimo(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
}
