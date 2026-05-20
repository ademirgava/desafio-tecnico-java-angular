package br.com.desafio.accenture.infra.persistence.entity.fornecedor;

import java.time.LocalDate;
import java.util.List;

import br.com.desafio.accenture.domain.model.TipoPessoa;
import br.com.desafio.accenture.infra.persistence.entity.empresa.EmpresaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fornecedores")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FornecedorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private TipoPessoa tipoPessoa;

	@Column(unique = true)
	private String cnpj;
	@Column(unique = true)
	private String cpf;

	private String nome;
	private String cep;
	private String email;
	private String rg;
	private LocalDate dataNascimento;

	@ManyToMany(mappedBy = "fornecedores")
	private List<EmpresaEntity> empresas;

	public FornecedorEntity(Long id) {
		this.id = id;
	}

}
