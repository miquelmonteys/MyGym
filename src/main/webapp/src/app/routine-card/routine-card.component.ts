import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChange, SimpleChanges} from '@angular/core';
import {RutinaSimpleModel} from "../models/rutinaSimple.model";
import {UserService} from "../_services/user.service";
import {Router} from "@angular/router";
import {ExerciciService} from "../_services/exercici.service";
import {DomSanitizer, SafeUrl} from "@angular/platform-browser";
import {RutinaService} from "../_services/rutina.service";

@Component({
  selector: 'app-routine-card',
  templateUrl: './routine-card.component.html',
  styleUrl: './routine-card.component.scss'
})
export class RoutineCardComponent implements OnInit {
  @Input() routine!: RutinaSimpleModel;
  @Output() favoriteToggle: EventEmitter<RutinaSimpleModel> = new EventEmitter();
  imageUrl: any;
  exercicis : string[] = []
  loading : number=0;
  imageUrls: string[] = []; // Ahora es un array de strings que almacenará las URLs directas de objectURL




  constructor(private userService: UserService,    private sanitizer: DomSanitizer
    , private router: Router,private exerciciService : ExerciciService,private rutinaSerice : RutinaService) {}


  ngOnInit(): void {
    this.loading ++;

    this.rutinaSerice.getRutina(this.routine.id).subscribe((res) => {
      this.exercicis = res['exercicis'] || [];
      console.log(this.exercicis);

      const imagePromises = this.exercicis.map((exerciciId) =>
        this.exerciciService.getImatge(exerciciId).toPromise()
      );

      Promise.all(imagePromises)
        .then((imageBlobs) => {
          this.imageUrls = imageBlobs.map((imageBlob) => {
            const objectURL = URL.createObjectURL(imageBlob);
            return objectURL;
          });
          this.loading --;
        })
        .catch((error) => {
          console.error('Error cargando las imágenes:', error);
          this.loading --;
        });
    });
  }


  saveRoutine(event: Event) {
    if (!this.routine.isFavourite) {
      this.userService.addFavourite(this.routine.id).subscribe(res => {
        this.routine.isFavourite = true;
        this.favoriteToggle.emit(this.routine);
      });
    } else {
      this.userService.deleteFavourite(this.routine.id).subscribe(res => {
        this.routine.isFavourite = false
        this.favoriteToggle.emit(this.routine);
      });
    }
  }

    iniciarRutina()
    {
      this.router.navigate(['iniciarRutina'], {
        state: {rutina: this.routine}
      });
    }

}
