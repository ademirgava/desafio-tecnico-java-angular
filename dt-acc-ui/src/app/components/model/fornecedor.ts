import { TipoPessoa } from './tipoPessoa';

export interface Fornecedor {
  id: number;
  tipoPessoa: TipoPessoa;
  cnpj: string;
  cpf: string;
  nome: string;
  cep: string;
  email: string;
  rg: string;
  dataNascimento: Date;
}
