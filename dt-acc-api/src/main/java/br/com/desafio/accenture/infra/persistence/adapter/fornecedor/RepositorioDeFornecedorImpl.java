package br.com.desafio.accenture.infra.persistence.adapter.fornecedor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.desafio.accenture.application.mapper.fornecedor.FornecedorMapper;
import br.com.desafio.accenture.domain.model.Fornecedor;
import br.com.desafio.accenture.domain.repository.fornecedor.RepositorioDeFornecedor;
import br.com.desafio.accenture.infra.persistence.entity.fornecedor.FornecedorEntity;
import br.com.desafio.accenture.infra.persistence.repository.fornecedor.FornecedorRepository;

public class RepositorioDeFornecedorImpl implements RepositorioDeFornecedor {

	private final FornecedorRepository repository;
	private final FornecedorMapper mapper;

	public RepositorioDeFornecedorImpl(FornecedorRepository repository, FornecedorMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public boolean existsByCnpj(String cnpj) {
		return this.repository.existsByCnpj(cnpj);
	}

	@Override
	public boolean existsByCpf(String cpf) {
		return this.repository.existsByCpf(cpf);
	}

	@Override
	public Fornecedor salvar(Fornecedor fornecedor) {
		FornecedorEntity fornecedorEntity = this.mapper.toEntity(fornecedor);
		return this.mapper.toDomain(this.repository.save(fornecedorEntity));
	}

	@Override
	public Optional<Fornecedor> buscarPorId(Long id) {
		Optional<FornecedorEntity> opEntity = this.repository.findById(id);

		if (opEntity.isPresent())
			return Optional.of(this.mapper.toDomain(opEntity.get()));

		return Optional.empty();
	}

	@Override
	public List<Fornecedor> buscarPorNome(String nome) {
		return this.repository.findByNomeContaining(nome).stream().map(mapper::toDomain).collect(Collectors.toList());
	}

	@Override
	public List<Fornecedor> buscarPorCpf(String cpf) {
		return this.repository.findByCpfContaining(cpf).stream().map(mapper::toDomain).collect(Collectors.toList());
	}

	@Override
	public List<Fornecedor> buscarPorCnpj(String cnpj) {
		return this.repository.findByCnpjContaining(cnpj).stream().map(mapper::toDomain).collect(Collectors.toList());
	}

	@Override
	public List<Fornecedor> buscarTodos() {
		return this.repository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
	}

	@Override
	public boolean existsByCnpjAndIdNot(String cnpj, Long id) {
		return this.repository.existsByCnpjAndIdNot(cnpj, id);
	}

	@Override
	public boolean existsByCpfAndIdNot(String cpf, Long id) {
		return this.repository.existsByCpfAndIdNot(cpf, id);
	}

	@Override
	public Fornecedor atualizar(Fornecedor fornecedor) {
		FornecedorEntity fornecedorEntity = this.repository.getReferenceById(fornecedor.getId());
		fornecedorEntity.atualizar(fornecedor);
		return this.mapper.toDomain(fornecedorEntity);
	}

}
