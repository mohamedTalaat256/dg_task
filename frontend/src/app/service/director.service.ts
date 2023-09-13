import { HttpClient } from "@angular/common/http";
import { baseURL } from "../constants/baseUrl";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { EntitySaveRequest } from "../request/EntitySaveRequest";
import { EntityUpdateRequest } from "../request/EntityUpdateRequest";

@Injectable({providedIn:'root'})
export class DirectorService {

  constructor(private http: HttpClient) {}


  getAll( ): Observable<any> {
    return this.http.get(baseURL+'/persons');
  }

  findById(id: number): Observable<any> {
    return this.http.get(baseURL+'/persons/'+ id )
  }

  searchEntity(searchText: string): Observable<any> {
    return this.http.get(baseURL+'/persons/search/'+ searchText )
  }
  public save(requestbody: EntitySaveRequest): Observable<any> {
    return this.http.post<any>(baseURL+'/persons', requestbody);
  }

  public update(id: number, requestbody: EntityUpdateRequest): Observable<any> {
    return this.http.put<any>(baseURL+'/persons/'+id, requestbody);
  }

  public delete(id: number): Observable<any> {
    return this.http.post<any>(baseURL+'/persons/delete/'+id,null);
  }
  

  }