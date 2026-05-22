import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CeplaService {
  private readonly API = '/cepla';
  private http = inject(HttpClient);

  buscarCep(cep: string): Observable<any> {
    const headers = new HttpHeaders({
      Accept: 'application/json',
    });
    return this.http.get<any>('/cepla/?cep=' + cep, { headers });
  }
}
