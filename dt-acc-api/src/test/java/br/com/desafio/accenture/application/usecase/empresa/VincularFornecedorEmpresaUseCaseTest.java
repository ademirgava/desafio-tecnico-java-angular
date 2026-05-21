package br.com.desafio.accenture.application.usecase.empresa;

import java.time.LocalDate;
import java.util.List;

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
import br.com.desafio.accenture.domain.model.Fornecedor;
import br.com.desafio.accenture.domain.model.TipoPessoa;
import br.com.desafio.accenture.domain.repository.empresa.RepositorioDeEmpresa;

@ExtendWith(MockitoExtension.class)
public class VincularFornecedorEmpresaUseCaseTest {

	private static final Long ID_EMPRESA = 1l;
	private static final Long ID_FORNECEDOR = 2l;
	private static final String CIDADE_PARANA = "PARANA";
	private static final LocalDate IDADE_MENOR = LocalDate.now();
	private static final LocalDate IDADE_MAIOR = LocalDate.now().minusYears(19);
	private static final String NOME = "Nome";
	private static final String CEP = "12345-000";
	private static final String CPF = "22543256721";
	private static final String EMAIL = "teste@test.com";
	private static final String CNPJ = "12345678900987";
	
	@Mock
	private RepositorioDeEmpresa repositorioDeEmpresa;

	@Mock
	private BuscarPorIdFornecedorUseCase buscarPorIdFornecedorUseCase;

	@Mock
	private BuscarPorIdEmpresaUseCase buscarPorIdEmpresaUseCase;

	@InjectMocks
	private VincularFornecedorEmpresaUseCase vincularFornecedorEmpresaUseCase;

	@Test
	public void naoDeveVincularComFornecedorIdNull() {
		Assertions.assertThrows(ValidacaoException.class,
				() -> this.vincularFornecedorEmpresaUseCase.vincular(null, ID_EMPRESA));
	}

	@Test
	public void naoDeveVincularComFornecedorNaoEncontrado() {
		Mockito.when(this.buscarPorIdEmpresaUseCase.buscarPorId(ID_EMPRESA))
				.thenReturn(givenEmpresaResponse(CIDADE_PARANA));
		Mockito.when(this.buscarPorIdFornecedorUseCase.buscarPorId(ID_FORNECEDOR))
				.thenReturn(givenFornecedor(IDADE_MENOR));
		Assertions.assertThrows(ValidacaoException.class, () -> this.vincularFornecedorEmpresaUseCase
				.vincular(new FornecedorIdRequestDTO(ID_FORNECEDOR), ID_EMPRESA));
	}

	@Test
	public void naoDeveVincularComFornecedorJaVinculadoParaEmpresa() {
		Mockito.when(this.buscarPorIdEmpresaUseCase.buscarPorId(ID_EMPRESA))
				.thenReturn(givenEmpresaResponse(CIDADE_PARANA));
		Mockito.when(this.buscarPorIdFornecedorUseCase.buscarPorId(ID_FORNECEDOR))
				.thenReturn(givenFornecedor(IDADE_MAIOR));
		Mockito.when(this.repositorioDeEmpresa.existsByIdAndFornecedorId(ID_EMPRESA, ID_FORNECEDOR)).thenReturn(true);

		Assertions.assertThrows(ValidacaoException.class, () -> this.vincularFornecedorEmpresaUseCase
				.vincular(new FornecedorIdRequestDTO(ID_FORNECEDOR), ID_EMPRESA));
	}

	@Test
	public void naoDeveVincularFornecedorComCidadeParanaMaoirParaEmpresa() {
		Mockito.when(this.buscarPorIdEmpresaUseCase.buscarPorId(ID_EMPRESA))
				.thenReturn(givenEmpresaResponse(CIDADE_PARANA));
		Mockito.when(this.buscarPorIdFornecedorUseCase.buscarPorId(ID_FORNECEDOR))
				.thenReturn(givenFornecedor(IDADE_MAIOR));
		Mockito.when(this.repositorioDeEmpresa.existsByIdAndFornecedorId(ID_EMPRESA, ID_FORNECEDOR)).thenReturn(false);
		Mockito.when(this.repositorioDeEmpresa.adicionarFornecedor(ID_FORNECEDOR, ID_EMPRESA))
				.thenReturn(givenEmpresaResponseDTO());

		EmpresaResponseDTO empresaResponseDTO = this.vincularFornecedorEmpresaUseCase
				.vincular(new FornecedorIdRequestDTO(ID_FORNECEDOR), ID_EMPRESA);

		Assertions.assertEquals(CNPJ, empresaResponseDTO.cnpj());
		Assertions.assertEquals(NOME, empresaResponseDTO.nomeFantasia());
		Assertions.assertEquals(CEP, empresaResponseDTO.cep());
		Assertions.assertEquals(CIDADE_PARANA, empresaResponseDTO.cidade());
		Assertions.assertEquals(ID_FORNECEDOR, empresaResponseDTO.fornecedores().get(0).id());
	}

	private Empresa givenEmpresaResponseDTO() {
		return new Empresa(ID_EMPRESA, CNPJ, NOME, CEP, CIDADE_PARANA, givenListaFornecedor());
	}

	private List<Fornecedor> givenListaFornecedor() {
		return List.of(new Fornecedor(ID_FORNECEDOR, TipoPessoa.FISICA, null, CPF, NOME, CEP, EMAIL, CIDADE_PARANA, IDADE_MAIOR));
	}

	private EmpresaResponseDTO givenEmpresaResponse(String cidade) {
		return new EmpresaResponseDTO(ID_EMPRESA, null, null, null, cidade, null);
	}

	private FornecedorResponseDTO givenFornecedor(LocalDate idade) {
		return new FornecedorResponseDTO(ID_EMPRESA, TipoPessoa.FISICA, null, null, null, null, null, null, idade,
				null);
	}
}
