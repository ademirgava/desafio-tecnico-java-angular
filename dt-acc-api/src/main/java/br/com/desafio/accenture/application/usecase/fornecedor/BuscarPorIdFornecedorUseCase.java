package br.com.desafio.accenture.application.usecase.fornecedor;

import java.util.Optional;

import br.com.desafio.accenture.application.dto.fornecedor.FornecedorResponseDTO;
import br.com.desafio.accenture.domain.exception.ValidacaoException;
import br.com.desafio.accenture.domain.model.Fornecedor;
import br.com.desafio.accenture.domain.repository.fornecedor.RepositorioDeFornecedor;

public class BuscarPorIdFornecedorUseCase {

	private final RepositorioDeFornecedor repositorioDeFornecedor;

	public BuscarPorIdFornecedorUseCase(RepositorioDeFornecedor repositorioDeFornecedor) {
		this.repositorioDeFornecedor = repositorioDeFornecedor;
	}

	public FornecedorResponseDTO buscarPorId(Long id) {
		Optional<Fornecedor> opFornecedor = this.repositorioDeFornecedor.buscarPorId(id);

		if (opFornecedor.isEmpty())
			throw new ValidacaoException("Fornecedor com id: " + id + " não encontrado!");

		return new FornecedorResponseDTO(opFornecedor.get());
	}
}
