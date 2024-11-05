import {EventEmitter, Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {UserModel} from '../models/user.model';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {RoleModel} from "../models/role.model";


const TOKEN_KEY = 'auth-token';
const LANGUAGE_KEY = 'language';
const USER_KEY = 'auth-user';
const USER_API = 'api/user/';


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {


  usuariEvent: EventEmitter<UserModel> = new EventEmitter();

  url : string;

  constructor(private http: HttpClient) { }



  // updateRolesTo()



  private getCookie(name: string) {
    let ca: Array<string> = document.cookie.split(';');
    let caLen: number = ca.length;
    let cookieName = `${name}=`;
    let c: string;

    for (let i: number = 0; i < caLen; i += 1) {
      c = ca[i].replace(/^\s+/g, '');
      if (c.indexOf(cookieName) == 0) {
        return c.substring(cookieName.length, c.length);
      }
    }
    return '';
  }

  private deleteCookie(name) {
    this.setCookie(name, '', -1);
  }

  private setCookie(name: string, value: string, expireDays: number, path: string = '') {
    let d:Date = new Date();
    d.setTime(d.getTime() + expireDays * 24 * 60 * 60 * 1000);
    let expires:string = `expires=${d.toUTCString()}`;
    let cpath:string = path ? `; path=${path}` : '';
    document.cookie = `${name}=${value}; ${expires}${cpath}`;
  }


  signOut(): void {
    this.deleteCookie(TOKEN_KEY);
    this.deleteCookie(USER_KEY);
    window.sessionStorage.clear();
  }

  public saveToken(token: string): void {
    this.deleteCookie(TOKEN_KEY);
    this.setCookie(TOKEN_KEY, token, 1);
    // window.sessionStorage.removeItem(TOKEN_KEY);
    // window.sessionStorage.setItem(TOKEN_KEY, token);
  }


  public getToken(): string | null {
    return this.getCookie(TOKEN_KEY);
    // return window.sessionStorage.getItem(TOKEN_KEY);
  }

  public getLanguage(): any | null {
    try {
      return JSON.parse(this.getCookie(LANGUAGE_KEY))
    } catch (ex) {
      return null;
    }
  }
  public setLanguage(language: string): void {
    this.deleteCookie(LANGUAGE_KEY);
    this.setCookie(LANGUAGE_KEY, JSON.stringify(language), 3650);
  }
  public saveUser(user: any): void {
    this.deleteCookie(USER_KEY);
    this.setCookie(USER_KEY, JSON.stringify(user), 1);
    this.usuariEvent.emit(user);
  }
  public getUserUpdate() {
    return this.usuariEvent;
  }
  public getUser(): any {
    const user = this.getCookie(USER_KEY);
    // const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }
    return null;
  }

  // en token-storage.service.ts:

  public modifyRoles(newRoles : RoleModel[]){
    const userStringified = this.getCookie(USER_KEY);
    let user : UserModel = JSON.parse(userStringified);
    user.roles = newRoles;
    this.deleteCookie(USER_KEY);
    this.setCookie(USER_KEY, JSON.stringify(user), 1);
    this.usuariEvent.emit(user);
  }
  public setMapView(mapView){
    localStorage.setItem("map", JSON.stringify(mapView));
  }
  public getMapView(){
    if (localStorage.getItem("map")) {
      return JSON.parse(localStorage.getItem("map"));
    } else
      return null;
  }

  canPlaceOrders() : boolean {
    var me = this.getUser();
    var roles = me.roles;
    // @ts-ignore
    return (roles.find(role => (role === "ROLE_DISTRIBUTOR") || (role === "ROLE_PURCHASE")))
  }

  canPlaceMarketingOrders() : boolean {
    var me = this.getUser();
    var roles = me.roles;
    // @ts-ignore
    return (roles.find(role => (role === "ROLE_DISTRIBUTOR") || (role === "ROLE_PURCHASE") || (role === "ROLE_STPG_COMMERCIAL")))
    // return (roles.find(role => (role === "ROLE_DISTRIBUTOR") || (role === "ROLE_MARKETING") || (role === "ROLE_PURCHASE") || (role === "ROLE_STPG_COMMERCIAL")))
  }

  isExport() : boolean {
  var me = this.getUser();
  var roles = me.roles;
  // @ts-ignore
  return (roles.find(role => (role === "ROLE_STPG_EXPORT")))
}

  temporaryRegCanDelete;
  canDeleteFiles() : boolean {
    var me = this.getUser();
    var roles = me.roles;
    this.temporaryRegCanDelete = this.getUser().roles.find(role => role === "ROLE_STPG_REGULATORY");
    // @ts-ignore
    return (roles.find(role => (role === "ROLE_STPG_REGULATORY_MASTER") || (role === "ROLE_STPG_MARKETING") || this.temporaryRegCanDelete))
  }

  setUrl(url : string){
    this.url = url;
  }

  getUrl(){
    return this.url;
  }


  generateNewPassword(myUsername : string){
    return this.http.post(USER_API + 'restore/requestNewPassword', {
      username : myUsername
    }, httpOptions);
  }
  getUserDataFromToken(token : string){
    return this.http.get(USER_API + 'restore/getUserRestorePassword/' + token);
  }

  setNewPasswordTo(token : string, newPassword : string){
    return this.http.post(USER_API + 'restore/setNewPassword/' + token, {
      newPassword : newPassword
    }, httpOptions);

  }
}
