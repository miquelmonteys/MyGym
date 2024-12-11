import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RutinaModel} from "../models/rutina.model";
import {Observable} from "rxjs";



@Injectable({
  providedIn: 'root'
})

export class UserService {

  urlUsers: string = 'api/users'

  constructor(private http: HttpClient) { }

  getMyFavourites() : Observable<Object>{
    return this.http.get(`${this.urlUsers}/favorites`)
  }

  addFavourite(id : string) : Observable<Object>{
    return this.http.post(`${this.urlUsers}/favorites/${id}`,null)
  }

  deleteFavourite(id : string) : Observable<Object>{
    return this.http.delete(`${this.urlUsers}/favorites/${id}`)
  }

}
