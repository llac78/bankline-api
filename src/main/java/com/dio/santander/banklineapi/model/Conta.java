package com.dio.santander.banklineapi.model;

import javax.persistence.Embeddable;

@Embeddable
public class Conta {

	private Long numero;
	private Double saldo;
	
	
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	
}
