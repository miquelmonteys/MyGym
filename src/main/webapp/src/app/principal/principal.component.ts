import { Component } from '@angular/core';
import {Router} from "@angular/router";

interface Routine {
  name: string;
  image: string;
  isFavorite: boolean;
}

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrl: './principal.component.scss'
})
export class PrincipalComponent {
  searchText: string = '';
  showFavorites: boolean = false;

  constructor(private router : Router) {
  }

  routines: Routine[] = [
    { name: 'Rutina de Pit 1', image: 'assets/images/prova1.jpg', isFavorite: false },
    { name: 'Rutina de Pit 2', image: 'assets/images/prova1.jpg', isFavorite: true },
    { name: 'Rutina de Pit 3', image: 'assets/images/prova1.jpg', isFavorite: true },
    { name: 'Rutina de Pit 4', image: 'assets/images/prova1.jpg', isFavorite: false },
    { name: 'Rutina de Pit 5', image: 'assets/images/prova1.jpg', isFavorite: false },
    { name: 'Rutina de Pit 6', image: 'assets/images/prova1.jpg', isFavorite: true },
    { name: 'Rutina de Pit 7', image: 'assets/images/prova1.jpg', isFavorite: false },
    { name: 'Rutina de Pit 8', image: 'assets/images/prova1.jpg', isFavorite: false },
    { name: 'Rutina de Pit 9', image: 'assets/images/prova1.jpg', isFavorite: true },
    // Agrega más rutinas según sea necesario
  ];

  get filteredRoutines(): Routine[] {
    return this.routines.filter(routine =>
      (!this.showFavorites || routine.isFavorite) &&
      routine.name.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }

  toggleFavorite(routine: Routine) {
    routine.isFavorite = !routine.isFavorite;
  }

  addRoutine() {
    this.router.navigate(['/crearRutina']);
  }
}
