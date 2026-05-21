package br.com.desafio.accenture.application.usecase.fornecedor;

import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.desafio.accenture.application.dto.fornecedor.FornecedorRequestDTO;
import br.com.desafio.accenture.application.dto.fornecedor.FornecedorResponseDTO;
import br.com.desafio.accenture.domain.exception.ValidacaoException;
import br.com.desafio.accenture.domain.model.Fornecedor;
import br.com.desafio.accenture.domain.model.TipoPessoa;
import br.com.desafio.accenture.domain.repository.fornecedor.RepositorioDeFornecedor;

@ExtendWith(MockitoExtension.class)
public class AtualizarFornecedorUseCaseTest {

	private static final String CNPJ = "12345678900987";
	private static final String NOME = "Nome";
	private static final String CEP = "12345-000";
	private static final String CPF = "22543256721";
	private static final String RG = "340974329";
	private static final String EMAIL = "teste@test.com";
	private static final LocalDate DATA_NASCIMENTO = LocalDate.of(2000, 12, 1);
	private static final Long ID = 2l;

	@Mock
	private RepositorioDeFornecedor repositorioDeFornecedor;

	@InjectMocks
	private AtualizarFornecedorUseCase atualizarFornecedorUseCase;

	@Test
	public void naoDeveAtualizarComCnpjCadastradoEmOutroFornecedor() {
		Mockito.when(this.repositorioDeFornecedor.existsByCnpjAndIdNot(CNPJ, ID)).thenReturn(true);

		Assertions.assertThrows(ValidacaoException.class,
				() -> this.atualizarFornecedorUseCase.atualizar(givenFornecedorJuridico(), ID));
	}

	@Test
	public void naoDeveAtualizarComCpfCadastradoEmOutroFornecedor() {
		Mockito.when(this.repositorioDeFornecedor.existsByCpfAndIdNot(CPF, ID)).thenReturn(true);

		Assertions.assertThrows(ValidacaoException.class,
				() -> this.atualizarFornecedorUseCase.atualizar(givenFornecedorFisica(), ID));
	}

	@Test
	public void deveAtualizarFornecedorFisica() {
		Mockito.when(this.repositorioDeFornecedor.existsByCpfAndIdNot(CPF, ID)).thenReturn(false);
		Mockito.when(this.repositorioDeFornecedor.atualizar(any())).thenReturn(givenFornecedor());

		FornecedorResponseDTO fornecedorResponseDTO = this.atualizarFornecedorUseCase.atualizar(givenFornecedorFisica(),
				ID);
		Assertions.assertEquals(CPF, fornecedorResponseDTO.cpf());
		Assertions.assertEquals(NOME, fornecedorResponseDTO.nome());
		Assertions.assertEquals(CEP, fornecedorResponseDTO.cep());
		Assertions.assertEquals(EMAIL, fornecedorResponseDTO.email());
		Assertions.assertEquals(RG, fornecedorResponseDTO.rg());
		Assertions.assertEquals(DATA_NASCIMENTO, fornecedorResponseDTO.dataNascimento());
		Assertions.assertEquals(TipoPessoa.FISICA, fornecedorResponseDTO.tipoPessoa());
		Assertions.assertNull(fornecedorResponseDTO.cnpj());
	}

	private FornecedorRequestDTO givenFornecedorJuridico() {
		return new FornecedorRequestDTO(TipoPessoa.JURIDICA, CNPJ, CPF, NOME, CEP, EMAIL, RG, DATA_NASCIMENTO);
	}

	private FornecedorRequestDTO givenFornecedorFisica() {
		return new FornecedorRequestDTO(TipoPessoa.FISICA, CNPJ, CPF, NOME, CEP, EMAIL, RG, DATA_NASCIMENTO);
	}

	private Fornecedor givenFornecedor() {
		return new Fornecedor(ID, TipoPessoa.FISICA, null, CPF, NOME, CEP, EMAIL, RG, DATA_NASCIMENTO);
	}
}
