package br.com.desafio.accenture.domain.repository.empresa;

import java.util.Optional;

import br.com.desafio.accenture.domain.model.Empresa;

public interface RepositorioDeEmpresa {

	Empresa salvar(Empresa empresa);

	boolean existsByCnpj(String cnpj);

	Optional<Empresa> buscarPorId(Long id);

	void deletar(Long empresaId);

	Empresa atualizar(Empresa empresaDomain);

	boolean existsByCnpjAndNotId(String cnpj, Long empresaId);

}
