import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DialogAfegirExerciciComponent } from "../dialog-afegir-exercici/dialog-afegir-exercici.component";
import { DialogEditarExerciciComponent } from "../dialog-editar-exercici/dialog-editar-exercici.component";
import { DialogEliminarExerciciComponent } from "../dialog-eliminar-exercici/dialog-eliminar-exercici.component";
import { Router } from "@angular/router";
import { ExerciciService } from "../_services/exercici.service";
import {RutinaModel} from "../models/rutina.model";
import {RutinaService} from "../_services/rutina.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-crear-rutina',
  templateUrl: './crear-rutina.component.html',
  styleUrls: ['./crear-rutina.component.scss']
})
export class CrearRutinaComponent implements OnInit {
  nomRutina: string = '';
  nomForm: FormGroup;
  exercicis: string[] = [];
  totsExercicis: Object = [];
  seriess: number[] = [];
  displayedColumns: string[] = ['exercici', 'series', 'opcions'];
  dataSource: { exercici: any, series: number }[] = [];
  constructor(private dialog: MatDialog,
              private router: Router,
              private exerciciService: ExerciciService,
              private rutinaService: RutinaService,
              private fb: FormBuilder,)
  {
    this.nomForm = this.fb.group({
      nom: ['', Validators.required],
    });
  }

  ngOnInit() {
    this.exerciciService.getAllExercicis().subscribe({
      next: (data) => {
        console.log(data);
        this.totsExercicis = data;
      },
      error: (err) => {
        console.error('Error al obtener exercici', err);
      },
    });
  }

  tornarPrincipal() {
    this.router.navigate(['']);
  }

  afegirExercici() {
    const dialogRef = this.dialog.open(DialogAfegirExerciciComponent, {
      width: '500px',
      data: { totsExercicis: this.totsExercicis }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.exercicis.push(result.exercici.id);
        this.seriess.push(result.series);
        this.dataSource = [...this.dataSource, {
          exercici: result.exercici.nom,
          series: result.series
        }];
      }
    });
  }

  editarExercici(index: number) {
    const dialogRef = this.dialog.open(DialogEditarExerciciComponent, {
      width: '500px',
      data: {
        totsExercicis: this.totsExercicis,
        exercici: this.dataSource[index].exercici,
        series: this.dataSource[index].series,
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.exercicis[index] = result.exercici.id;
        this.seriess[index] = result.series;

        this.dataSource[index] = {
          exercici: result.exercici.nom,
          series: result.series
        };
        this.dataSource = [...this.dataSource];

      }
    });
  }

  eliminarExercici(index: number) {
    const dialogRef = this.dialog.open(DialogEliminarExerciciComponent, {
      width: '500px',
      data: {
        exercici: this.dataSource[index].exercici,
        series: this.dataSource[index].series
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'confirmado') {
        this.exercicis.splice(index, 1);
        this.seriess.splice(index, 1);
        this.dataSource.splice(index, 1);
        this.dataSource = [...this.dataSource];
      }
    });
  }


  enviarRutina() {

    if(this.nomForm.valid){
      const rutina: RutinaModel = {
        nomRutina: this.nomRutina,
        exercicis: this.exercicis,
        series: this.seriess,
      };
      this.rutinaService.postRutina(rutina).subscribe();
      this.tornarPrincipal();
    }
    else this.nomForm.markAllAsTouched()


  }

}
