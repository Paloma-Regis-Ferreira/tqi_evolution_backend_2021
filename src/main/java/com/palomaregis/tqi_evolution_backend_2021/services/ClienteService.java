package com.palomaregis.tqi_evolution_backend_2021.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.palomaregis.tqi_evolution_backend_2021.entities.Cliente;
import com.palomaregis.tqi_evolution_backend_2021.repositories.ClienteRepository;
import com.palomaregis.tqi_evolution_backend_2021.services.exceptions.DatabaseException;
import com.palomaregis.tqi_evolution_backend_2021.services.exceptions.ResourceNotFoundException;

//regras de negocio e requisições ao bd

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public List<Cliente> findAll(){
		return repository.findAll();
	}
	
	public Cliente findById(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Cliente insert(Cliente obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
		repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Cliente update(Long id, Cliente obj) {
		try {
			Cliente entity = repository.getOne(id);//nao exclui de imediato podendo mexer antes de efetuar operação no banco
			updateData(entity, obj);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id); 
		}
	}

	private void updateData(Cliente entity, Cliente obj) {
		entity.setNome(obj.getNome());
		entity.setEmail(obj.getEmail());
		entity.setEnderecoCompleto(obj.getEnderecoCompleto());
		entity.setRenda(obj.getRenda());
	}
}
