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
public class CriarEmpresaUseCaseTest {
	
	private static final String CNPJ = "12345678900987";
	private static final String CNPJ_NULL = null;
	private static final String CNPJ_INVALIDO = "1234567897";
	private static final Long ID = 1L;
	private static final String NOME = "Nome";
	private static final String CEP = "12345-000";
	private static final String CEP_INVALIDO = "12345-0000";
	private static final String CIDADE = "Cerquilho";

	@Mock
	private RepositorioDeEmpresa repositorioDeEmpresa;
	
	@InjectMocks
	private CriarEmpresaUseCase criarEmpresaUseCase;
	
	@Test
	public void naoDeveCadastrarComCnpjInvalido() {
		Assertions.assertThrows(ValidacaoException.class, () -> this.criarEmpresaUseCase.cadastrar(givenRequest(CNPJ_INVALIDO)));
	}
	
	@Test
	public void naoDeveCadastrarComCnpjNull() {
		Assertions.assertThrows(ValidacaoException.class, () -> this.criarEmpresaUseCase.cadastrar(givenRequest(CNPJ_NULL)));
	}
	
	@Test
	public void naoDeveCadastrarComCepInvalido() {
		Assertions.assertThrows(ValidacaoException.class, () -> this.criarEmpresaUseCase.cadastrar(givenRequestCepInvalido()));
	}
	
	@Test
	public void naoDeveCadastrarComCnpjJaCadastrado() {
		Mockito.when(this.repositorioDeEmpresa.existsByCnpj(CNPJ)).thenReturn(true);
		Assertions.assertThrows(ValidacaoException.class, () -> this.criarEmpresaUseCase.cadastrar(givenRequest(CNPJ)));   
	}

	@Test
	public void deveCadastrarComCnpjOk() {
		Mockito.when(this.repositorioDeEmpresa.existsByCnpj(CNPJ)).thenReturn(false);
		Mockito.when(this.repositorioDeEmpresa.salvar(any())).thenReturn(givenEmpresa());
		EmpresaResponseDTO empresaResponseDTO = this.criarEmpresaUseCase.cadastrar(givenRequest(CNPJ));
		
		Assertions.assertEquals(CNPJ, empresaResponseDTO.cnpj());
		Assertions.assertEquals(NOME, empresaResponseDTO.nomeFantasia());
		Assertions.assertEquals(CEP, empresaResponseDTO.cep());
		Assertions.assertEquals(CIDADE, empresaResponseDTO.cidade());
	}
	
	private Empresa givenEmpresa() {
		return new Empresa(ID, CNPJ, NOME, CEP, CIDADE, null);
	}

	private EmpresaRequestDTO givenRequest(String cnpj) {
		return new EmpresaRequestDTO(cnpj, NOME, CEP, CIDADE, null);
	}
	
	private EmpresaRequestDTO givenRequestCepInvalido() {
		return new EmpresaRequestDTO(CNPJ, NOME, CEP_INVALIDO, CIDADE, null);
	}

}
