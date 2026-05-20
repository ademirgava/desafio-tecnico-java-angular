package br.com.desafio.accenture.interfaces.controller.empresa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.desafio.accenture.application.dto.empresa.EmpresaRequestDTO;
import br.com.desafio.accenture.application.dto.empresa.EmpresaResponseDTO;
import br.com.desafio.accenture.application.usecase.empresa.AtualizarEmpresaUseCase;
import br.com.desafio.accenture.application.usecase.empresa.BuscarPorIdEmpresaUseCase;
import br.com.desafio.accenture.application.usecase.empresa.CriarEmpresaUseCase;
import br.com.desafio.accenture.application.usecase.empresa.DeletarEmpresaUseCase;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

	private final CriarEmpresaUseCase criarEmpresaUseCase;
	private final BuscarPorIdEmpresaUseCase buscarPorIdEmpresaUseCase;
	private final DeletarEmpresaUseCase deletarEmpresaUseCase;
	private final AtualizarEmpresaUseCase atualizarEmpresaUseCase;

	public EmpresaController(CriarEmpresaUseCase criarEmpresaUseCase,
			BuscarPorIdEmpresaUseCase buscarPorIdEmpresaUseCase, DeletarEmpresaUseCase deletarEmpresaUseCase, AtualizarEmpresaUseCase atualizarEmpresaUseCase) {
		this.criarEmpresaUseCase = criarEmpresaUseCase;
		this.buscarPorIdEmpresaUseCase = buscarPorIdEmpresaUseCase;
		this.deletarEmpresaUseCase = deletarEmpresaUseCase;
		this.atualizarEmpresaUseCase = atualizarEmpresaUseCase;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<EmpresaResponseDTO> cadastrar(@RequestBody EmpresaRequestDTO dto,
			UriComponentsBuilder builder) {
		EmpresaResponseDTO empresaResponse = this.criarEmpresaUseCase.cadastrar(dto);
		var uri = builder.path("/empresas/{id}").buildAndExpand(empresaResponse.id()).toUri();
		return ResponseEntity.created(uri).body(empresaResponse);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmpresaResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(this.buscarPorIdEmpresaUseCase.buscarPorId(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarEmpresa(@PathVariable Long id) {
		this.deletarEmpresaUseCase.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EmpresaResponseDTO> atualizar(@PathVariable Long id, @RequestBody EmpresaRequestDTO dto) {
		return ResponseEntity.ok(this.atualizarEmpresaUseCase.atualizar(dto, id));
	}
}
