package br.com.desafio.accenture.domain.repository.fornecedor;

import java.util.List;
import java.util.Optional;

import br.com.desafio.accenture.domain.model.Fornecedor;

public interface RepositorioDeFornecedor {

	boolean existsByCnpj(String cnpj);

	boolean existsByCpf(String cpf);

	Fornecedor salvar(Fornecedor fornecedor);

	Optional<Fornecedor> buscarPorId(Long id);

	List<Fornecedor> buscarPorNome(String nome);

	List<Fornecedor> buscarPorCpf(String cpf);

	List<Fornecedor> buscarPorCnpj(String cnpj);

	List<Fornecedor> buscarTodos();

	boolean existsByCnpjAndIdNot(String cnpj, Long id);

	boolean existsByCpfAndIdNot(String cpf, Long id);

	Fornecedor atualizar(Fornecedor fornecedor);

}
