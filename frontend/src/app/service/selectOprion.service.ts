import { HttpClient } from "@angular/common/http";
import { baseURL } from "../constants/baseUrl";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { AuthService } from "./auth.service";

@Injectable({providedIn:'root'})
export class SelectOprionService {

  constructor(private http: HttpClient, private authservice: AuthService) {}


  public getComunicationTypes(): Observable<any> {
    return this.http.get(baseURL+'/select_options/Communication_types' )
  }

  public getGenders(): Observable<any> {
    return this.http.get(baseURL+'/select_options/genders' )
  }
  
  public getCountriesCodes(): Observable<any> {
    return this.http.get(baseURL+'/select_options/countries_codes' )
  }

  public getContactType(): Observable<any> {
    return this.http.get(baseURL+'/select_options/contact_types' )
  }
  
  }