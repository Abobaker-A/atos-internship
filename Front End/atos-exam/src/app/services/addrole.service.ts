import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AddroleService {

  constructor(private http:HttpClient) { }


  addRole(roleData:any):Observable<any>
  {
    return this.http.post(`http://localhost:8081/roles/add`,roleData)
  }
}
