import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RutinaModel} from "../models/rutina.model";



@Injectable({
  providedIn: 'root'
})

export class HistoricService {

  urlRutines: string = 'api/historics'

  constructor(private http: HttpClient) { }

  postRutina(historic: any){
    return this.http.post(`${this.urlRutines}`, historic);
  }
}
