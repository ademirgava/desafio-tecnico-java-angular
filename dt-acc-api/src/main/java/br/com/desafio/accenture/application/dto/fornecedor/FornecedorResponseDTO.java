package br.com.desafio.accenture.application.dto.fornecedor;

import java.time.LocalDate;
import java.util.List;

import br.com.desafio.accenture.application.dto.empresa.EmpresaResponseDTO;
import br.com.desafio.accenture.domain.model.Fornecedor;
import br.com.desafio.accenture.domain.model.TipoPessoa;

public record FornecedorResponseDTO(Long id, TipoPessoa tipoPessoa, String cnpj, String cpf, String nome, String cep,
		String email, String rg, LocalDate dataNascimento, List<EmpresaResponseDTO> empresas) {

	public FornecedorResponseDTO(Fornecedor fornecedor) {
		this(fornecedor.getId(), fornecedor.getTipoPessoa(), fornecedor.getCnpj(), fornecedor.getCpf(),
				fornecedor.getNome(), fornecedor.getCep(), fornecedor.getEmail(), fornecedor.getRg(),
				fornecedor.getDataNascimento(), null);
	}

}
