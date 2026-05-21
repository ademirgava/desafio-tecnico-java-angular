package br.com.desafio.accenture.infra.persistence.config.fornecedor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.desafio.accenture.application.mapper.fornecedor.FornecedorMapper;
import br.com.desafio.accenture.application.usecase.fornecedor.AtualizarFornecedorUseCase;
import br.com.desafio.accenture.application.usecase.fornecedor.BuscarPorIdFornecedorUseCase;
import br.com.desafio.accenture.application.usecase.fornecedor.BuscarPorNomeCpfCnpfUseCase;
import br.com.desafio.accenture.application.usecase.fornecedor.CriarFornecedorUseCase;
import br.com.desafio.accenture.domain.repository.fornecedor.RepositorioDeFornecedor;
import br.com.desafio.accenture.infra.persistence.adapter.fornecedor.RepositorioDeFornecedorImpl;
import br.com.desafio.accenture.infra.persistence.repository.fornecedor.FornecedorRepository;

@Configuration
public class FornecedorConfig {

	@Bean
	public CriarFornecedorUseCase criarFornecedorUseCase(RepositorioDeFornecedor repositorio) {
		return new CriarFornecedorUseCase(repositorio);
	}
	
	@Bean
	public BuscarPorIdFornecedorUseCase buscarPorIdFornecedorUseCase(RepositorioDeFornecedor repositorio) {
		return new BuscarPorIdFornecedorUseCase(repositorio);
	}
	
	@Bean
	public BuscarPorNomeCpfCnpfUseCase buscarPorNomeCpfCnpfUseCase(RepositorioDeFornecedor repositorio) {
		return new BuscarPorNomeCpfCnpfUseCase(repositorio);
	}
	
	@Bean
	public AtualizarFornecedorUseCase atualizarFornecedorUseCase(RepositorioDeFornecedor repositorio) {
		return new AtualizarFornecedorUseCase(repositorio);
	}
	
	@Bean
	public RepositorioDeFornecedor repositorioDeFornecedor(FornecedorRepository repository, FornecedorMapper mapper) {
		return new RepositorioDeFornecedorImpl(repository, mapper);
	}
	
	@Bean 
	public FornecedorMapper fornecedorMapper() {
		return new FornecedorMapper();
	}
}
