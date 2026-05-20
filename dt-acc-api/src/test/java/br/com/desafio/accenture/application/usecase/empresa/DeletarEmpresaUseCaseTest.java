package br.com.desafio.accenture.application.usecase.empresa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.desafio.accenture.domain.repository.empresa.RepositorioDeEmpresa;

@ExtendWith(MockitoExtension.class)
public class DeletarEmpresaUseCaseTest {

	private static final Long ID = 1l;

	@Mock
	private RepositorioDeEmpresa repositorioDeEmpresa;
	
	@InjectMocks
	private DeletarEmpresaUseCase deletarEmpresaUseCase;
	
	@Test
	public void deveDeletarEmpresa() {
		this.deletarEmpresaUseCase.deletar(ID);
		Mockito.verify(this.repositorioDeEmpresa).deletar(ID);
	}
}
