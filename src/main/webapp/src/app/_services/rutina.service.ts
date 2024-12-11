import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RutinaModel} from "../models/rutina.model";



@Injectable({
  providedIn: 'root'
})

export class RutinaService {

  urlRutines: string = 'api/rutines'

  constructor(private http: HttpClient) { }

  getDefaultRutines() {
    return this.http.get(`${this.urlRutines}/default`);
  }

  getPropiesRutines(){
    return this.http.get(`${this.urlRutines}/propies`);
  }

  getRutina(id: string){
    return this.http.get(`${this.urlRutines}/${id}`);
  }

  postRutina(rutina: RutinaModel){
    return this.http.post(`${this.urlRutines}`, rutina);
  }
}
