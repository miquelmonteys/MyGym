import {Component, EventEmitter, Input, Output} from '@angular/core';
import {RutinaModel} from "../models/rutina.model";
import {RutinaSimpleModel} from "../models/rutinaSimple.model";
import {UserService} from "../_services/user.service";

@Component({
  selector: 'app-routine-card',
  templateUrl: './routine-card.component.html',
  styleUrl: './routine-card.component.scss'
})
export class RoutineCardComponent {
  @Input() routine: RutinaSimpleModel;
  @Output() favoriteToggle : EventEmitter<RutinaSimpleModel> = new EventEmitter();


  constructor(private userService : UserService) {
  }

  toggleFavorite() {
    console.log(this.routine)
    this.favoriteToggle.emit();
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
}
