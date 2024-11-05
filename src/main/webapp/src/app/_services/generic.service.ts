import {EventEmitter, Injectable} from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class GenericService {
  private resize: EventEmitter<any> = new EventEmitter<any>();
  constructor() { }

  getResize() {
    return this.resize;
  }
  emitResize() {
    this.resize.emit();
  }
}
