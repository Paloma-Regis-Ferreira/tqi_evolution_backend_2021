package com.palomaregis.tqi_evolution_backend_2021.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palomaregis.tqi_evolution_backend_2021.entities.Emprestimo;
import com.palomaregis.tqi_evolution_backend_2021.repositories.EmprestimoRepository;

//regras de negocio e requisições ao bd

@Service
public class EmprestimoService {
	
	@Autowired
	private EmprestimoRepository repository;
	
	public List<Emprestimo> findAll(){
		return repository.findAll();
	}
	
	public Emprestimo findById(Long id) {
		Optional<Emprestimo> obj = repository.findById(id);
		return obj.get();
	}
}
