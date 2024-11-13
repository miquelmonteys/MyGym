import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-routine-card',
  templateUrl: './routine-card.component.html',
  styleUrl: './routine-card.component.scss'
})
export class RoutineCardComponent {
  @Input() routine: { name: string; image: string; isFavorite: boolean };
  @Output() favoriteToggle = new EventEmitter<void>();

  toggleFavorite() {
    this.favoriteToggle.emit();
  }
}
