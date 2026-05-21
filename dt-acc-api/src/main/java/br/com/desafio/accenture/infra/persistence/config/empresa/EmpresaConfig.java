package br.com.desafio.accenture.infra.persistence.config.empresa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.desafio.accenture.application.mapper.empresa.EmpresaMapper;
import br.com.desafio.accenture.application.mapper.fornecedor.FornecedorMapper;
import br.com.desafio.accenture.application.usecase.empresa.AtualizarEmpresaUseCase;
import br.com.desafio.accenture.application.usecase.empresa.BuscarPorIdEmpresaUseCase;
import br.com.desafio.accenture.application.usecase.empresa.CriarEmpresaUseCase;
import br.com.desafio.accenture.application.usecase.empresa.DeletarEmpresaUseCase;
import br.com.desafio.accenture.application.usecase.empresa.DesvincularFornecedorEmpresaUseCase;
import br.com.desafio.accenture.application.usecase.empresa.VincularFornecedorEmpresaUseCase;
import br.com.desafio.accenture.application.usecase.fornecedor.BuscarPorIdFornecedorUseCase;
import br.com.desafio.accenture.domain.repository.empresa.RepositorioDeEmpresa;
import br.com.desafio.accenture.infra.persistence.adapter.empresa.RepositorioDeEmpresaImpl;
import br.com.desafio.accenture.infra.persistence.repository.empresa.EmpresaRepository;
import br.com.desafio.accenture.infra.persistence.repository.fornecedor.FornecedorRepository;

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
	public VincularFornecedorEmpresaUseCase fornecedorEmpresaUseCase(RepositorioDeEmpresa repositorio, BuscarPorIdFornecedorUseCase buscarPorIdFornecedorUseCase, BuscarPorIdEmpresaUseCase buscarPorIdEmpresaUseCase) {
		return new VincularFornecedorEmpresaUseCase(repositorio, buscarPorIdFornecedorUseCase, buscarPorIdEmpresaUseCase);
	}
	
	@Bean
	public DesvincularFornecedorEmpresaUseCase desvincularFornecedorEmpresaUseCase(RepositorioDeEmpresa repositorio, BuscarPorIdFornecedorUseCase buscarPorIdFornecedorUseCase) {
		return new DesvincularFornecedorEmpresaUseCase(repositorio, buscarPorIdFornecedorUseCase);
	}
	
	@Bean
	public RepositorioDeEmpresa repositorioDeEmpresa(EmpresaRepository repository, EmpresaMapper mapper, FornecedorRepository fornecedorRepository) {
		return new RepositorioDeEmpresaImpl(repository, mapper, fornecedorRepository);
	}
	
	@Bean
	public EmpresaMapper empresaMapper(FornecedorMapper fornecedorMapper) {
		return new EmpresaMapper(fornecedorMapper);
	}
}
