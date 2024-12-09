import {Component, Inject, OnDestroy, OnInit} from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-comptador-descans',
  templateUrl: './dialog-comptador-descans.component.html',
  styleUrl: './dialog-comptador-descans.component.scss'
})
export class DialogComptadorDescansComponent implements OnInit,OnDestroy {
  temps = 0;
  intervalId: any;

  constructor(
    public dialogRef: MatDialogRef<DialogComptadorDescansComponent>
  ) {}

  ngOnInit() {
    this.intervalId = setInterval(() => {
      this.temps++;
    }, 1000);
  }

  parar() {
    clearInterval(this.intervalId);
    this.dialogRef.close(this.temps);
  }

  ngOnDestroy() {
    clearInterval(this.intervalId);
  }
}
