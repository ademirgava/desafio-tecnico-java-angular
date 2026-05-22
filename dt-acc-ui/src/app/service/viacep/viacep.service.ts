import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Endereco } from '../../components/model/endereco';

@Injectable({
  providedIn: 'root',
})
export class ViacepService {
  private readonly API = 'https://viacep.com.br/ws/';
  private http = inject(HttpClient);

  buscarCep(cep: string): Observable<Endereco> {
    cep = cep.replace('-', '');
    return this.http.get<Endereco>(this.API + cep + '/json');
  }
}
