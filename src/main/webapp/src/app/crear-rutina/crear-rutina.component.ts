import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { DialogAfegirExerciciComponent } from "../dialog-afegir-exercici/dialog-afegir-exercici.component";
import { DialogEditarExerciciComponent } from "../dialog-editar-exercici/dialog-editar-exercici.component";
import { DialogEliminarExerciciComponent } from "../dialog-eliminar-exercici/dialog-eliminar-exercici.component";

@Component({
  selector: 'app-crear-rutina',
  templateUrl: './crear-rutina.component.html',
  styleUrls: ['./crear-rutina.component.scss']
})
export class CrearRutinaComponent {
  dataSource: MatTableDataSource<any> = new MatTableDataSource();
  displayedColumns: string[] = ['exercici', 'series', 'repeticions', 'descans', 'opcions'];

  constructor(private dialog: MatDialog) {}

  afegirExercici() {
    const dialogRef = this.dialog.open(DialogAfegirExerciciComponent, {
      width: '500px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.dataSource.data = [...this.dataSource.data, result];
      }
    });
  }
  editarExercici(exercici: any) {
    const dialogRef = this.dialog.open(DialogEditarExerciciComponent, {
      width: '500px',
      maxWidth: '100%',
      autoFocus: false,
      data: { ...exercici }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        const index = this.dataSource.data.findIndex(item => item === exercici);
        if (index !== -1) {
          this.dataSource.data[index] = result;
          this.dataSource.data = [...this.dataSource.data];
        }
      }
    });
  }
  eliminarExercici(exercici: any) {
    const dialogRef = this.dialog.open(DialogEliminarExerciciComponent, {
      width: '500px',
      data: { ...exercici } // Pasamos los datos del ejercicio a eliminar
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'confirmado') {
        this.dataSource.data = this.dataSource.data.filter(item => item !== exercici);
      }
    });
  }

  enviarRutina() {
    const datos = this.dataSource.data;
    console.log('Dades enviades al backend:', JSON.stringify(datos));
  }
}
