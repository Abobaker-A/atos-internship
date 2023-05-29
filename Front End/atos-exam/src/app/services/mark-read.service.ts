import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class MarkReadService {
  constructor(private http:HttpClient ) { }

  markRead(id:string):Observable<any>
  {
    console.log(id + "read");

    return this.http.put(`http://localhost:8083/notifications/read`,id)
  }
}
