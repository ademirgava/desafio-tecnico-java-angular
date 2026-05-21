import { Routes } from '@angular/router';
import { HomeComponent } from './paginas/home/home.component';
import { ListaEmpresasComponent } from './paginas/empresa/lista-empresas/lista-empresas.component';
import { FormularioEmpresaComponent } from './paginas/empresa/formulario-empresa/formulario-empresa.component';
import { ListaFornecedoresComponent } from './paginas/fornecedor/lista-fornecedores/lista-fornecedores.component';
import { FormularioFornecedorComponent } from './paginas/fornecedor/formulario-fornecedor/formulario-fornecedor.component';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'lista-empresas', component: ListaEmpresasComponent },
  { path: 'formulario-empresa', component: FormularioEmpresaComponent },
  { path: 'lista-fornecedores', component: ListaFornecedoresComponent },
  { path: 'formulario-fornecedor', component: FormularioFornecedorComponent },
];
