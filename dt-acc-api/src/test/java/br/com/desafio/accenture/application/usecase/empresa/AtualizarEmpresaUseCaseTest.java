package br.com.desafio.accenture.application.usecase.empresa;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.desafio.accenture.application.dto.empresa.EmpresaRequestDTO;
import br.com.desafio.accenture.application.dto.empresa.EmpresaResponseDTO;
import br.com.desafio.accenture.domain.exception.ValidacaoException;
import br.com.desafio.accenture.domain.model.Empresa;
import br.com.desafio.accenture.domain.repository.empresa.RepositorioDeEmpresa;

@ExtendWith(MockitoExtension.class)
public class AtualizarEmpresaUseCaseTest {

	private static final String CNPJ = "12345678900987";
	private static final Long ID = 1L;
	private static final String NOME = "Nome";
	private static final String CEP = "12345-000";
	private static final String CIDADE = "Cerquilho";
	
	@Mock
	private RepositorioDeEmpresa repositorioDeEmpresa;
	
	@InjectMocks
	private AtualizarEmpresaUseCase atualizarEmpresaUseCase;
	
	@Test
	public void deveAtualizarEmpresa() {
		Mockito.when(this.repositorioDeEmpresa.existsByCnpjAndNotId(CNPJ, ID)).thenReturn(false);
		Mockito.when(this.repositorioDeEmpresa.atualizar(any())).thenReturn(givenEmpresa());
		EmpresaResponseDTO responseDTO = this.atualizarEmpresaUseCase.atualizar(givenRequestDTO(), ID);
		
		Assertions.assertEquals(CNPJ, responseDTO.cnpj());
	}

	@Test
	public void naoDeveAtualizarComCnpjJaCadastrado() {
		Mockito.when(this.repositorioDeEmpresa.existsByCnpjAndNotId(CNPJ, ID)).thenReturn(true);
		Assertions.assertThrows(ValidacaoException.class, () -> this.atualizarEmpresaUseCase.atualizar(givenRequestDTO(), ID));
	}
	
	private EmpresaRequestDTO givenRequestDTO() {
		return new EmpresaRequestDTO(CNPJ, NOME, CEP, CIDADE, null);
	}

	private Empresa givenEmpresa() {
		return new Empresa(ID, CNPJ, NOME, CEP, CIDADE, null);
	}
}
