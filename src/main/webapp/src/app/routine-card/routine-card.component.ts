import {Component, EventEmitter, Input, Output} from '@angular/core';
import {RutinaModel} from "../models/rutina.model";
import {RutinaSimpleModel} from "../models/rutinaSimple.model";

@Component({
  selector: 'app-routine-card',
  templateUrl: './routine-card.component.html',
  styleUrl: './routine-card.component.scss'
})
export class RoutineCardComponent {
  @Input() routine: RutinaSimpleModel;
  @Output() favoriteToggle = new EventEmitter<void>();

  toggleFavorite() {
    console.log(this.routine)
    this.favoriteToggle.emit();
  }
}
