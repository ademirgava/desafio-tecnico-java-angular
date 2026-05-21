package br.com.desafio.accenture.interfaces.controller.fornecedor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.desafio.accenture.application.dto.ListaResponseDTO;
import br.com.desafio.accenture.application.dto.fornecedor.FornecedorRequestDTO;
import br.com.desafio.accenture.application.dto.fornecedor.FornecedorResponseDTO;
import br.com.desafio.accenture.application.usecase.fornecedor.AtualizarFornecedorUseCase;
import br.com.desafio.accenture.application.usecase.fornecedor.BuscarPorIdFornecedorUseCase;
import br.com.desafio.accenture.application.usecase.fornecedor.BuscarPorNomeCpfCnpfUseCase;
import br.com.desafio.accenture.application.usecase.fornecedor.CriarFornecedorUseCase;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

	private final CriarFornecedorUseCase criarFornecedorUseCase;
	private final BuscarPorIdFornecedorUseCase buscarPorIdFornecedorUseCase;
	private final BuscarPorNomeCpfCnpfUseCase buscarPorNomeCpfCnpfUseCase;
	private final AtualizarFornecedorUseCase atualizarFornecedorUseCase;

	public FornecedorController(CriarFornecedorUseCase criarFornecedorUseCase,
			BuscarPorIdFornecedorUseCase buscarPorIdFornecedorUseCase,
			BuscarPorNomeCpfCnpfUseCase buscarPorNomeCpfCnpfUseCase,
			AtualizarFornecedorUseCase atualizarEmpresaUseCase) {
		this.criarFornecedorUseCase = criarFornecedorUseCase;
		this.buscarPorIdFornecedorUseCase = buscarPorIdFornecedorUseCase;
		this.buscarPorNomeCpfCnpfUseCase = buscarPorNomeCpfCnpfUseCase;
		this.atualizarFornecedorUseCase = atualizarEmpresaUseCase;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<FornecedorResponseDTO> cadastrar(@RequestBody FornecedorRequestDTO dto,
			UriComponentsBuilder builder) {
		FornecedorResponseDTO fornecedorResponseDTO = this.criarFornecedorUseCase.cadastrar(dto);
		var uri = builder.path("/fornecedores/{id}").build().toUri();
		return ResponseEntity.created(uri).body(fornecedorResponseDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<FornecedorResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(this.buscarPorIdFornecedorUseCase.buscarPorId(id));
	}

	@GetMapping
	public ResponseEntity<ListaResponseDTO<FornecedorResponseDTO>> buscarPorFiltros(
			@RequestParam(required = false) String nome, @RequestParam(required = false) String cnpj,
			@RequestParam(required = false) String cpf) {
		return ResponseEntity.ok(this.buscarPorNomeCpfCnpfUseCase.buscar(nome, cnpj, cpf));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<FornecedorResponseDTO> atualizar(@PathVariable Long id, @RequestBody FornecedorRequestDTO dto) {
		return ResponseEntity.ok(this.atualizarFornecedorUseCase.atualizar(dto, id));
	}
}
