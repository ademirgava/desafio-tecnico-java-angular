import { Component, inject, OnInit, signal } from '@angular/core';
import { TableComponent } from '../../../components/table/table.component';
import { Fornecedor } from '../../../components/model/fornecedor';
import { MatTableDataSource } from '@angular/material/table';
import { Column } from '../../../components/model/column';
import { FornecedorService } from '../../../service/fornecedor/fornecedor.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-lista-fornecedores',
  imports: [TableComponent],
  templateUrl: './lista-fornecedores.component.html',
  styleUrl: './lista-fornecedores.component.css',
})
export class ListaFornecedoresComponent implements OnInit {
  private fornecedorService = inject(FornecedorService);
  private router = inject(Router);

  mensagemSemDados: string = 'Não há fornecedor cadastrado!';
  tableData: Array<Fornecedor> = [];
  dataSource: MatTableDataSource<Fornecedor> = new MatTableDataSource();
  fornecedores = signal<Fornecedor[]>([]);

  tableColumns: Array<Column> = [
    {
      columnDef: 'tipoPessoa',
      header: 'Tipo',
      cell: (element: Record<string, any>) => `${element['tipoPessoa']}`,
    },
    {
      columnDef: 'cnpj',
      header: 'CNPJ',
      cell: (element: Record<string, any>) => `${element['cnpj']}`,
    },
    {
      columnDef: 'cpf',
      header: 'CPF',
      cell: (element: Record<string, any>) => `${element['cpf']}`,
    },
    {
      columnDef: 'nome',
      header: 'Nome',
      cell: (element: Record<string, any>) => `${element['nome']}`,
    },
    {
      columnDef: 'cep',
      header: 'CEP',
      cell: (element: Record<string, any>) => `${element['cep']}`,
    },
    {
      columnDef: 'email',
      header: 'E-mail',
      cell: (element: Record<string, any>) => `${element['email']}`,
    },
    {
      columnDef: 'rg',
      header: 'RG',
      cell: (element: Record<string, any>) => `${element['rg']}`,
    },
    {
      columnDef: 'dataNascimento',
      header: 'Data de nascimento',
      cell: (element: Record<string, any>) => `${element['dataNascimento']}`,
      isDate: true,
    },
  ];

  ngOnInit(): void {
    this.fornecedorService.obterFornecedores().subscribe((fornecedores) => {
      this.fornecedores.set(fornecedores.itens);
      this.tableData = fornecedores.itens;
      this.dataSource = new MatTableDataSource(this.tableData);
    });
  }

  novoFornecedor(): void {
    this.router.navigateByUrl('/formulario-fornecedor');
  }
}
