package com.palomaregis.tqi_evolution_backend_2021.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palomaregis.tqi_evolution_backend_2021.entities.Cliente;

//Controller

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@GetMapping
	public ResponseEntity<Cliente> findAll(){
		Cliente c = new Cliente(1L, "Paloma", "paloma.com", "444.222.555.33", "44.777.333-Z", "av. X, n 123", 2000.00, 123456);
		return ResponseEntity.ok().body(c);
	}
}
