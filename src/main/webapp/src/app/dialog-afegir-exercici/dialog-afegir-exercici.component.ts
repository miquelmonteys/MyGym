import {Component, Inject, OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ExerciciService} from "../_services/exercici.service";

@Component({
  selector: 'app-dialog-afegir-exercici',
  templateUrl: './dialog-afegir-exercici.component.html',
  styleUrl: './dialog-afegir-exercici.component.scss'
})
export class DialogAfegirExerciciComponent{
  exerciciForm: FormGroup;
  exercicis: any[] = [];

  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<DialogAfegirExerciciComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.exercicis = data.exercicis || [];
    this.exerciciForm = this.fb.group({
      exercici: ['', Validators.required],
      repeticions: ['', Validators.required],
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
