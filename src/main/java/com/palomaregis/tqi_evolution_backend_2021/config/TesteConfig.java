package com.palomaregis.tqi_evolution_backend_2021.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.palomaregis.tqi_evolution_backend_2021.entities.Cliente;
import com.palomaregis.tqi_evolution_backend_2021.repositories.ClienteRepository;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner{
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void run(String... args) throws Exception {
		Cliente c1 = new Cliente (1L, "Pamela", "pamela.com", "444.222.555.33", "44.777.333-Z", "av. X, n 123", 2000.00, 123456);
		Cliente c2 = new Cliente (2L, "Marisa", "marisa.com", "444.222.555.33", "44.777.333-Z", "av. X, n 123", 2000.00, 123456);
		
		clienteRepository.saveAll(Arrays.asList(c1, c2));
	}
}
