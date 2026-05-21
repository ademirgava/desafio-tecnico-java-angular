package br.com.desafio.accenture.infra.persistence.repository.fornecedor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.accenture.infra.persistence.entity.fornecedor.FornecedorEntity;

public interface FornecedorRepository extends JpaRepository<FornecedorEntity, Long>{

	boolean existsByCnpj(String cnpj);

	boolean existsByCpf(String cpf);

	List<FornecedorEntity> findByNomeContaining(String nome);

	List<FornecedorEntity> findByCpfContaining(String cpf);

	List<FornecedorEntity> findByCnpjContaining(String cnpj);

	boolean existsByCnpjAndIdNot(String cnpj, Long id);

	boolean existsByCpfAndIdNot(String cpf, Long id);

}
