import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {RutinaModel} from "../models/rutina.model";
import {UserService} from "../_services/user.service";
import {RutinaService} from "../_services/rutina.service";
import {RutinaSimpleModel} from "../models/rutinaSimple.model";

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
export class PrincipalComponent implements OnInit{
  searchText: string = '';
  showFavorites: boolean = false;
  rutinesDefault : RutinaSimpleModel[];
  rutinesMeves : RutinaSimpleModel[];
  loading : number=0;

  constructor(private router : Router,
              private rutinaService : RutinaService,
              private userService : UserService) {
  }

  ngOnInit(): void {
    this.loading--;
    this.rutinaService.getDefaultRutines().subscribe((res : RutinaSimpleModel[])=>{
      this.rutinesDefault = res;
      console.log(this.rutinesDefault)
      this.loading++;
    })
  }

  get filteredRoutines(): RutinaSimpleModel[] {
    return this.rutinesDefault.filter(routine =>
      (!this.showFavorites || routine.isFavourite) &&
      routine.nom.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }

  toggleFavorite(routine: RutinaSimpleModel) {
    routine.isFavourite = !routine.isFavourite;
  }

  addRoutine() {
    this.router.navigate(['/crearRutina']);
  }
}
