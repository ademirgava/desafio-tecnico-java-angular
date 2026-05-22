import { Component, inject, OnInit, signal, Signal } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { TipoPessoa } from '../../../components/model/tipoPessoa';
import { CommonModule } from '@angular/common';
import { FornecedorService } from '../../../service/fornecedor/fornecedor.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ViacepService } from '../../../service/viacep/viacep.service';

@Component({
  selector: 'app-formulario-fornecedor',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './formulario-fornecedor.component.html',
  styleUrl: './formulario-fornecedor.component.css',
})
export class FormularioFornecedorComponent implements OnInit {
  private fornecedorService = inject(FornecedorService);
  private activatedRoute = inject(ActivatedRoute);
  private router = inject(Router);
  private viacepService = inject(ViacepService);

  fornecedorForm!: FormGroup;
  tipos = Object.values(TipoPessoa);
  mensagemDeErro = signal<string>('');
  isErro = signal<boolean>(false);
  selectedTipo = signal<TipoPessoa>(TipoPessoa.FISICA);

  ngOnInit(): void {
    this.isErro.set(false);
    this.mensagemDeErro.set('');
    this.inicializarFormulario();
  }

  inicializarFormulario(): void {
    this.fornecedorForm = new FormGroup({
      tipoPessoa: new FormControl(TipoPessoa.FISICA, Validators.required),
      cnpj: new FormControl('', [Validators.minLength(14), Validators.maxLength(14)]),
      cpf: new FormControl('', [Validators.minLength(11), Validators.maxLength(11)]),
      nome: new FormControl('', [Validators.required, Validators.minLength(3)]),
      cep: new FormControl('', [Validators.required, Validators.pattern(/^\d{5}-\d{3}$/)]),
      cidade: new FormControl(''),
      email: new FormControl('', [Validators.required, Validators.email]),
      rg: new FormControl('', [Validators.minLength(9), Validators.maxLength(9)]),
      dataNascimento: new FormControl('', Validators.pattern(/^\d{4}-\d{2}-\d{2}$/)),
    });
  }

  isPessoaFisica(): boolean {
    return TipoPessoa.FISICA == this.selectedTipo();
  }

  salvarFornecedor(): void {
    const novoFornecedor = this.fornecedorForm.value;
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    novoFornecedor.id = id ? parseInt(id) : null;

    this.fornecedorService.salvarOuEditarFornecedor(novoFornecedor).subscribe({
      next: (fornecedor) => {
        this.router.navigateByUrl('/lista-fornecedores');
      },
      error: (erro) => {
        this.isErro.set(true);
        this.mensagemDeErro.set(erro.error);
      },
    });
  }

  onTipoChange(event: Event): void {
    const valor = (event.target as HTMLSelectElement).value as TipoPessoa;
    this.selectedTipo.set(valor);
    this.fornecedorForm.get('tipoPessoa')?.setValue(valor);
  }

  cancelar(): void {
    this.router.navigateByUrl('/lista-fornecedores');
  }

  onChangeViacep(): void {
    const valor = this.fornecedorForm.get('cep')?.value;
    if (valor.length == 9) {
      this.viacepService.buscarCep(valor).subscribe({
        next: (endereco) => {
          if (endereco) {
            this.fornecedorForm.get('cidade')?.setValue(endereco.localidade);
          }
        },
        error: (erro) => {
          this.isErro.set(true);
          this.mensagemDeErro.set('CEP não encontrado!');
        },
      });
    }
  }
}
