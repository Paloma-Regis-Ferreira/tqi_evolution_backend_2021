package com.palomaregis.tqi_evolution_backend_2021.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.palomaregis.tqi_evolution_backend_2021.entities.Cliente;
import com.palomaregis.tqi_evolution_backend_2021.entities.Emprestimo;
import com.palomaregis.tqi_evolution_backend_2021.repositories.ClienteRepository;
import com.palomaregis.tqi_evolution_backend_2021.repositories.EmprestimoRepository;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner{
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EmprestimoRepository emprestimoRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Cliente c1 = new Cliente (null, "Pamela", "pamela.com", "444.222.555.33", "44.777.333-Z", "av. X, n 123", 2000.00, 123456);
		Cliente c2 = new Cliente (null, "Marisa", "marisa.com", "444.222.555.33", "44.777.333-Z", "av. X, n 123", 2000.00, 123456);
		
		clienteRepository.saveAll(Arrays.asList(c1, c2));
		
		Emprestimo emp = new Emprestimo(null, 200.00, sdf.parse("05/01/2022"), 2, c2);
		
		emprestimoRepository.saveAll(Arrays.asList(emp));
	}
}
