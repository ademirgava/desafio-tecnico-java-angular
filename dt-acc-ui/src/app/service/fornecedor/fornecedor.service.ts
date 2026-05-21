import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Fornecedor } from '../../components/model/fornecedor';
import { Page } from '../../components/model/page';

@Injectable({
  providedIn: 'root',
})
export class FornecedorService {
  private readonly API = 'http://localhost:8080/api/fornecedores';
  private http = inject(HttpClient);

  obterFornecedores(): Observable<Page> {
    return this.http.get<Page>(this.API);
  }

  obterFornecedoresPorNome(nome: string): Observable<Fornecedor[]> {
    return this.http.get<Fornecedor[]>(`${this.API}?nome=${nome}`);
  }

  obterFornecedoresPorCpf(cpf: string): Observable<Fornecedor[]> {
    return this.http.get<Fornecedor[]>(`${this.API}?cpf=${cpf}`);
  }

  obterFornecedoresPorCnpj(cnpj: string): Observable<Fornecedor[]> {
    return this.http.get<Fornecedor[]>(`${this.API}?cnpj=${cnpj}`);
  }

  salvarFornecedor(fornecedor: Fornecedor): Observable<Fornecedor> {
    return this.http.post<Fornecedor>(this.API, fornecedor);
  }
}
