import { Component, inject, OnInit, signal } from '@angular/core';
import { EmpresaService } from '../../../service/empresa/empresa.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ViacepService } from '../../../service/viacep/viacep.service';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { error } from 'console';

@Component({
  selector: 'app-formulario-empresa',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './formulario-empresa.component.html',
  styleUrl: './formulario-empresa.component.css',
})
export class FormularioEmpresaComponent implements OnInit {
  private empresaService = inject(EmpresaService);
  private activatedRoute = inject(ActivatedRoute);
  private router = inject(Router);
  private viacepService = inject(ViacepService);

  empresaForm!: FormGroup;
  mensagemDeErro = signal<string>('');
  isErro = signal<boolean>(false);

  ngOnInit(): void {
    this.isErro.set(false);
    this.mensagemDeErro.set('');
    this.inicializarFormulario();
  }

  inicializarFormulario(): void {
    this.empresaForm = new FormGroup({
      cnpj: new FormControl('', [Validators.minLength(14), Validators.maxLength(14)]),
      nomeFantasia: new FormControl('', [Validators.required, Validators.minLength(3)]),
      cep: new FormControl('', [Validators.required, Validators.pattern(/^\d{5}-\d{3}$/)]),
      cidade: new FormControl('', Validators.required),
    });
  }

  salvarEmpresa(): void {
    const novaEmpresa = this.empresaForm.value;
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    novaEmpresa.id = id ? parseInt(id) : null;

    this.empresaService.salvarOuEditar(novaEmpresa).subscribe({
      next: (empresa) => {
        this.cancelar();
      },
      error: (erro) => {
        this.isErro.set(true);
        this.mensagemDeErro.set(erro.error);
      },
    });
  }

  cancelar(): void {
    this.router.navigateByUrl('/lista-empresas');
  }

  onChangeViacep(): void {
    const valor = this.empresaForm.get('cep')?.value;
    if (valor.length == 9) {
      this.viacepService.buscarCep(valor).subscribe({
        next: (endereco) => {
          if (endereco) {
            this.empresaForm.get('cidade')?.setValue(endereco.localidade);
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
