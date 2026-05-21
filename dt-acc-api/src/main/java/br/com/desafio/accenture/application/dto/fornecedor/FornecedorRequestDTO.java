package br.com.desafio.accenture.application.dto.fornecedor;

import java.time.LocalDate;

import br.com.desafio.accenture.domain.model.Fornecedor;
import br.com.desafio.accenture.domain.model.TipoPessoa;

public record FornecedorRequestDTO(TipoPessoa tipoPessoa, String cnpj, String cpf, String nome, String cep,
		String email, String rg, LocalDate dataNascimento) {

	public FornecedorRequestDTO(Fornecedor fornecedor) {
		this(fornecedor.getTipoPessoa(), fornecedor.getCnpj() != null ? fornecedor.getCnpj().getValue() : null, fornecedor.getCpf() != null ? fornecedor.getCpf().getValue() : null,
				fornecedor.getNome(), fornecedor.getCep(), fornecedor.getEmail(), fornecedor.getRg(),
				fornecedor.getDataNascimento());
	}

}
