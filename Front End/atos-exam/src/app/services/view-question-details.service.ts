import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ViewQuestionDetailsService {

  constructor(private http:HttpClient ) { }

  viewQustionDetails(id:String):Observable<any>
  {
    return this.http.get(`http://localhost:8080/questions/${id}`)
  }
}
