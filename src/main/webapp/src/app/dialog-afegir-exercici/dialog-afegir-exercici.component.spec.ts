import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogAfegirExerciciComponent } from './dialog-afegir-exercici.component';

describe('DialogAfegirExerciciComponent', () => {
  let component: DialogAfegirExerciciComponent;
  let fixture: ComponentFixture<DialogAfegirExerciciComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DialogAfegirExerciciComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DialogAfegirExerciciComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
