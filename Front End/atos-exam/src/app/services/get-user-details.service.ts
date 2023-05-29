import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class GetUserDetailsService {

  constructor(private http:HttpClient ) { }

  getUserDetails(id:String):Observable<any>
  {
    return this.http.get(`http://localhost:8081/users/${id}`)
  }
}
