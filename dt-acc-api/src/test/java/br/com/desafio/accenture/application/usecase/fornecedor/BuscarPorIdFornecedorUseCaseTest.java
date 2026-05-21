package br.com.desafio.accenture.application.usecase.fornecedor;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.desafio.accenture.application.dto.fornecedor.FornecedorResponseDTO;
import br.com.desafio.accenture.domain.exception.ValidacaoException;
import br.com.desafio.accenture.domain.model.Fornecedor;
import br.com.desafio.accenture.domain.model.TipoPessoa;
import br.com.desafio.accenture.domain.repository.fornecedor.RepositorioDeFornecedor;

@ExtendWith(MockitoExtension.class)
public class BuscarPorIdFornecedorUseCaseTest {

	private static final Long ID = 2l;

	private static final TipoPessoa TIPO_PESSOAL_FISICA = TipoPessoa.FISICA;
	private static final String CPF = "12345678909";
	private static final String NOME = "Nome";
	private static final String CEP = "00000-000";
	private static final String EMAIL = "teste@test";
	private static final String RG = "234567892";
	private static final LocalDate NASCIMENTO = LocalDate.now();

	@Mock
	private RepositorioDeFornecedor repositorioDeFornecedor;

	@InjectMocks
	private BuscarPorIdFornecedorUseCase buscarPorIdFornecedorUseCase;

	@Test
	public void deveRetornarFornecedor() {
		Mockito.when(this.repositorioDeFornecedor.buscarPorId(ID)).thenReturn(givenFornecedor());

		FornecedorResponseDTO fornecedorResponseDTO = this.buscarPorIdFornecedorUseCase.buscarPorId(ID);

		Assertions.assertEquals(ID, fornecedorResponseDTO.id());
		Assertions.assertEquals(TIPO_PESSOAL_FISICA, fornecedorResponseDTO.tipoPessoa());
		Assertions.assertNull(fornecedorResponseDTO.cnpj());
		Assertions.assertEquals(CPF, fornecedorResponseDTO.cpf());
		Assertions.assertEquals(NOME, fornecedorResponseDTO.nome());
		Assertions.assertEquals(CEP, fornecedorResponseDTO.cep());
		Assertions.assertEquals(EMAIL, fornecedorResponseDTO.email());
		Assertions.assertEquals(RG, fornecedorResponseDTO.rg());
		Assertions.assertEquals(NASCIMENTO, fornecedorResponseDTO.dataNascimento());
	}

	@Test
	public void deveRetornarExecption() {
		Assertions.assertThrows(ValidacaoException.class, () -> this.buscarPorIdFornecedorUseCase.buscarPorId(ID));
	}

	private Optional<Fornecedor> givenFornecedor() {
		return Optional.of(new Fornecedor(ID, TIPO_PESSOAL_FISICA, null, CPF, NOME, CEP, EMAIL, RG, NASCIMENTO));
	}

}
