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
public class CriarFornecedorUseCaseTest {

	private static final String CNPJ = "12345678900987";
	private static final String NOME = "Nome";
	private static final String CEP = "12345-000";
	private static final String CPF = "22543256721";
	private static final String RG = "340974329";
	private static final String EMAIL = "teste@test.com";
	private static final LocalDate DATA_NASCIMENTO = LocalDate.of(2000, 12, 1);

	@Mock
	private RepositorioDeFornecedor repositorioDeFornecedor;

	@InjectMocks
	private CriarFornecedorUseCase criarFornecedorUseCase;

	@Test
	public void naoDeveCadastrarComCnpjJaCadastrado() {
		Mockito.when(this.repositorioDeFornecedor.existsByCnpj(CNPJ)).thenReturn(true);

		Assertions.assertThrows(ValidacaoException.class,
				() -> this.criarFornecedorUseCase.cadastrar(givenRequestJuridica(CNPJ)));
	}

	@Test
	public void naoDeveCadastrarPessoaJuridicaComCnpjNull() {
		Assertions.assertThrows(ValidacaoException.class,
				() -> this.criarFornecedorUseCase.cadastrar(givenRequestJuridica(null)));
	}

	@Test
	public void naoDeveCadastrarPessoaJuridicaComCnpjInvalido() {
		Assertions.assertThrows(ValidacaoException.class,
				() -> this.criarFornecedorUseCase.cadastrar(givenRequestJuridica("1234567897")));
	}

	@Test
	public void naoDeveCadastrarPessoaFisicaComCpfJaCadastrado() {
		Mockito.when(this.repositorioDeFornecedor.existsByCpf(CPF)).thenReturn(true);

		Assertions.assertThrows(ValidacaoException.class,
				() -> this.criarFornecedorUseCase.cadastrar(givenRequestFisica(CPF)));
	}

	@Test
	public void naoDeveCadastrarPessoaFisicaComCpfNull() {
		Assertions.assertThrows(ValidacaoException.class,
				() -> this.criarFornecedorUseCase.cadastrar(givenRequestFisica(null)));
	}

	@Test
	public void naoDeveCadastrarPessoaFisicaComCpfInvalido() {
		Assertions.assertThrows(ValidacaoException.class,
				() -> this.criarFornecedorUseCase.cadastrar(givenRequestFisica("22222543256721")));
	}

	@Test
	public void naoDeveCadastrarPessoaFisicaComRgNull() {
		Assertions.assertThrows(ValidacaoException.class,
				() -> this.criarFornecedorUseCase.cadastrar(givenRequestFisicaRg(null)));
	}

	@Test
	public void naoDeveCadastrarPessoaFisicaComDataNascimentoNull() {
		Assertions.assertThrows(ValidacaoException.class,
				() -> this.criarFornecedorUseCase.cadastrar(givenRequestFisicaDataNascimento()));
	}

	@Test
	public void naoDeveCadastrarComEmailInvalido() {
		Assertions.assertThrows(ValidacaoException.class,
				() -> this.criarFornecedorUseCase.cadastrar(givenRequestFisicaEmailInvalido()));
	}

	@Test
	public void deveCadastrarPessoaFisica() {
		Mockito.when(this.repositorioDeFornecedor.salvar(any())).thenReturn(givenFornecedor());
		Mockito.when(this.repositorioDeFornecedor.existsByCpf(CPF)).thenReturn(false);

		FornecedorResponseDTO fornecedorResponseDTO = this.criarFornecedorUseCase.cadastrar(givenRequestFisica(CPF));
		Assertions.assertEquals(CPF, fornecedorResponseDTO.cpf());
		Assertions.assertEquals(NOME, fornecedorResponseDTO.nome());
		Assertions.assertEquals(CEP, fornecedorResponseDTO.cep());
		Assertions.assertEquals(EMAIL, fornecedorResponseDTO.email());
		Assertions.assertEquals(RG, fornecedorResponseDTO.rg());
		Assertions.assertEquals(DATA_NASCIMENTO, fornecedorResponseDTO.dataNascimento());
		Assertions.assertEquals(TipoPessoa.FISICA, fornecedorResponseDTO.tipoPessoa());

	}

	private Fornecedor givenFornecedor() {
		return new Fornecedor(TipoPessoa.FISICA, CNPJ, CPF, NOME, CEP, EMAIL, RG, DATA_NASCIMENTO);
	}

	private FornecedorRequestDTO givenRequestFisica(String cpf) {
		return new FornecedorRequestDTO(TipoPessoa.FISICA, CNPJ, cpf, NOME, CEP, EMAIL, RG, DATA_NASCIMENTO);
	}

	private FornecedorRequestDTO givenRequestFisicaRg(String rg) {
		return new FornecedorRequestDTO(TipoPessoa.FISICA, CNPJ, CPF, NOME, CEP, EMAIL, rg, DATA_NASCIMENTO);
	}

	private FornecedorRequestDTO givenRequestFisicaDataNascimento() {
		return new FornecedorRequestDTO(TipoPessoa.FISICA, CNPJ, CPF, NOME, CEP, EMAIL, RG, null);
	}

	private FornecedorRequestDTO givenRequestFisicaEmailInvalido() {
		return new FornecedorRequestDTO(TipoPessoa.FISICA, null, CPF, NOME, CEP, "sdasdsa", RG, DATA_NASCIMENTO);
	}

	private FornecedorRequestDTO givenRequestJuridica(String cnpj) {
		return new FornecedorRequestDTO(TipoPessoa.JURIDICA, cnpj, CPF, NOME, CEP, EMAIL, null, null);
	}

}
