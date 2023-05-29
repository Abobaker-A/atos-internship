import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AssignRolesToUserService {
  constructor(private http:HttpClient) { }


  assignRolesToUser( id:string,RolesIds:any ):Observable<any>
  {
    return this.http.put(`http://localhost:8081/users/${id}/roles`,RolesIds)
  }

}
