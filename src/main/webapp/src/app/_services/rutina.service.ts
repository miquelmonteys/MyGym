import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RutinaModel} from "../models/rutina.model";
import {Observable} from "rxjs";



@Injectable({
  providedIn: 'root'
})

export class RutinaService {

  urlRutines: string = 'api/rutines'

  constructor(private http: HttpClient) { }

  getDefaultRutines() {
    return this.http.get(`${this.urlRutines}/default`);
  }
}
