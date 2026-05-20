package br.com.desafio.accenture.infra.persistence.repository.empresa;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.accenture.infra.persistence.entity.empresa.EmpresaEntity;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long>{

	boolean existsByCnpj(String cnpj);

	boolean existsByCnpjAndIdNot(String cnpj, Long empresaId);

}
