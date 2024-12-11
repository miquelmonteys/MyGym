import {Component, OnInit} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { DialogAfegirExerciciComponent } from "../dialog-afegir-exercici/dialog-afegir-exercici.component";
import { DialogEditarExerciciComponent } from "../dialog-editar-exercici/dialog-editar-exercici.component";
import { DialogEliminarExerciciComponent } from "../dialog-eliminar-exercici/dialog-eliminar-exercici.component";
import {Router} from "@angular/router";
import {ExerciciService} from "../_services/exercici.service";

@Component({
  selector: 'app-crear-rutina',
  templateUrl: './crear-rutina.component.html',
  styleUrls: ['./crear-rutina.component.scss']
})
export class CrearRutinaComponent implements OnInit{
  dataSource: MatTableDataSource<any> = new MatTableDataSource();
  displayedColumns: string[] = ['exercici', 'repeticions', 'opcions'];
  exercicis: any;

  constructor(private dialog: MatDialog, private router : Router, private exerciciService: ExerciciService) {}

  ngOnInit(){
    this.exerciciService.getAllExercicis().subscribe({
      next: (data) => {
        this.exercicis = data;
        console.log(data);
      },
      error: (err) => {
        console.error('Error al obtener exercici', err);
      },
    });
  }

  afegirExercici() {
    const dialogRef = this.dialog.open(DialogAfegirExerciciComponent, {
      width: '500px',
      data: { exercicis: this.exercicis }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.dataSource.data = [...this.dataSource.data, result];
      }
    });
  }

  tornarPrincipal() {
    this.router.navigate(['']);
  }
  editarExercici(exercici: any) {
    const dialogRef = this.dialog.open(DialogEditarExerciciComponent, {
      width: '500px',
      data: { ...exercici, exercicis: this.exercicis  }
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
      data: { ...exercici }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'confirmado') {
        this.dataSource.data = this.dataSource.data.filter(item => item !== exercici);
      }
    });
  }

  enviarRutina() {
    const dades = this.dataSource.data;
    console.log('Dades enviades al backend:', JSON.stringify(dades));
  }
}
