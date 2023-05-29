import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RolesDisplay } from './../Modals/RolesDisplay';
@Injectable({
  providedIn: 'root'
})
export class DispalyrolesService {

  constructor(private http:HttpClient ) { }

  getRoles():Observable<any>
  {
    return this.http.get("http://localhost:8081/roles")
  }
}
