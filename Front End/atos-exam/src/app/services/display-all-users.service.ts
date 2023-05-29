import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class DisplayAllUsersService {

  constructor(private http:HttpClient ) { }

  DisplayAllUsers(page:number):Observable<any>
  {
    return this.http.get(`http://localhost:8081/users/list?page=${page-1}`)
  }
}
