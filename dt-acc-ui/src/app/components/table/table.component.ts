import { Component, Input, OnInit } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { CommonModule } from '@angular/common';
import { Column } from '../model/column';

@Component({
  selector: 'app-table',
  imports: [CommonModule, MatTableModule],
  templateUrl: './table.component.html',
  styleUrl: './table.component.css',
})
export class TableComponent<T> implements OnInit {
  @Input() tableColumns: Array<Column> = [];
  @Input() dataSource: MatTableDataSource<T> = new MatTableDataSource();
  @Input() mensagemSemDados!: string;
  @Input() vincularCallback!: (args: T) => void;

  tableData: Array<T> = [];
  displayedColumns: Array<string> = [];

  constructor() {}

  ngOnInit(): void {
    this.displayedColumns = this.tableColumns.map((c) => c.columnDef);
  }

  temDados = () => {
    return this.dataSource.data.length > 0;
  };

  vincularFornecedores(element: T): void {
    if (this.vincularCallback) {
      this.vincularCallback(element);
    }
  }
}
