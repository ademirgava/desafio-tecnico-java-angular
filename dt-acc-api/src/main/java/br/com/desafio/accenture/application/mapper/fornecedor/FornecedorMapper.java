package br.com.desafio.accenture.application.mapper.fornecedor;

import br.com.desafio.accenture.domain.model.Fornecedor;
import br.com.desafio.accenture.infra.persistence.entity.fornecedor.FornecedorEntity;

public class FornecedorMapper {

	public FornecedorEntity toEntity(Fornecedor fornecedor) {
		return new FornecedorEntity(fornecedor.getId(), fornecedor.getTipoPessoa(),
				fornecedor.getCnpj() != null ? fornecedor.getCnpj().getValue() : null,
				fornecedor.getCpf() != null ? fornecedor.getCpf().getValue() : null, fornecedor.getNome(),
				fornecedor.getCep(), fornecedor.getEmail(), fornecedor.getRg(), fornecedor.getDataNascimento(), null);
	}

	public Fornecedor toDomain(FornecedorEntity fornecedor) {
		return new Fornecedor(fornecedor.getId(), fornecedor.getTipoPessoa(), fornecedor.getCnpj(), fornecedor.getCpf(),
				fornecedor.getNome(), fornecedor.getCep(), fornecedor.getEmail(), fornecedor.getRg(),
				fornecedor.getDataNascimento());
	}

}
