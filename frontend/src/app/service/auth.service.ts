import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from '../model/login.model';
import { Register } from '../model/register.model';
import { baseURL } from '../constants/baseUrl';

@Injectable({providedIn:'root'})
export class AuthService {


  constructor(private http: HttpClient) {}


  register(register: Register): Observable<any> {
    const headers = { 'content-type': 'application/json'}
    const body=JSON.stringify(register);
    return this.http.post(baseURL+'/auth/register' ,body,{'headers':headers})
  }




  login(login:Login): Observable<any> {
    const headers = { 'content-type': 'application/json'}
    const body=JSON.stringify(login);
    return this.http.post(baseURL+'/auth/login' ,body,{'headers':headers})
  }


  isLogedIn(){
    return localStorage.getItem('accessToken') != null;
  }


  GetToken(){
    return localStorage.getItem('accessToken')||'';
   }
   HaveAccess(){
     var loggintoken=localStorage.getItem('accessToken')||'';
     var _extractedtoken=loggintoken.split('.')[1];
     var _atobdata=atob(_extractedtoken);
     var _finaldata=JSON.parse(_atobdata);
     if(_finaldata.role=='admin'){
       return true
     }else{
       alert('you not having access');
       return false
     }
   }


}
