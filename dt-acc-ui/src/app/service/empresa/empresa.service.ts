import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Page } from '../../components/model/page';
import { Observable } from 'rxjs';
import { Empresa } from '../../components/model/empresa';

@Injectable({
  providedIn: 'root',
})
export class EmpresaService {
  private readonly API = '/api/empresas';
  private http = inject(HttpClient);

  obterEmpresas(): Observable<Page> {
    return this.http.get<Page>(this.API);
  }

  salvarOuEditar(empresa: Empresa): Observable<Empresa> {
    if (empresa.id) {
      return this.editarEmpresa(empresa);
    }
    return this.http.post<Empresa>(this.API, empresa);
  }

  editarEmpresa(empresa: Empresa): Observable<Empresa> {
    return this.http.put<Empresa>(`${this.API}/${empresa.id}`, empresa);
  }
}
