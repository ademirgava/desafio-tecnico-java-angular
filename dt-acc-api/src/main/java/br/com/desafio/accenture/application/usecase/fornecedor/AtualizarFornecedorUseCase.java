package br.com.desafio.accenture.application.usecase.fornecedor;

import br.com.desafio.accenture.application.dto.fornecedor.FornecedorRequestDTO;
import br.com.desafio.accenture.application.dto.fornecedor.FornecedorResponseDTO;
import br.com.desafio.accenture.domain.exception.ValidacaoException;
import br.com.desafio.accenture.domain.model.Fornecedor;
import br.com.desafio.accenture.domain.model.TipoPessoa;
import br.com.desafio.accenture.domain.repository.fornecedor.RepositorioDeFornecedor;

public class AtualizarFornecedorUseCase {
	private final RepositorioDeFornecedor repositorioDeFornecedor;

	public AtualizarFornecedorUseCase(RepositorioDeFornecedor repositorioDeFornecedor) {
		this.repositorioDeFornecedor = repositorioDeFornecedor;
	}

	public FornecedorResponseDTO atualizar(FornecedorRequestDTO dto, Long id) {

		if (TipoPessoa.JURIDICA.equals(dto.tipoPessoa())) {
			if (this.repositorioDeFornecedor.existsByCnpjAndIdNot(dto.cnpj(), id))
				throw new ValidacaoException("Já existe uma empresa com o CNPJ: " + dto.cnpj());
		} else {
			if (this.repositorioDeFornecedor.existsByCpfAndIdNot(dto.cpf(), id))
				throw new ValidacaoException("Já existe uma empresa com o CPF: " + dto.cpf());
		}
		
		Fornecedor fornecedor = new Fornecedor(id, dto.tipoPessoa(), dto.cnpj(), dto.cpf(), dto.nome(), dto.cep(),
				dto.email(), dto.rg(), dto.dataNascimento());

		return new FornecedorResponseDTO(this.repositorioDeFornecedor.atualizar(fornecedor));
	}
}
