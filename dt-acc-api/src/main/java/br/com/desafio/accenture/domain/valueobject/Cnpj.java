package br.com.desafio.accenture.domain.valueobject;

import br.com.desafio.accenture.domain.exception.ValidacaoException;

public class Cnpj {

	private final String cnpj;
	
	public Cnpj(String cnpj) {
		if (cnpj == null || cnpj.length() != 14) {
			throw new ValidacaoException("CNPJ inválido!");
		}
		
		this.cnpj = cnpj;
	}
	
	public String getValue() {
		return cnpj;
	}
}
