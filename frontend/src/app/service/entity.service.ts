import { HttpClient } from "@angular/common/http";
import { baseURL } from "../constants/baseUrl";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { EntitySaveRequest } from "../request/EntitySaveRequest";
import { EntityUpdateRequest } from "../request/EntityUpdateRequest";

@Injectable({providedIn:'root'})
export class EntityService {

  constructor(private http: HttpClient) {}


  getAll(pageNum: number, pageSize: number): Observable<any> {
    return this.http.get(baseURL+'/entities?pageNum='+pageNum+'&pageSize='+pageSize)
  }

  findById(id: number): Observable<any> {
    return this.http.get(baseURL+'/entities/'+ id )
  }

  searchEntity(searchText: string): Observable<any> {
    return this.http.get(baseURL+'/entities/search/'+ searchText )
  }
  public save(requestbody: EntitySaveRequest): Observable<any> {
    return this.http.post<any>(baseURL+'/entities', requestbody);
  }

  public update(id: number, requestbody: EntityUpdateRequest): Observable<any> {
    return this.http.put<any>(baseURL+'/entities/'+id, requestbody);
  }

  public delete(id: number): Observable<any> {
    return this.http.post<any>(baseURL+'/entities/delete/'+id,null);
  }
  

  }