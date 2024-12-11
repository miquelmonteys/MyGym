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


  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<DialogEditarExerciciComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.exercicis = data.exercicis || [];
    this.exerciciForm = this.fb.group({
      exercici: [data.exercici, Validators.required],
      repeticions: [data.repeticions, Validators.required]
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
