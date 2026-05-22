import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Fornecedor } from '../../../components/model/fornecedor';
import { Empresa } from '../../../components/model/empresa';
import { EmpresaService } from '../../../service/empresa/empresa.service';
import { FormsModule } from '@angular/forms';
import { FornecedorService } from '../../../service/fornecedor/fornecedor.service';
import { EmpresaFornecedor } from '../../../components/model/empresa-fornecedor';

@Component({
  selector: 'app-vincular-fornecedor.component',
  imports: [FormsModule],
  templateUrl: './vincular-fornecedor.component.html',
  styleUrl: './vincular-fornecedor.component.css',
})
export class VincularFornecedorComponent implements OnInit {
  private activatedRoute = inject(ActivatedRoute);
  private empresaService = inject(EmpresaService);
  private fornecedorService = inject(FornecedorService);

  fornecedores = signal<Fornecedor[]>([]);
  fornecedoresSelecionado = signal<Fornecedor[]>([]);
  searchText = signal<string>('');
  nome = signal<string>('');
  cnpj = signal<string>('');
  selectedFiltro = signal<string>('nome');

  empresa!: Empresa;

  ngOnInit(): void {
    this.carregarEmpresa();
  }

  carregarEmpresa(): void {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    if (id) {
      this.empresaService.obterEmpresaPorId(parseInt(id)).subscribe({
        next: (empresa) => {
          this.empresa = empresa;
          this.nome.set(empresa.nomeFantasia);
          this.cnpj.set(empresa.cnpj);
          this.fornecedoresSelecionado.set(empresa.fornecedores);
        },
        error: (erro) => {
          console.log(erro.error);
        },
      });
    }
  }

  onKeyUpSearch() {
    if (this.searchText().length >= 2) {
      if (this.selectedFiltro() === 'nome') {
        this.fornecedorService.obterFornecedoresPorNome(this.searchText()).subscribe({
          next: (fornecedoresList) => {
            this.fornecedores.set(fornecedoresList.itens);
          },
          error: (erro) => {
            console.log(erro.error);
          },
        });
      }

      if (this.selectedFiltro() === 'cpf') {
        this.fornecedorService.obterFornecedoresPorCpf(this.searchText()).subscribe({
          next: (fornecedoresList) => {
            this.fornecedores.set(fornecedoresList.itens);
          },
          error: (erro) => {
            console.log(erro.error);
          },
        });
      }

      if (this.selectedFiltro() === 'cnpj') {
        this.fornecedorService.obterFornecedoresPorCnpj(this.searchText()).subscribe({
          next: (fornecedoresList) => {
            this.fornecedores.set(fornecedoresList.itens);
          },
          error: (erro) => {
            console.log(erro.error);
          },
        });
      }
    }
  }

  changeFiltro(): void {
    this.searchText.set('');
  }

  resetBusca(): void {
    this.fornecedores.set([]);
  }

  selecionarFornecedor(id: number): void {
    if (!this.fornecedoresSelecionado().find((f) => f.id === id)) {
      this.empresaService
        .vincularFornecedor(this.empresa.id, this.getEmpresaFornecedor(id))
        .subscribe({
          next: (empresa) => {
            this.fornecedoresSelecionado.set(empresa.fornecedores);
          },
          error: (erro) => {
            console.log(erro.error);
          },
        });
    }
  }

  removerFornecedor(id: number): void {
    this.empresaService
      .desvicularFornecedor(this.empresa.id, this.getEmpresaFornecedor(id))
      .subscribe({
        next: (empresa) => {
          this.fornecedoresSelecionado.set(empresa.fornecedores);
        },
        error: (erro) => {
          console.log(erro.error);
        },
      });
  }

  getEmpresaFornecedor(fornecedorId: number): EmpresaFornecedor {
    return {
      idFornecedor: fornecedorId,
    };
  }
}
