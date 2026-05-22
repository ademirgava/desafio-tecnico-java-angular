import { Component, inject, OnInit, signal } from '@angular/core';
import { EmpresaService } from '../../../service/empresa/empresa.service';
import { Router } from '@angular/router';
import { Empresa } from '../../../components/model/empresa';
import { MatTableDataSource } from '@angular/material/table';
import { Column } from '../../../components/model/column';
import { CommonModule } from '@angular/common';
import { TableComponent } from '../../../components/table/table.component';

@Component({
  selector: 'app-lista-empresas',
  imports: [CommonModule, TableComponent],
  templateUrl: './lista-empresas.component.html',
  styleUrl: './lista-empresas.component.css',
})
export class ListaEmpresasComponent implements OnInit {
  private empresaService = inject(EmpresaService);
  private router = inject(Router);

  mensagemSemDados: string = 'Não há fornecedor cadastrado!';
  tableData: Array<Empresa> = [];
  dataSource: MatTableDataSource<Empresa> = new MatTableDataSource();
  empresas = signal<Empresa[]>([]);

  tableColumns: Array<Column> = [
    {
      columnDef: 'cnpj',
      header: 'CNPJ',
      cell: (element: Record<string, any>) => `${element['cnpj']}`,
    },
    {
      columnDef: 'nomeFantasia',
      header: 'Nome',
      cell: (element: Record<string, any>) => `${element['nomeFantasia']}`,
    },
    {
      columnDef: 'cep',
      header: 'CEP',
      cell: (element: Record<string, any>) => `${element['cep']}`,
    },
    {
      columnDef: 'cidade',
      header: 'Cidade',
      cell: (element: Record<string, any>) => `${element['cidade']}`,
    },
    {
      columnDef: 'actions',
      header: 'Ações',
      cell: (element: Record<string, any>) => `${element['id']}`,
      isVincular: true,
    },
  ];

  ngOnInit(): void {
    this.empresaService.obterEmpresas().subscribe({
      next: (empresas) => {
        this.empresas.set(empresas.itens);
        this.tableData = empresas.itens;
        this.dataSource = new MatTableDataSource(this.tableData);
      },
      error: (erro) => {
        console.log(erro.error);
      },
    });
  }

  novaEmpresa(): void {
    this.router.navigateByUrl('/formulario-empresa');
  }

  handleVincular = (empresa: Empresa) => {
    this.router.navigate(['/vincular-fornecedores', empresa.id]);
  };
}
