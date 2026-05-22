import { Component, inject, OnInit } from '@angular/core';
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
  ];

  ngOnInit(): void {}

  novaEmpresa(): void {
    this.router.navigateByUrl('/formulario-empresa');
  }
}
