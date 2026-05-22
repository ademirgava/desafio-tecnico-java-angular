import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VincularFornecedorComponent } from './vincular-fornecedor.component';

describe('VincularFornecedorComponent', () => {
  let component: VincularFornecedorComponent;
  let fixture: ComponentFixture<VincularFornecedorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VincularFornecedorComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(VincularFornecedorComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
