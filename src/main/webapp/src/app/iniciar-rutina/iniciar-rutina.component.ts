import {Component, OnInit} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import {DialogComptadorDescansComponent} from "../dialog-comptador-descans/dialog-comptador-descans.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-iniciar-rutina',
  templateUrl: './iniciar-rutina.component.html',
  styleUrl: './iniciar-rutina.component.scss'
})
export class IniciarRutinaComponent implements OnInit{

  rutina = [
    { exercici: "Press banca", series: 4, seriesRepeticions: Array(4).fill(''), seriesPes: Array(4).fill(''), descansos: Array(4).fill(null) },
    { exercici: "Press banca 2", series: 4, seriesRepeticions: Array(4).fill(''), seriesPes: Array(4).fill(''), descansos: Array(4).fill(null) },
    { exercici: "Press banca 3", series: 4, seriesRepeticions: Array(4).fill(''), seriesPes: Array(4).fill(''), descansos: Array(4).fill(null) },
  ];
  constructor(private dialog: MatDialog, private router : Router) {}
  ngOnInit(){
    console.log(this.rutina.length)
  }
  descansar(i: number, j: number){
    const dialogRef = this.dialog.open(DialogComptadorDescansComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe((temps) => {
      if (temps !== undefined) {
        this.rutina[i].descansos[j] = temps;
      }
    });
  }
  acabar(): void{

  }
  tornarPrincipal() {
    this.router.navigate(['']);
  }
}
