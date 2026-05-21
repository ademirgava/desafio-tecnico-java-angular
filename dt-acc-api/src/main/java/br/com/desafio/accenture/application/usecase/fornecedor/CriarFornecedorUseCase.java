package br.com.desafio.accenture.application.usecase.fornecedor;

import br.com.desafio.accenture.application.dto.fornecedor.FornecedorRequestDTO;
import br.com.desafio.accenture.application.dto.fornecedor.FornecedorResponseDTO;
import br.com.desafio.accenture.domain.exception.ValidacaoException;
import br.com.desafio.accenture.domain.model.Fornecedor;
import br.com.desafio.accenture.domain.model.TipoPessoa;
import br.com.desafio.accenture.domain.repository.fornecedor.RepositorioDeFornecedor;

public class CriarFornecedorUseCase {

	private final RepositorioDeFornecedor repositorioDeFornecedor;

	public CriarFornecedorUseCase(RepositorioDeFornecedor repositorioDeFornecedor) {
		this.repositorioDeFornecedor = repositorioDeFornecedor;
	}

	public FornecedorResponseDTO cadastrar(FornecedorRequestDTO dto) {
		Fornecedor fornecedor = new Fornecedor(dto.tipoPessoa(), dto.cnpj(), dto.cpf(), dto.nome(), dto.cep(),
				dto.email(), dto.rg(), dto.dataNascimento());
		
		if (TipoPessoa.JURIDICA.equals(dto.tipoPessoa()) && this.repositorioDeFornecedor.existsByCnpj(dto.cnpj()))
			throw new ValidacaoException("Fornecedor com o CNPJ: " + dto.cnpj() + " já cadastrado!");

		if (TipoPessoa.FISICA.equals(dto.tipoPessoa()) && this.repositorioDeFornecedor.existsByCpf(dto.cpf()))
			throw new ValidacaoException("Fornecedor com o CPF: " + dto.cpf() + " já cadastrado!");
			
		return new FornecedorResponseDTO(this.repositorioDeFornecedor.salvar(fornecedor));
	}
}
