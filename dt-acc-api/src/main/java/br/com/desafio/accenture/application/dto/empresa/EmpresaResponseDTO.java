package br.com.desafio.accenture.application.dto.empresa;

import java.util.List;

import br.com.desafio.accenture.application.dto.fornecedor.FornecedorResponseDTO;
import br.com.desafio.accenture.domain.model.Empresa;

public record EmpresaResponseDTO(Long id, String cnpj, String nomeFantasia, String cep, String cidade,
		List<FornecedorResponseDTO> fornecedores) {

	public EmpresaResponseDTO(Empresa empresa) {
		this(empresa.getId(), empresa.getCnpj(), empresa.getNomeFantasia(), empresa.getCep(), empresa.getCidade(),
				empresa.getFornecedores() != null ? empresa.getFornecedores().stream().map(FornecedorResponseDTO::new).toList(): null);
	}

}
