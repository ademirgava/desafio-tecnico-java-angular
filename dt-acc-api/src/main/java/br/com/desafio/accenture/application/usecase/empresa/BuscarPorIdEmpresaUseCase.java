package br.com.desafio.accenture.application.usecase.empresa;

import java.util.Optional;

import br.com.desafio.accenture.application.dto.empresa.EmpresaResponseDTO;
import br.com.desafio.accenture.domain.exception.ValidacaoException;
import br.com.desafio.accenture.domain.model.Empresa;
import br.com.desafio.accenture.domain.repository.empresa.RepositorioDeEmpresa;

public class BuscarPorIdEmpresaUseCase {
	
	private final RepositorioDeEmpresa repositorioDeEmpresa;

	public BuscarPorIdEmpresaUseCase(RepositorioDeEmpresa repositorioDeEmpresa) {
		this.repositorioDeEmpresa = repositorioDeEmpresa;
	}
	
	public EmpresaResponseDTO buscarPorId(Long id) {
		Optional<Empresa> opEmpresa = this.repositorioDeEmpresa.buscarPorId(id);
		if (opEmpresa.isEmpty()) 
			throw new ValidacaoException("Empresa com id: " +id+" não encontrada!");
		
		Empresa empresa = opEmpresa.get();
		return new EmpresaResponseDTO(empresa);
	}
}