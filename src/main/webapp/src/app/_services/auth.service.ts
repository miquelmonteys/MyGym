import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {RoleModel} from "../models/role.model";

const AUTH_API = 'api/auth/';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) {
  }

  login(username: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      username,
      password
    }, httpOptions);
  }

  register(username: string, email: string, password: string, roles: any): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      username,
      email,
      password,
      roles
    }, httpOptions);
  }

  registerForDistributor(username: string, email: string, password: string, roles: any, dist_id : any): Observable<any> {
    return this.http.post(AUTH_API + 'registerUserForDist', {
      username,
      email,
      password,
      roles,
      dist_id
    }, httpOptions);
  }


  updateRoles(id: number, roles: RoleModel[]): Observable<any> {
    return this.http.put(AUTH_API + 'changeRoles', {
      id,
      roles
    }, httpOptions);
  }

  refreshToken(authReq: any) {
    return this.http.get(AUTH_API + 'refreshtoken', authReq);
  }
}
