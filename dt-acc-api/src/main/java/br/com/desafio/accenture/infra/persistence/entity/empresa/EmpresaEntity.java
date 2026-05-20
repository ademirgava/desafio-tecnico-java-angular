package br.com.desafio.accenture.infra.persistence.entity.empresa;

import java.util.List;

import br.com.desafio.accenture.domain.model.Empresa;
import br.com.desafio.accenture.infra.persistence.entity.fornecedor.FornecedorEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "empresas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String cnpj;
	private String nomeFantasia;
	private String cep;
	private String cidade;

	@ManyToMany
	@JoinTable(name = "empresa_fornecedor", joinColumns = @JoinColumn(name = "empresa_id"), inverseJoinColumns = @JoinColumn(name = "fornecedor_id"))
	private List<FornecedorEntity> fornecedores;

	public void atualizar(Empresa empresaDomain) {
		this.cnpj = empresaDomain.getCnpj();
		this.nomeFantasia = empresaDomain.getNomeFantasia();
		this.cep = empresaDomain.getCep();
		this.cidade = empresaDomain.getCidade();
	}
}
