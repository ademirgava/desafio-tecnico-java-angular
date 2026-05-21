package br.com.desafio.accenture.application.usecase.fornecedor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.desafio.accenture.application.dto.ListaResponseDTO;
import br.com.desafio.accenture.application.dto.fornecedor.FornecedorResponseDTO;
import br.com.desafio.accenture.domain.model.Fornecedor;
import br.com.desafio.accenture.domain.repository.fornecedor.RepositorioDeFornecedor;

public class BuscarPorNomeCpfCnpfUseCase {

	private final RepositorioDeFornecedor repositorioDeFornecedor;

	public BuscarPorNomeCpfCnpfUseCase(RepositorioDeFornecedor repositorioDeFornecedor) {
		this.repositorioDeFornecedor = repositorioDeFornecedor;
	}

	public ListaResponseDTO<FornecedorResponseDTO> buscar(String nome, String cnpj, String cpf) {
		List<Fornecedor> listaFornecedores = new ArrayList<Fornecedor>();

		if (nome != null) {
			listaFornecedores = this.repositorioDeFornecedor.buscarPorNome(nome);
		} else if (cpf != null) {
			listaFornecedores = this.repositorioDeFornecedor.buscarPorCpf(cpf);
		} else if (cnpj != null) {
			listaFornecedores = this.repositorioDeFornecedor.buscarPorCnpj(cnpj);
		} else {
			listaFornecedores = this.repositorioDeFornecedor.buscarTodos();
		}

		return new ListaResponseDTO<FornecedorResponseDTO>(
				listaFornecedores.stream().map(FornecedorResponseDTO::new).collect(Collectors.toList()));
	}
}
