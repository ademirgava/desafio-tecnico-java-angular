import { Component, inject, OnInit, signal, Signal } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { TipoPessoa } from '../../../components/model/tipoPessoa';
import { CommonModule } from '@angular/common';
import { FornecedorService } from '../../../service/fornecedor/fornecedor.service';
import { Fornecedor } from '../../../components/model/fornecedor';
import { ActivatedRoute, Router } from '@angular/router';
import { CeplaService } from '../../../service/cepla/cepla.service';

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
  private ceplaSerivce = inject(CeplaService);

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
      cep: new FormControl('', [
        Validators.required,
        Validators.minLength(9),
        Validators.maxLength(9),
      ]),
      email: new FormControl('', [Validators.required, Validators.email]),
      rg: new FormControl('', [Validators.minLength(9), Validators.maxLength(9)]),
      dataNascimento: new FormControl('', Validators.pattern(/^\d{2}-\d{2}-\d{4}$/)),
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
        this.mensagemDeErro = erro.error;
      },
    });
  }

  onTipoChange(event: Event): void {
    const valor = (event.target as HTMLSelectElement).value as TipoPessoa;
    this.selectedTipo.set(valor);
  }

  cancelar(): void {
    this.router.navigateByUrl('/lista-fornecedores');
  }

  onChangeCepla(event: Event): void {
    const valor = (event.target as HTMLSelectElement).value;
    if (valor.length == 9) {
      this.ceplaSerivce.buscarCep(valor).subscribe((cep) => {
        console.log(cep);
      });
    }
  }
}
