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
  rutinesFavourite : string[];
  loading : number=0;

  constructor(private router : Router,
              private rutinaService : RutinaService,
              private userService : UserService) {
  }

  ngOnInit(): void {
    this.loading--;
    this.userService.getMyFavourites().subscribe((favourties : string[])=>{
      this.rutinesFavourite = favourties;
      this.rutinaService.getDefaultRutines().subscribe((res : RutinaSimpleModel[])=>{
        this.rutinesDefault = res;
        for(let i = 0; i < this.rutinesDefault.length; i++){
          this.rutinesDefault[i].isFavourite = this.rutinesFavourite.includes(this.rutinesDefault[i].id);
        }
      })

      this.rutinaService.getPropiesRutines().subscribe((res2 : RutinaSimpleModel[])=>{
        this.rutinesMeves = res2;
        for(let i = 0; i < this.rutinesMeves.length; i++){
          this.rutinesMeves[i].isFavourite = this.rutinesFavourite.includes(this.rutinesMeves[i].id);
        }
      })
      this.loading++;
    })
  }

  get filteredDefaultRoutines(): RutinaSimpleModel[] {
    return this.rutinesDefault.filter(routine =>
      (!this.showFavorites || routine.isFavourite) &&
      routine.nom.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }

  get filteredPropiesRoutines(): RutinaSimpleModel[] {
    return this.rutinesMeves.filter(routine =>
      (!this.showFavorites || routine.isFavourite) &&
      routine.nom.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }

  addRoutine() {
    this.router.navigate(['/crearRutina']);
  }

  changeFavourite(rutina: RutinaSimpleModel) {
    if (rutina.isFavourite == false) {
      this.rutinesDefault[this.rutinesDefault.indexOf(rutina)].isFavourite=false;
      this.rutinesMeves[this.rutinesMeves.indexOf(rutina)].isFavourite=false;
      this.rutinesFavourite.splice(this.rutinesFavourite.indexOf(rutina.id), 1)
    }
    else {
      this.rutinesDefault[this.rutinesDefault.indexOf(rutina)].isFavourite=true;
      this.rutinesMeves[this.rutinesMeves.indexOf(rutina)].isFavourite=true;
      this.rutinesFavourite.push(rutina.id)
    }
  }
}
