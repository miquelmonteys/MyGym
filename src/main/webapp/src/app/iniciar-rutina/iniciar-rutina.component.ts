import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DialogComptadorDescansComponent } from '../dialog-comptador-descans/dialog-comptador-descans.component';
import { Router } from '@angular/router';
import { RutinaService } from '../_services/rutina.service';
import { ExerciciService } from '../_services/exercici.service';
import {HistoricService} from "../_services/historic.service";

@Component({
  selector: 'app-iniciar-rutina',
  templateUrl: './iniciar-rutina.component.html',
  styleUrls: ['./iniciar-rutina.component.scss']
})
export class IniciarRutinaComponent implements OnInit {
  rutina: any;
  exercicisId: any[] = [];
  exercicis: any[] = [];
  loading: number = 0;

  constructor(
    private dialog: MatDialog,
    private router: Router,
    private rutinaService: RutinaService,
    private exerciciService: ExerciciService,
    private historicService: HistoricService
  ) {}

  ngOnInit() {
    const navigation = history.state;
    this.rutina = navigation.rutina;

    if (!this.rutina || !this.rutina.id) {
      console.error('Rutina no trobada.');
      return;
    }
    this.rutinaService.getRutina(this.rutina.id).subscribe({
      next: (data) => {
        console.log('Rutina carregada:', data);
        this.rutina = data;
        this.exercicisId = data['exercicis'] || [];

        this.exercicisId.forEach((exerciciId, index) => {
          this.exerciciService.getExercici(exerciciId).subscribe({
            next: (exerciciData) => {
              exerciciData['series'] = Array(this.rutina.series[index]).fill(null).map(() => ({
                reps: '',
                pes: '',
              }));
              exerciciData['descansos'] = Array(this.rutina.series[index]).fill(null);

              this.exercicis.push(exerciciData);
            },
            error: (err) => {
              console.error('Error al obtenir exercici', err);
            },
          });
        });
      },
      error: (err) => {
        console.error('Error al obtenir rutina', err);
      },
    });
    this.loading=1;
  }


  descansar(i: number, j: number) {
    const dialogRef = this.dialog.open(DialogComptadorDescansComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe((temps) => {
      if (temps !== undefined) {
        this.exercicis[i].descansos[j] = temps;
        console.log('Exercicis despres de descansar:', this.exercicis);
      }
    });
  }

  acabar(): void {
    console.log('Rutina acabada:', this.exercicis);
    var infoExercici: {};
    var exercicisFinals: {
      exerciciId: string,
      series: number,
      descans: number[],
      pes: number[],
      reps: number[]
    }[] = [];
    for(let i=0;i<this.exercicis.length;i++){
      var pes = [];
      var reps = [];
      for(let j=0;j<this.exercicis[i].series.length;j++){
        pes.push(this.exercicis[i].series[j].pes)
        reps.push(this.exercicis[i].series[j].reps)
      }
      exercicisFinals.push(
        {
          exerciciId: this.exercicis[i].id,
          series: this.exercicis[i].series.length,
          descans: this.exercicis[i].descansos,
          pes: pes,
          reps: reps
        }
      )
    }
    this.historicService.postRutina({
      exercicis: exercicisFinals
    }).subscribe(
      () => {
        this.router.navigate(['']);
      }
    );

  }

  tornarPrincipal() {
    this.router.navigate(['']);
  }
}
