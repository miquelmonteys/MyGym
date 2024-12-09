import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RutinaModel} from "../models/rutina.model";
import {Observable} from "rxjs";



@Injectable({
  providedIn: 'root'
})

export class UserService {

  urlProduct: string = 'api/users'
  idUserMONGO: string = '6755ea79d616277df3efe13f'

  constructor(private http: HttpClient) { }

  getMyFavourites() : Observable<Object>{
    return this.http.get(`${this.urlProduct}/${this.idUserMONGO}/myFavouriteProducts`)
  }
}
