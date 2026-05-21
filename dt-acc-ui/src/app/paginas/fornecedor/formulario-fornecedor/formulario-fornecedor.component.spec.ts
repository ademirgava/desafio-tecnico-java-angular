import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioFornecedorComponent } from './formulario-fornecedor.component';

describe('FormularioFornecedorComponent', () => {
  let component: FormularioFornecedorComponent;
  let fixture: ComponentFixture<FormularioFornecedorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormularioFornecedorComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(FormularioFornecedorComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
