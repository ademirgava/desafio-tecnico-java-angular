package br.com.desafio.accenture.application.usecase.empresa;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.desafio.accenture.application.dto.empresa.EmpresaResponseDTO;
import br.com.desafio.accenture.domain.exception.ValidacaoException;
import br.com.desafio.accenture.domain.model.Empresa;
import br.com.desafio.accenture.domain.repository.empresa.RepositorioDeEmpresa;

@ExtendWith(MockitoExtension.class)
public class BuscarPorIdEmpresaUseCaseTest {
	private static final String CNPJ = "12345678900987";
	private static final Long ID = 1L;
	private static final String NOME = "Nome";
	private static final String CEP = "12345-000";
	private static final String CIDADE = "Cerquilho";

	@Mock
	private RepositorioDeEmpresa repositorioDeEmpresa;

	@InjectMocks
	private BuscarPorIdEmpresaUseCase buscarPorIdEmpresaUseCase;

	@Test
	public void deveRetornarExecptionComEmpresaNaoEncontrada() {
		Mockito.when(this.repositorioDeEmpresa.buscarPorId(ID)).thenReturn(Optional.empty());

		Assertions.assertThrows(ValidacaoException.class, () -> this.buscarPorIdEmpresaUseCase.buscarPorId(ID));
	}

	@Test
	public void deveRetornarEmpresa() {
		Mockito.when(this.repositorioDeEmpresa.buscarPorId(ID)).thenReturn(givenEmpresa());		
		
		EmpresaResponseDTO empresaResponseDTO = this.buscarPorIdEmpresaUseCase.buscarPorId(ID);
		
		Assertions.assertEquals(CNPJ, empresaResponseDTO.cnpj());
		Assertions.assertEquals(NOME, empresaResponseDTO.nomeFantasia());
		Assertions.assertEquals(CEP, empresaResponseDTO.cep());
		Assertions.assertEquals(CIDADE, empresaResponseDTO.cidade());
	}

	private Optional<Empresa> givenEmpresa() {
		Empresa empresa = new Empresa(CNPJ, NOME, CEP, CIDADE, null);
		return Optional.of(empresa);
	}
}
