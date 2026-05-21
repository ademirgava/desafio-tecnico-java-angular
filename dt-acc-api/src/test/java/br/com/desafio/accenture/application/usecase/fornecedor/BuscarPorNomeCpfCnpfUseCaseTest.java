package br.com.desafio.accenture.application.usecase.fornecedor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.desafio.accenture.domain.repository.fornecedor.RepositorioDeFornecedor;

@ExtendWith(MockitoExtension.class)
public class BuscarPorNomeCpfCnpfUseCaseTest {

	@Mock
	private RepositorioDeFornecedor repositorioDeFornecedor;

	@InjectMocks
	private BuscarPorNomeCpfCnpfUseCase buscarPorNomeCpfCnpfUseCase;
	
	@Test
	public void deveBuscarPorNome() {
		this.buscarPorNomeCpfCnpfUseCase.buscar("nome", null, null);
		
		Mockito.verify(this.repositorioDeFornecedor).buscarPorNome("nome");
	}
	
	@Test
	public void deveBuscarPorCpf() {
		this.buscarPorNomeCpfCnpfUseCase.buscar(null, null, "221");
		
		Mockito.verify(this.repositorioDeFornecedor).buscarPorCpf("221");
	}
	
	@Test
	public void deveBuscarPorCnpj() {
		this.buscarPorNomeCpfCnpfUseCase.buscar(null, "221", null);
		
		Mockito.verify(this.repositorioDeFornecedor).buscarPorCnpj("221");
	}
}
