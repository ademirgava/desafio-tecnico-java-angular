package br.com.desafio.accenture.application.usecase.empresa;

import java.util.stream.Collectors;

import br.com.desafio.accenture.application.dto.ListaResponseDTO;
import br.com.desafio.accenture.application.dto.empresa.EmpresaResponseDTO;
import br.com.desafio.accenture.domain.repository.empresa.RepositorioDeEmpresa;

public class ListarEmpresaUseCase {
	
	private final RepositorioDeEmpresa repositorioDeEmpresa;

	public ListarEmpresaUseCase(RepositorioDeEmpresa repositorioDeEmpresa) {
		this.repositorioDeEmpresa = repositorioDeEmpresa;
	}
	
	public ListaResponseDTO<EmpresaResponseDTO> listar() {
		return new ListaResponseDTO<EmpresaResponseDTO>(this.repositorioDeEmpresa.listarTodas().stream().map(EmpresaResponseDTO::new).collect(Collectors.toList()));
	}
}