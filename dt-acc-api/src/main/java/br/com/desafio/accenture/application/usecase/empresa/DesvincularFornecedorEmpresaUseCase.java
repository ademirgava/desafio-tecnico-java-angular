package br.com.desafio.accenture.application.usecase.empresa;

import br.com.desafio.accenture.application.dto.empresa.EmpresaResponseDTO;
import br.com.desafio.accenture.application.dto.fornecedor.FornecedorIdRequestDTO;
import br.com.desafio.accenture.application.usecase.fornecedor.BuscarPorIdFornecedorUseCase;
import br.com.desafio.accenture.domain.exception.ValidacaoException;
import br.com.desafio.accenture.domain.repository.empresa.RepositorioDeEmpresa;

public class DesvincularFornecedorEmpresaUseCase {

	private final RepositorioDeEmpresa repositorioDeEmpresa;
	private final BuscarPorIdFornecedorUseCase buscarPorIdFornecedorUseCase;

	public DesvincularFornecedorEmpresaUseCase(RepositorioDeEmpresa repositorioDeEmpresa, BuscarPorIdFornecedorUseCase buscarPorIdFornecedorUseCase) {
		this.repositorioDeEmpresa = repositorioDeEmpresa;
		this.buscarPorIdFornecedorUseCase = buscarPorIdFornecedorUseCase;
	}

	public EmpresaResponseDTO desvincular(FornecedorIdRequestDTO dto, Long idEmpresa) {
		if (dto == null || dto.idFornecedor() == null) 
			throw new ValidacaoException("Fornecedor id não deve ser nulo!");
		
		this.buscarPorIdFornecedorUseCase.buscarPorId(dto.idFornecedor());
		
		if (!this.repositorioDeEmpresa.existsByIdAndFornecedorId(idEmpresa, dto.idFornecedor()))
			throw new ValidacaoException("Fornecedor já desvinculado para está empresa!");
			
		return new EmpresaResponseDTO(this.repositorioDeEmpresa.removerFornecedor(dto.idFornecedor(), idEmpresa));
	}
}
