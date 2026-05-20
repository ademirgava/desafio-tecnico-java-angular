package br.com.desafio.accenture.domain.model;

import java.time.LocalDate;
import java.util.List;

import br.com.desafio.accenture.domain.exception.ValidacaoException;

public class Fornecedor {
	private Long id;
	private TipoPessoa tipoPessoa;
	private String cnpj;
	private String cpf;
	private String nome;
	private String cep;
	private String email;
	private String rg;
	private LocalDate dataNascimento;
	private List<Empresa> empresas;

	public Fornecedor(TipoPessoa tipoPessoa, String cnpj, String cpf, String nome, String cep, String email, String rg,
			LocalDate dataNascimento) {
		this.tipoPessoa = tipoPessoa;
		this.cnpj = cnpj;
		this.cpf = cpf;
		this.nome = nome;
		this.cep = cep;
		this.email = email;
		this.rg = rg;
		this.dataNascimento = dataNascimento;

		validar();
	}

	public Fornecedor(Long idFornecedor) {
		this.id = idFornecedor;
	}

	private void validar() {
		if (TipoPessoa.FISICA.equals(tipoPessoa)) {
			if (this.cpf == null || this.cpf.length() != 11) {
				throw new ValidacaoException("CPF inválido!");
			}
			if (this.rg == null) {
				throw new ValidacaoException("RG é obrigatório para pessoa fisíca!");
			}
			if (this.dataNascimento == null) {
				throw new ValidacaoException("Data de nascimento é obrigatório para pessoa fisíca!");
			}
		} else {
			if (this.cnpj == null || this.cnpj.length() != 14) {
				throw new ValidacaoException("CNPJ inválido!");
			}
		}

		if (this.email != null && this.email.contains("@")) {
			throw new RuntimeException("E-mail inválido!");
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

}
