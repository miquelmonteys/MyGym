import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-eliminar-exercici',
  templateUrl: './dialog-eliminar-exercici.component.html',
  styleUrls: ['./dialog-eliminar-exercici.component.scss']
})
export class DialogEliminarExerciciComponent {
  constructor(
    public dialogRef: MatDialogRef<DialogEliminarExerciciComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  confirmarEliminacion() {
    this.dialogRef.close('confirmado');
  }
}
