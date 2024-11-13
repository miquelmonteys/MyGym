import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-editar-exercici',
  templateUrl: './dialog-editar-exercici.component.html',
  styleUrls: ['./dialog-editar-exercici.component.scss']
})
export class DialogEditarExerciciComponent {
  constructor(
    public dialogRef: MatDialogRef<DialogEditarExerciciComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  guardarExercici() {
    this.dialogRef.close(this.data);
  }
}
