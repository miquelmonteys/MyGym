import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-afegir-exercici',
  templateUrl: './dialog-afegir-exercici.component.html',
  styleUrl: './dialog-afegir-exercici.component.scss'
})
export class DialogAfegirExerciciComponent {
  exerciciForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<DialogAfegirExerciciComponent>
  ) {
    this.exerciciForm = this.fb.group({
      exercici: ['', Validators.required],
      series: ['', Validators.required],
      repeticions: ['', Validators.required],
      descans: ['', Validators.required]
    });
  }

  onGuardar() {
    if (this.exerciciForm.valid) {
      this.dialogRef.close(this.exerciciForm.value);
    }
  }
  onCancelar() {
    this.dialogRef.close();
  }
}
