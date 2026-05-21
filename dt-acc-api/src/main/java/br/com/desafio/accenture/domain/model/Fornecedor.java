package br.com.desafio.accenture.domain.model;

import java.time.LocalDate;
import java.util.List;

import br.com.desafio.accenture.domain.exception.ValidacaoException;
import br.com.desafio.accenture.domain.valueobject.Cnpj;
import br.com.desafio.accenture.domain.valueobject.Cpf;

public class Fornecedor {
	private Long id;
	private TipoPessoa tipoPessoa;
	private Cnpj cnpj;
	private Cpf cpf;
	private String nome;
	private String cep;
	private String email;
	private String rg;
	private LocalDate dataNascimento;
	private List<Empresa> empresas;

	public Fornecedor(Long id, TipoPessoa tipoPessoa, String cnpj, String cpf, String nome, String cep, String email,
			String rg, LocalDate dataNascimento) {
		this.id = id;
		this.tipoPessoa = tipoPessoa;
		this.nome = nome;
		this.cep = cep;
		this.email = email;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
		
		validar(cnpj, cpf);
	}

	public Fornecedor(TipoPessoa tipoPessoa, String cnpj, String cpf, String nome, String cep, String email, String rg,
			LocalDate dataNascimento) {
		this.tipoPessoa = tipoPessoa;
		this.nome = nome;
		this.cep = cep;
		this.email = email;
		this.rg = rg;
		this.dataNascimento = dataNascimento;

		validar(cnpj,cpf);
	}

	public Fornecedor(Long idFornecedor) {
		this.id = idFornecedor;
	}

	private void validar(String cnpj, String cpf) {
		if (TipoPessoa.FISICA.equals(tipoPessoa)) {
			if (this.rg == null) {
				throw new ValidacaoException("RG é obrigatório para pessoa fisíca!");
			}
			if (this.dataNascimento == null) {
				throw new ValidacaoException("Data de nascimento é obrigatório para pessoa fisíca!");
			}
			this.cpf = new Cpf(cpf);
		} else {
			this.cnpj = new Cnpj(cnpj);
			this.rg = null;
			this.dataNascimento = null;
		}

		if (this.email != null && !this.email.contains("@")) {
			throw new ValidacaoException("E-mail inválido!");
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

	public Cnpj getCnpj() {
		return cnpj;
	}

	public void setCnpj(Cnpj cnpj) {
		this.cnpj = cnpj;
	}

	public Cpf getCpf() {
		return cpf;
	}

	public void setCpf(Cpf cpf) {
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
