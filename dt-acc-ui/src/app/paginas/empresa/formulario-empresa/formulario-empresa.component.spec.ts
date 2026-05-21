import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioEmpresaComponent } from './formulario-empresa.component';

describe('FormularioEmpresaComponent', () => {
  let component: FormularioEmpresaComponent;
  let fixture: ComponentFixture<FormularioEmpresaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormularioEmpresaComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(FormularioEmpresaComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
