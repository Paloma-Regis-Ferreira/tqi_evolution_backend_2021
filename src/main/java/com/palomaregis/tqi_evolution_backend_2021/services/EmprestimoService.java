package com.palomaregis.tqi_evolution_backend_2021.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.palomaregis.tqi_evolution_backend_2021.entities.Cliente;
import com.palomaregis.tqi_evolution_backend_2021.entities.Emprestimo;
import com.palomaregis.tqi_evolution_backend_2021.entities.Parcela;
import com.palomaregis.tqi_evolution_backend_2021.repositories.EmprestimoRepository;

//regras de negocio e requisições ao bd

@Service
public class EmprestimoService {
	
	@Autowired
	private EmprestimoRepository repository;
	
	@Autowired
	private ClienteService clienteService;
	
	public List<Emprestimo> findAll(){
		return repository.findAll();
	}
	
	public Emprestimo findById(Long id) {
		Optional<Emprestimo> obj = repository.findById(id);
		return obj.get();
	}
	
	public Emprestimo solicitarEmprestimo(Emprestimo obj) {
		try {
			Cliente cliente = clienteService.repository.getOne(obj.getCliente().getId());
			return insert(new Emprestimo(null, obj.getPrice(), obj.getDataPrimeiraParcela(), obj.getQuantidadeParcelas(), cliente));
		}catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("CPF não existe");
		}
		
		/*List<Cliente>clientes = clienteService.findAll();
		for(Cliente cliente : clientes) {
			if(cliente.getId() == id) {
				return insert(new Emprestimo(null, price, dataPrimeiraParcela, quantidadeParcelas, cliente));
			}		
		}
		
		throw new IllegalArgumentException("CPF não existe");*/
	}
	
	public Emprestimo insert(Emprestimo obj) {
		if(obj.getQuantidadeParcelas() > 60) {
			throw new IllegalArgumentException("Quantidade de parcelas não pode ser maior que 60");
		}else if(obj.getDataSolicitacaoEmprestimo().get(Calendar.MONTH) - obj.getDataPrimeiraParcela().getMonth() > 3) {
			throw new IllegalArgumentException("Data da primeira parcela deve ser no máximo 3 meses após o dia atual");
		}else {
			processamentoEmprestimo(obj);
		}
		return repository.save(obj);
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
}
