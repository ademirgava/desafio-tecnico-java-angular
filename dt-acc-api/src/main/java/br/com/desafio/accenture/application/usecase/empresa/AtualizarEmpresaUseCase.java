package br.com.desafio.accenture.application.usecase.empresa;

import java.util.stream.Collectors;

import br.com.desafio.accenture.application.dto.empresa.EmpresaRequestDTO;
import br.com.desafio.accenture.application.dto.empresa.EmpresaResponseDTO;
import br.com.desafio.accenture.domain.exception.ValidacaoException;
import br.com.desafio.accenture.domain.model.Empresa;
import br.com.desafio.accenture.domain.model.Fornecedor;
import br.com.desafio.accenture.domain.repository.empresa.RepositorioDeEmpresa;

public class AtualizarEmpresaUseCase {

	private final RepositorioDeEmpresa repositorioDeEmpresa;

	public AtualizarEmpresaUseCase(RepositorioDeEmpresa repositorioDeEmpresa) {
		this.repositorioDeEmpresa = repositorioDeEmpresa;
	}
	
	public EmpresaResponseDTO atualizar(EmpresaRequestDTO dto, Long empresaId) {
		if (this.repositorioDeEmpresa.existsByCnpjAndNotId(dto.cnpj(), empresaId)) 
			throw new ValidacaoException("Já existe uma empresa com o CNPJ: " + dto.cnpj());
			 
		Empresa empresaDomain = new Empresa(empresaId, dto.cnpj(), dto.nomeFantasia(), dto.cep(), dto.cidade(),
				dto.fornecedores() != null ? dto.fornecedores().stream().map((item) -> new Fornecedor(item.idFornecedor())).collect(Collectors.toList()) : null);
		
		Empresa empresaSalva = this.repositorioDeEmpresa.atualizar(empresaDomain);
		return new EmpresaResponseDTO(empresaSalva);
	}
}
