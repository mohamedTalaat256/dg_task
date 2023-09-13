import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from '../model/login.model';
import { Register } from '../model/register.model';
import { baseURL } from '../constants/baseUrl';
import { User } from '../model/user.model';

@Injectable({ providedIn: 'root' })
export class AuthService {


  constructor(private http: HttpClient) { }


  register(register: Register): Observable<any> {
    const headers = { 'content-type': 'application/json' }
    const body = JSON.stringify(register);
    return this.http.post(baseURL + '/auth/register', body, { 'headers': headers })
  }




  public login(login: Login): Observable<any> {
    const headers = { 'content-type': 'application/json' }
    const body = JSON.stringify(login);
    return this.http.post(baseURL + '/auth/login', body, { 'headers': headers })
  }


  isLogedIn() {
    return localStorage.getItem('accessToken') != null;
  }


  GetToken() {
    return localStorage.getItem('accessToken') || '';
  }

  GetRefreshToken() {
    return localStorage.getItem('refreshToken') || '';
  }



  getLogedIngUser(){
    const data = JSON.parse(localStorage.getItem('user'))

    return new User(
      data.id,
      data.email,
      data.username,
      data.fullName,
      data.roles

    );
  }
  
  
  public logOut(): Observable<any> {

    const headers = { 'content-type': 'application/json' }
    const refreshToken = this.GetRefreshToken();
    return this.http.post<any>(baseURL + '/auth/logout?refreshToken='+ refreshToken, null, { 'headers': headers });
  }


}
