import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DialogComptadorDescansComponent } from '../dialog-comptador-descans/dialog-comptador-descans.component';
import { Router } from '@angular/router';
import { RutinaService } from '../_services/rutina.service';
import { ExerciciService } from '../_services/exercici.service';

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
    private exerciciService: ExerciciService
  ) {}

  ngOnInit() {
    const navigation = history.state;
    this.rutina = navigation.rutina;

    if (!this.rutina || !this.rutina.id) {
      console.error('Rutina no encontrada en el estado de navegación.');
      return;
    }

    this.rutinaService.getRutina(this.rutina.id).subscribe({
      next: (data) => {
        console.log('Rutina cargada:', data);
        this.exercicisId = data['exercicis'] || [];

        this.exercicisId.forEach((exerciciId) => {
          this.exerciciService.getExercici(exerciciId).subscribe({
            next: (exerciciData) => {
              exerciciData['series'] = Array(3).fill(null).map(() => ({
                reps: '',
                weight: '',
              }));
              exerciciData['descansos'] = Array(3).fill(null);

              this.exercicis.push(exerciciData);
              console.log('Exercici carregat:', exerciciData);
            },
            error: (err) => {
              console.error('Error al obtener exercici', err);
            },
          });
        });
      },
      error: (err) => {
        console.error('Error al obtener rutina', err);
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
        console.log('Exercicis después de descansar:', this.exercicis);
      }
    });
  }

  acabar(): void {
    console.log('Rutina finalizada:', this.exercicis);
    this.router.navigate(['/finalizada']);
  }

  tornarPrincipal() {
    this.router.navigate(['']);
  }
}
