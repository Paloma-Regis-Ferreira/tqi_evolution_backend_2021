package com.palomaregis.tqi_evolution_backend_2021.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "tb_emprestimo")
public class Emprestimo implements Serializable{
	
	private static final long serialVersionUID = 1L; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Calendar dataSolicitacaoEmprestimo;
	private Double price;
	private Date dataPrimeiraParcela;
	private Integer quantidadeParcelas;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@JsonIgnore
	@OneToMany(mappedBy = "emprestimo")
	private List<Parcela> parcelas = new ArrayList<>();
	
	public Emprestimo() {
	}

	public Emprestimo(Long id, Double price, Date dataPrimeiraParcela, Integer quantidadeParcelas, Cliente cliente) {
		this.id = id;
		this.dataSolicitacaoEmprestimo = Calendar.getInstance();
		this.price = price;
		this.dataPrimeiraParcela = dataPrimeiraParcela;
		this.quantidadeParcelas = quantidadeParcelas;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Calendar getDataSolicitacaoEmprestimo() {
		return dataSolicitacaoEmprestimo;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Date getDataPrimeiraParcela() {
		return dataPrimeiraParcela;
	}

	public void setDataPrimeiraParcela(Date dataPrimeiraParcela) {
		this.dataPrimeiraParcela = dataPrimeiraParcela;
	}

	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(Integer quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Parcela> getParcelas() {
		return parcelas;
	}
	
	public void adiconarParcelaContrato(Parcela parcela) {
		parcelas.add(parcela);
	}

	public void removerParcelaContrato(Parcela parcela) {
		parcelas.remove(parcela);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emprestimo other = (Emprestimo) obj;
		return Objects.equals(id, other.id);
	}
	
	 
}
