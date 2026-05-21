package br.com.desafio.accenture.application.usecase.empresa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.desafio.accenture.application.dto.empresa.EmpresaResponseDTO;
import br.com.desafio.accenture.application.dto.fornecedor.FornecedorIdRequestDTO;
import br.com.desafio.accenture.application.dto.fornecedor.FornecedorResponseDTO;
import br.com.desafio.accenture.application.usecase.fornecedor.BuscarPorIdFornecedorUseCase;
import br.com.desafio.accenture.domain.exception.ValidacaoException;
import br.com.desafio.accenture.domain.model.Empresa;
import br.com.desafio.accenture.domain.model.TipoPessoa;
import br.com.desafio.accenture.domain.repository.empresa.RepositorioDeEmpresa;

@ExtendWith(MockitoExtension.class)
public class DesvincularFornecedorEmpresaUseCaseTest {

	private static final Long ID_EMPRESA = 1l;
	private static final Long ID_FORNECEDOR = 2l;
	private static final String CIDADE = "CERQUILHO";
	private static final String NOME = "Nome";
	private static final String CEP = "12345-000";
	private static final String CNPJ = "12345678900987";

	@Mock
	private RepositorioDeEmpresa repositorioDeEmpresa;

	@Mock
	private BuscarPorIdFornecedorUseCase buscarPorIdFornecedorUseCase;

	@InjectMocks
	private DesvincularFornecedorEmpresaUseCase desvincularFornecedorEmpresaUseCase;

	@Test
	public void naoDeveVincularComFornecedorIdNull() {
		Assertions.assertThrows(ValidacaoException.class,
				() -> this.desvincularFornecedorEmpresaUseCase.desvincular(null, ID_EMPRESA));
	}

	@Test
	public void naoDeveDesvincularComFornecedorNaoVinculado() {
		Mockito.when(this.buscarPorIdFornecedorUseCase.buscarPorId(ID_FORNECEDOR)).thenReturn(givenFornecedor());
		Mockito.when(this.repositorioDeEmpresa.existsByIdAndFornecedorId(ID_EMPRESA, ID_FORNECEDOR)).thenReturn(false);
		Assertions.assertThrows(ValidacaoException.class, () -> this.desvincularFornecedorEmpresaUseCase
				.desvincular(new FornecedorIdRequestDTO(ID_FORNECEDOR), ID_EMPRESA));
	}

	@Test
	public void deveDesvincularFornecedorDaEmpresa() {
		Mockito.when(this.buscarPorIdFornecedorUseCase.buscarPorId(ID_FORNECEDOR)).thenReturn(givenFornecedor());
		Mockito.when(this.repositorioDeEmpresa.existsByIdAndFornecedorId(ID_EMPRESA, ID_FORNECEDOR)).thenReturn(true);
		Mockito.when(this.repositorioDeEmpresa.removerFornecedor(ID_FORNECEDOR, ID_EMPRESA))
		.thenReturn(givenEmpresaResponseDTO());
		
		EmpresaResponseDTO empresaResponseDTO = this.desvincularFornecedorEmpresaUseCase.desvincular(new FornecedorIdRequestDTO(ID_FORNECEDOR), ID_EMPRESA);
		
		Mockito.verify(this.repositorioDeEmpresa).removerFornecedor(ID_FORNECEDOR, ID_EMPRESA);
		
		Assertions.assertEquals(CNPJ, empresaResponseDTO.cnpj());
		Assertions.assertEquals(NOME, empresaResponseDTO.nomeFantasia());
		Assertions.assertEquals(CEP, empresaResponseDTO.cep());
		Assertions.assertEquals(CIDADE, empresaResponseDTO.cidade());
		Assertions.assertNull(empresaResponseDTO.fornecedores());
	}

	private FornecedorResponseDTO givenFornecedor() {
		return new FornecedorResponseDTO(ID_FORNECEDOR, TipoPessoa.FISICA, null, null, null, null, null, null, null,
				null);
	}

	private Empresa givenEmpresaResponseDTO() {
		return new Empresa(ID_EMPRESA, CNPJ, NOME, CEP, CIDADE, null);
	}
}
