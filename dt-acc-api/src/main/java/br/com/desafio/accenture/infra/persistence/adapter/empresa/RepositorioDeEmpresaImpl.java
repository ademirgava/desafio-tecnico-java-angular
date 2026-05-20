package br.com.desafio.accenture.infra.persistence.adapter.empresa;

import java.util.Optional;

import br.com.desafio.accenture.application.mapper.empresa.EmpresaMapper;
import br.com.desafio.accenture.domain.model.Empresa;
import br.com.desafio.accenture.domain.repository.empresa.RepositorioDeEmpresa;
import br.com.desafio.accenture.infra.persistence.entity.empresa.EmpresaEntity;
import br.com.desafio.accenture.infra.persistence.repository.empresa.EmpresaRepository;

public class RepositorioDeEmpresaImpl implements RepositorioDeEmpresa {

	private final EmpresaRepository repository;
	private final EmpresaMapper mapper;

	public RepositorioDeEmpresaImpl(EmpresaRepository repository, EmpresaMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public Empresa salvar(Empresa empresa) {
		EmpresaEntity empresaEntity = this.repository.save(this.mapper.toEntity(empresa));
		return this.mapper.toDomain(empresaEntity);
	}

	@Override
	public boolean existsByCnpj(String cnpj) {
		return this.repository.existsByCnpj(cnpj);
	}

	@Override
	public Optional<Empresa> buscarPorId(Long id) {
		Optional<EmpresaEntity> opEmpresaa = this.repository.findById(id);
		if (opEmpresaa.isEmpty())
			return Optional.empty();

		return Optional.of(mapper.toDomain(opEmpresaa.get()));
	}

	@Override
	public void deletar(Long empresaId) {
		this.repository.deleteById(empresaId);
	}

	@Override
	public Empresa atualizar(Empresa empresaDomain) {
		EmpresaEntity empresaEntity = this.repository.getReferenceById(empresaDomain.getId());
		empresaEntity.atualizar(empresaDomain);
		return this.mapper.toDomain(empresaEntity);
	}

	@Override
	public boolean existsByCnpjAndNotId(String cnpj, Long empresaId) {
		return this.repository.existsByCnpjAndIdNot(cnpj, empresaId);
	}

}
