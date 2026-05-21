package br.com.desafio.accenture.application.mapper.empresa;

import br.com.desafio.accenture.application.mapper.fornecedor.FornecedorMapper;
import br.com.desafio.accenture.domain.model.Empresa;
import br.com.desafio.accenture.infra.persistence.entity.empresa.EmpresaEntity;

public class EmpresaMapper {

	private final FornecedorMapper fornecedorMapper;

	public EmpresaMapper(FornecedorMapper fornecedorMapper) {
		this.fornecedorMapper = fornecedorMapper;
	}

	public EmpresaEntity toEntity(Empresa empresa) {
		return new EmpresaEntity(null, empresa.getCnpj(), empresa.getNomeFantasia(), empresa.getCep(),
				empresa.getCidade(),
				empresa.getFornecedores() != null
						? empresa.getFornecedores().stream().map(fornecedorMapper::toEntity).toList()
						: null);
	}

	public Empresa toDomain(EmpresaEntity empresaEntity) {
		return new Empresa(empresaEntity.getId(), empresaEntity.getCnpj(), empresaEntity.getNomeFantasia(),
				empresaEntity.getCep(), empresaEntity.getCidade(),
				empresaEntity.getFornecedores() != null
						? empresaEntity.getFornecedores().stream().map(fornecedorMapper::toDomain).toList()
						: null);
	}

}
