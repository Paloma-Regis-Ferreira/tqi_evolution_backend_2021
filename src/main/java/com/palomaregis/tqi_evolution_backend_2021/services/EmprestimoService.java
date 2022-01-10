package com.palomaregis.tqi_evolution_backend_2021.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.palomaregis.tqi_evolution_backend_2021.entities.Cliente;
import com.palomaregis.tqi_evolution_backend_2021.entities.Emprestimo;
import com.palomaregis.tqi_evolution_backend_2021.entities.Parcela;
import com.palomaregis.tqi_evolution_backend_2021.repositories.EmprestimoRepository;
import com.palomaregis.tqi_evolution_backend_2021.services.exceptions.DatabaseException;
import com.palomaregis.tqi_evolution_backend_2021.services.exceptions.ResourceNotFoundException;

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

	public void processamentoEmprestimo(Emprestimo obj) {
		double valorParcela = obj.getPrice() / obj.getQuantidadeParcelas();
        for (int i = 1; i <= obj.getQuantidadeParcelas(); i++) {
            Date date = adicionarMeses(obj.getDataPrimeiraParcela(), i);
            Parcela parc = new Parcela(null, date, valorParcela, obj); 
            obj.adiconarParcelaContrato(parc);
        }
	}
	
	private Date adicionarMeses(Date dataPrimeiraParcela, int i) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dataPrimeiraParcela);
		cal.add(Calendar.MONTH, i);
		return cal.getTime();
	}
	
	public Long numeroMeses(Date dataInicial, Date dataFinal) {
		long num2 = dataInicial.toInstant().toEpochMilli();
		Long num1 = dataFinal.toInstant().toEpochMilli();
		Long numeroDeMeses = num1 - num2;
		return numeroDeMeses;
	}
	
	public Emprestimo insert(Emprestimo obj) {
		if(obj.getQuantidadeParcelas() > 60) {
			throw new IllegalArgumentException("Quantidade de parcelas não pode ser maior que 60");
		}else if(numeroMeses(obj.getDataSolicitacaoEmprestimo(), obj.getDataPrimeiraParcela()) > 7889400000L) {
			throw new IllegalArgumentException("Data da primeira parcela deve ser no máximo 3 meses após o dia atual");
		}else {
			processamentoEmprestimo(obj);
			repository.save(obj);
			return obj;
		}
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
	
	public Emprestimo update(Long id, Emprestimo obj) {
		try {
			Emprestimo entity = repository.getOne(id);//nao exclui de imediato podendo mexer antes de efetuar operação no banco
			updateData(entity, obj);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id); 
		}
	}

	private void updateData(Emprestimo entity, Emprestimo obj) {
		entity.setQuantidadeParcelas(obj.getQuantidadeParcelas());
	}
}
