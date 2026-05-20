package br.com.desafio.accenture.application.mapper.empresa;

import br.com.desafio.accenture.domain.model.Empresa;
import br.com.desafio.accenture.domain.model.Fornecedor;
import br.com.desafio.accenture.infra.persistence.entity.empresa.EmpresaEntity;
import br.com.desafio.accenture.infra.persistence.entity.fornecedor.FornecedorEntity;

public class EmpresaMapper {

	public EmpresaMapper() {
	}

	public EmpresaEntity toEntity(Empresa empresa) {
		return new EmpresaEntity(null, empresa.getCnpj(), empresa.getNomeFantasia(), empresa.getCep(),
				empresa.getCidade(),
				empresa.getFornecedores() != null
						? empresa.getFornecedores().stream()
								.map((fornecedor) -> new FornecedorEntity(fornecedor.getId())).toList()
						: null);
	}

	public Empresa toDomain(EmpresaEntity empresaEntity) {
		return new Empresa(empresaEntity.getId(), empresaEntity.getCnpj(), empresaEntity.getNomeFantasia(), empresaEntity.getCep(), empresaEntity.getCidade(), empresaEntity.getFornecedores() != null
				? empresaEntity.getFornecedores().stream()
						.map((fornecedor) -> new Fornecedor(fornecedor.getId())).toList()
				: null);
	}

}
