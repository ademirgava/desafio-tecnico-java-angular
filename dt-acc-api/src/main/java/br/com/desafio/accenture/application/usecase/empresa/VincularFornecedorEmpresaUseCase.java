package br.com.desafio.accenture.application.usecase.empresa;

import java.time.LocalDate;
import java.time.Period;

import br.com.desafio.accenture.application.dto.empresa.EmpresaResponseDTO;
import br.com.desafio.accenture.application.dto.fornecedor.FornecedorIdRequestDTO;
import br.com.desafio.accenture.application.dto.fornecedor.FornecedorResponseDTO;
import br.com.desafio.accenture.application.usecase.fornecedor.BuscarPorIdFornecedorUseCase;
import br.com.desafio.accenture.domain.exception.ValidacaoException;
import br.com.desafio.accenture.domain.model.TipoPessoa;
import br.com.desafio.accenture.domain.repository.empresa.RepositorioDeEmpresa;

public class VincularFornecedorEmpresaUseCase {

	private final RepositorioDeEmpresa repositorioDeEmpresa;
	private final BuscarPorIdFornecedorUseCase buscarPorIdFornecedorUseCase;
	private final BuscarPorIdEmpresaUseCase buscarPorIdEmpresaUseCase;

	public VincularFornecedorEmpresaUseCase(RepositorioDeEmpresa repositorioDeEmpresa, BuscarPorIdFornecedorUseCase buscarPorIdFornecedorUseCase, BuscarPorIdEmpresaUseCase buscarPorIdEmpresaUseCase) {
		this.repositorioDeEmpresa = repositorioDeEmpresa;
		this.buscarPorIdFornecedorUseCase = buscarPorIdFornecedorUseCase;
		this.buscarPorIdEmpresaUseCase = buscarPorIdEmpresaUseCase;
	}

	public EmpresaResponseDTO vincular(FornecedorIdRequestDTO dto, Long idEmpresa) {
		if (dto == null || dto.idFornecedor() == null) 
			throw new ValidacaoException("Fornecedor id não deve ser nulo!");
		
		FornecedorResponseDTO fornecedorResponseDTO = this.buscarPorIdFornecedorUseCase.buscarPorId(dto.idFornecedor());
		EmpresaResponseDTO empresaDto = this.buscarPorIdEmpresaUseCase.buscarPorId(idEmpresa);
		
		if (empresaDto.cidade().toUpperCase().equals("PARANA")) {
			if (TipoPessoa.FISICA.equals(fornecedorResponseDTO.tipoPessoa())) {
				Period periodo = Period.between(fornecedorResponseDTO.dataNascimento(), LocalDate.now());
				if (periodo.getYears() < 18) 
					throw new ValidacaoException("Empresa do Paraná não permitem fornecedores pessoa fisíca menores de idade!");
			}
		}
		
		if (this.repositorioDeEmpresa.existsByIdAndFornecedorId(idEmpresa, dto.idFornecedor()))
			throw new ValidacaoException("Fornecedor já vinculado para está empresa!");
			
		return new EmpresaResponseDTO(this.repositorioDeEmpresa.adicionarFornecedor(dto.idFornecedor(), idEmpresa));
	}
}
