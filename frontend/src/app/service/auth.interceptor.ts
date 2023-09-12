import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AuthService } from "./auth.service";



@Injectable()
export class AuthInterceptor implements HttpInterceptor{
 
    
    constructor(private authService: AuthService){}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
     
        const accessToken = this.authService.GetToken();

        console.log(accessToken);

        if(accessToken != ''){
            const cloned = req.clone({
                setHeaders: {
                    Authorization: 'bearer '+accessToken,
                    'content-type': 'application/json'
                  }
              /*   headers: req.headers.set(" Authorization",  'bearer '+ accessToken) */
            });

            return next.handle(cloned);
        }else{
            return next.handle(req);
        }
    }
}