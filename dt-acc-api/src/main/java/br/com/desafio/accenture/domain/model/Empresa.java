package br.com.desafio.accenture.domain.model;

import java.util.List;

import br.com.desafio.accenture.domain.exception.ValidacaoException;

public class Empresa {

	private Long id;
	private String cnpj;
	private String nomeFantasia;
	private String cep;
	private String cidade;
	private List<Fornecedor> fornecedores;

	public Empresa(Long id, String cnpj, String nomeFantasia, String cep, String cidade, List<Fornecedor> fornecedores) {
		this.id = id;
		this.cnpj = cnpj;
		this.nomeFantasia = nomeFantasia;
		this.cep = cep;
		this.cidade = cidade;
		this.fornecedores = fornecedores;
		
		validar();
	}
	
	public Empresa(String cnpj, String nomeFantasia, String cep, String cidade, List<Fornecedor> fornecedores) {
		this.cnpj = cnpj;
		this.nomeFantasia = nomeFantasia;
		this.cep = cep;
		this.cidade = cidade;
		this.fornecedores = fornecedores;

		validar();
	}

	private void validar() {
		if (this.cnpj == null || this.cnpj.length() != 14) {
			throw new ValidacaoException("CNPJ inválido!");
		}
		
		if (this.cep != null && this.cep.length() != 9) {
			throw new ValidacaoException("CEP inválido!");
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public void addFornecedor(Fornecedor fornecedor) {
		this.fornecedores.add(fornecedor);
	}

}
