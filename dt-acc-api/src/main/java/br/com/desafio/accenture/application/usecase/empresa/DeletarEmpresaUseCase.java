package br.com.desafio.accenture.application.usecase.empresa;

import br.com.desafio.accenture.domain.repository.empresa.RepositorioDeEmpresa;

public class DeletarEmpresaUseCase {

	private final RepositorioDeEmpresa repositorioDeEmpresa;

	public DeletarEmpresaUseCase(RepositorioDeEmpresa repositorioDeEmpresa) {
		this.repositorioDeEmpresa = repositorioDeEmpresa;
	}

	public void deletar(Long empresaId) {
		this.repositorioDeEmpresa.deletar(empresaId);
	}
}
