import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IniciarRutinaComponent } from './iniciar-rutina.component';

describe('IniciarRutinaComponent', () => {
  let component: IniciarRutinaComponent;
  let fixture: ComponentFixture<IniciarRutinaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IniciarRutinaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(IniciarRutinaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
