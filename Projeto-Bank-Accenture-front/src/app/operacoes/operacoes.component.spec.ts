import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OperacoesComponent } from './operacoes.component';

describe('OperacoesComponent', () => {
  let component: OperacoesComponent;
  let fixture: ComponentFixture<OperacoesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OperacoesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OperacoesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
