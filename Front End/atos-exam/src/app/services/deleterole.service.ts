import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DeleteroleService {


  constructor(private http:HttpClient ) { }

  deleteRole(id:string):Observable<any>
  {
    return this.http.delete(`http://localhost:8081/roles/${id}`)
  }

}
