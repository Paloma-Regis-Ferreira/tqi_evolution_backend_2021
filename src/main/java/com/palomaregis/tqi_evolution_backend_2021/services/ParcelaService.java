package com.palomaregis.tqi_evolution_backend_2021.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palomaregis.tqi_evolution_backend_2021.entities.Parcela;
import com.palomaregis.tqi_evolution_backend_2021.repositories.ParcelaRepository;

//regras de negocio e requisições ao bd

@Service
public class ParcelaService {
	
	@Autowired
	private ParcelaRepository repository;
	
	public List<Parcela> findAll(){
		return repository.findAll();
	}
	
	public Parcela findById(Long id) {
		Optional<Parcela> obj = repository.findById(id);
		return obj.get();
	}
	
}
