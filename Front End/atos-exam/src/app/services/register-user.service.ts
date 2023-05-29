import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { RegisterUser } from './../Modals/register-user';


@Injectable({
  providedIn: 'root'
})
export class RegisterUserService {

  constructor(private http:HttpClient) { }

  registerUser(userInfo:RegisterUser):Observable<any>
  {
    return this.http.post(`http://localhost:8081/auth/register`,userInfo)
  }

}
