package com.palomaregis.tqi_evolution_backend_2021.entities;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_parcela")
public class Parcela {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date data;
	private Double price;
	
	@ManyToOne
	@JoinColumn(name = "emprestimo_id")
	private Emprestimo emprestimo;
	
	public Parcela() {
	}

	public Parcela(Long id, Date data, Double price, Emprestimo emprestimo) {
		super();
		this.id = id;
		this.data = data;
		this.price = price;
		this.emprestimo = emprestimo;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
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
		Parcela other = (Parcela) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Parcelas [id=" + id + ", data=" + data + ", price=" + price + ", emprestimo=" + emprestimo + "]";
	}
	
	
}
