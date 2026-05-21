export interface Column {
  columnDef: string;
  header: string;
  cell: Function;
  isDate?: boolean;
}
