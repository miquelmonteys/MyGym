import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogComptadorDescansComponent } from './dialog-comptador-descans.component';

describe('DialogComptadorDescansComponent', () => {
  let component: DialogComptadorDescansComponent;
  let fixture: ComponentFixture<DialogComptadorDescansComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DialogComptadorDescansComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DialogComptadorDescansComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
