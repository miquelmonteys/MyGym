import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogEliminarExerciciComponent } from './dialog-eliminar-exercici.component';

describe('DialogEliminarExerciciComponent', () => {
  let component: DialogEliminarExerciciComponent;
  let fixture: ComponentFixture<DialogEliminarExerciciComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DialogEliminarExerciciComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DialogEliminarExerciciComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
