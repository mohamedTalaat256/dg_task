import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from '../service/auth.service';

@Injectable()
export class tokenInterceptor implements HttpInterceptor {

  constructor(private inject:Injector) {}


  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {


    //auto login
    let authservice=this.inject.get(AuthService);


    let jwtToken = request.clone({
      setHeaders: {
        Authorization: 'bearer '+authservice.GetToken()
      }
    });
    return next.handle(jwtToken);
  }
}
