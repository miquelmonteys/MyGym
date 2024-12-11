import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ExerciciService {

  urlExercici: string = 'api/exercicis'

  constructor(private http: HttpClient) { }

  getExercici(id: string){
    return this.http.get(`${this.urlExercici}/${id}`);
  }

  getAllExercicis(){
    return this.http.get(`${this.urlExercici}`);
  }
}
