package br.com.desafio.accenture.application.dto.empresa;

import java.util.List;

import br.com.desafio.accenture.application.dto.fornecedor.FornecedorIdRequestDTO;

public record EmpresaRequestDTO(String cnpj, String nomeFantasia, String cep, String cidade,
		List<FornecedorIdRequestDTO> fornecedores) {

}
