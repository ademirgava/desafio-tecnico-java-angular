package br.com.desafio.accenture.application.usecase.empresa;

import java.util.stream.Collectors;

import br.com.desafio.accenture.application.dto.empresa.EmpresaRequestDTO;
import br.com.desafio.accenture.application.dto.empresa.EmpresaResponseDTO;
import br.com.desafio.accenture.domain.exception.ValidacaoException;
import br.com.desafio.accenture.domain.model.Empresa;
import br.com.desafio.accenture.domain.model.Fornecedor;
import br.com.desafio.accenture.domain.repository.empresa.RepositorioDeEmpresa;

public class CriarEmpresaUseCase {

	private RepositorioDeEmpresa repositorioDeEmpresa;

	public CriarEmpresaUseCase(RepositorioDeEmpresa repositorioDeEmpresa) {
		this.repositorioDeEmpresa = repositorioDeEmpresa;
	}

	public EmpresaResponseDTO cadastrar(EmpresaRequestDTO dto) {
		Empresa empresaDomain = new Empresa(dto.cnpj(), dto.nomeFantasia(), dto.cep(), dto.cidade(),
				dto.fornecedores() != null ? dto.fornecedores().stream().map((item) -> new Fornecedor(item.idFornecedor())).collect(Collectors.toList()) : null);

		if (this.repositorioDeEmpresa.existsByCnpj(empresaDomain.getCnpj()))
			throw new ValidacaoException("Empresa com o CNPJ: " + empresaDomain.getCnpj() + " já cadastrada!");
		
		Empresa empresaSalva = repositorioDeEmpresa.salvar(empresaDomain);
		
		return new EmpresaResponseDTO(empresaSalva);
	}

}
