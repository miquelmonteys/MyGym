import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-afegir-exercici',
  templateUrl: './dialog-afegir-exercici.component.html',
  styleUrls: ['./dialog-afegir-exercici.component.scss']
})
export class DialogAfegirExerciciComponent {
  exerciciForm: FormGroup;
  totsExercicis: any[] = [];

  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<DialogAfegirExerciciComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.totsExercicis = data.totsExercicis || [];
    this.exerciciForm = this.fb.group({
      exercici: ['', Validators.required],
      series: ['', [Validators.required, Validators.min(1)]],
    });
  }

  onGuardar() {
    if (this.exerciciForm.valid) {
      this.dialogRef.close({
        exercici: this.exerciciForm.value.exercici,
        series: this.exerciciForm.value.series,
      });
    }
  }

  onCancelar() {
    this.dialogRef.close();
  }
}
