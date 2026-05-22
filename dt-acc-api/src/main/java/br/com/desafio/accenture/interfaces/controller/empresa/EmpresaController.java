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

import br.com.desafio.accenture.application.dto.ListaResponseDTO;
import br.com.desafio.accenture.application.dto.empresa.EmpresaRequestDTO;
import br.com.desafio.accenture.application.dto.empresa.EmpresaResponseDTO;
import br.com.desafio.accenture.application.dto.fornecedor.FornecedorIdRequestDTO;
import br.com.desafio.accenture.application.usecase.empresa.AtualizarEmpresaUseCase;
import br.com.desafio.accenture.application.usecase.empresa.BuscarPorIdEmpresaUseCase;
import br.com.desafio.accenture.application.usecase.empresa.CriarEmpresaUseCase;
import br.com.desafio.accenture.application.usecase.empresa.DeletarEmpresaUseCase;
import br.com.desafio.accenture.application.usecase.empresa.DesvincularFornecedorEmpresaUseCase;
import br.com.desafio.accenture.application.usecase.empresa.ListarEmpresaUseCase;
import br.com.desafio.accenture.application.usecase.empresa.VincularFornecedorEmpresaUseCase;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

	private final CriarEmpresaUseCase criarEmpresaUseCase;
	private final BuscarPorIdEmpresaUseCase buscarPorIdEmpresaUseCase;
	private final DeletarEmpresaUseCase deletarEmpresaUseCase;
	private final AtualizarEmpresaUseCase atualizarEmpresaUseCase;
	private final VincularFornecedorEmpresaUseCase vincularFornecedorEmpresaUseCase;
	private final DesvincularFornecedorEmpresaUseCase desvincularFornecedorEmpresaUseCase;
	private final ListarEmpresaUseCase listarEmpresaUseCase;

	public EmpresaController(CriarEmpresaUseCase criarEmpresaUseCase,
			BuscarPorIdEmpresaUseCase buscarPorIdEmpresaUseCase, DeletarEmpresaUseCase deletarEmpresaUseCase,
			AtualizarEmpresaUseCase atualizarEmpresaUseCase,
			VincularFornecedorEmpresaUseCase vincularFornecedorEmpresaUseCase, DesvincularFornecedorEmpresaUseCase desvincularFornecedorEmpresaUseCase, ListarEmpresaUseCase listarEmpresaUseCase) {
		this.criarEmpresaUseCase = criarEmpresaUseCase;
		this.buscarPorIdEmpresaUseCase = buscarPorIdEmpresaUseCase;
		this.deletarEmpresaUseCase = deletarEmpresaUseCase;
		this.atualizarEmpresaUseCase = atualizarEmpresaUseCase;
		this.vincularFornecedorEmpresaUseCase = vincularFornecedorEmpresaUseCase;
		this.desvincularFornecedorEmpresaUseCase = desvincularFornecedorEmpresaUseCase;
		this.listarEmpresaUseCase = listarEmpresaUseCase;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<EmpresaResponseDTO> cadastrar(@RequestBody EmpresaRequestDTO dto,
			UriComponentsBuilder builder) {
		EmpresaResponseDTO empresaResponse = this.criarEmpresaUseCase.cadastrar(dto);
		var uri = builder.path("/empresas/{id}").buildAndExpand(empresaResponse.id()).toUri();
		return ResponseEntity.created(uri).body(empresaResponse);
	}

	@GetMapping()
	public ResponseEntity<ListaResponseDTO<EmpresaResponseDTO>> listarTodas() {
		return ResponseEntity.ok(listarEmpresaUseCase.listar());
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

	@PutMapping("/vincular/{id}")
	@Transactional
	public ResponseEntity<EmpresaResponseDTO> vincular(@PathVariable Long id, @RequestBody FornecedorIdRequestDTO dto) {
		return ResponseEntity.ok(this.vincularFornecedorEmpresaUseCase.vincular(dto, id));
	}

	@PutMapping("/desvincular/{id}")
	@Transactional
	public ResponseEntity<EmpresaResponseDTO> desvincular(@PathVariable Long id, @RequestBody FornecedorIdRequestDTO dto) {
		return ResponseEntity.ok(this.desvincularFornecedorEmpresaUseCase.desvincular(dto, id));
	}
}
