import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogEditarExerciciComponent } from './dialog-editar-exercici.component';

describe('DialogEditarExerciciComponent', () => {
  let component: DialogEditarExerciciComponent;
  let fixture: ComponentFixture<DialogEditarExerciciComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DialogEditarExerciciComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DialogEditarExerciciComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
