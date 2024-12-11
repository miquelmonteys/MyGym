import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-dialog-editar-exercici',
  templateUrl: './dialog-editar-exercici.component.html',
  styleUrls: ['./dialog-editar-exercici.component.scss']
})
export class DialogEditarExerciciComponent {
  exerciciForm: FormGroup;
  exercicis: any[] = [];
  totsExercicis: any[] = [];


  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<DialogEditarExerciciComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.totsExercicis = data.totsExercicis || [];
    const exerciciSeleccionado = this.totsExercicis.find(ex => ex.nom === data.exercici);

    this.exerciciForm = this.fb.group({
      exercici: [exerciciSeleccionado, Validators.required],
      series: [data.series, Validators.required]
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  guardarExercici() {
    if (this.exerciciForm.valid) {
      this.dialogRef.close(this.exerciciForm.value);
    }
  }
}
