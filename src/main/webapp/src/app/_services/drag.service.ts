import {EventEmitter, Injectable} from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class DragService {
  private beingDragged: EventEmitter<any> = new EventEmitter<any>();
  constructor() { }

  drag(d : boolean) {
    this.beingDragged.emit(d);
  }
 getDrag() {
    return this.beingDragged;
  }

}
