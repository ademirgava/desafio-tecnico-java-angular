package br.com.desafio.accenture.infra.persistence.config.empresa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.desafio.accenture.application.mapper.empresa.EmpresaMapper;
import br.com.desafio.accenture.application.usecase.empresa.AtualizarEmpresaUseCase;
import br.com.desafio.accenture.application.usecase.empresa.BuscarPorIdEmpresaUseCase;
import br.com.desafio.accenture.application.usecase.empresa.CriarEmpresaUseCase;
import br.com.desafio.accenture.application.usecase.empresa.DeletarEmpresaUseCase;
import br.com.desafio.accenture.domain.repository.empresa.RepositorioDeEmpresa;
import br.com.desafio.accenture.infra.persistence.adapter.empresa.RepositorioDeEmpresaImpl;
import br.com.desafio.accenture.infra.persistence.repository.empresa.EmpresaRepository;

@Configuration
public class EmpresaConfig {

	@Bean
	public CriarEmpresaUseCase criarEmpresaUseCase(RepositorioDeEmpresa repositorio) {
		return new CriarEmpresaUseCase(repositorio);
	}
	
	@Bean
	public BuscarPorIdEmpresaUseCase buscarPorIdEmpresaUseCase(RepositorioDeEmpresa repositorio) {
		return new BuscarPorIdEmpresaUseCase(repositorio);
	}
	
	@Bean
	public AtualizarEmpresaUseCase atualizarEmpresaUseCase(RepositorioDeEmpresa repositorio) {
		return new AtualizarEmpresaUseCase(repositorio);
	}
	
	@Bean 
	public DeletarEmpresaUseCase deletarEmpresaUseCase(RepositorioDeEmpresa repositorio) {
		return new DeletarEmpresaUseCase(repositorio);
	}
	
	@Bean
	public RepositorioDeEmpresa repositorioDeEmpresa(EmpresaRepository repository, EmpresaMapper mapper) {
		return new RepositorioDeEmpresaImpl(repository, mapper);
	}
	
	@Bean
	public EmpresaMapper empresaMapper() {
		return new EmpresaMapper();
	}
}
