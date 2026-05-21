package br.com.desafio.accenture.domain.valueobject;

import br.com.desafio.accenture.domain.exception.ValidacaoException;

public class Cpf {

	private final String cpf;
	
	public Cpf(String cpf) {
		if (cpf == null || cpf.length() != 11) {
			throw new ValidacaoException("CPF inválido!");
		}
		
		this.cpf = cpf;
	}
	
	public String getValue() {
		return cpf;
	}
}
