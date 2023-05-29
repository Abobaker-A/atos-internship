import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GetNotificationsService {

  constructor(private http:HttpClient ) { }

  getNotifications(id:String):Observable<any>
  {
    return this.http.get(`http://localhost:8083/notifications/${id}`)
  }
}
