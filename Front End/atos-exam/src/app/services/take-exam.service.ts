import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class TakeExamService {

  constructor(private http:HttpClient ) { }

  takeExam(id:string):Observable<any>
  {
    return this.http.get(`http://localhost:8082/exams/instances/${id}`)
  }
  submitExam(id:string ,questionDto:any):Observable<any>
  {
    return this.http.post(`http://localhost:8082/exams/instances/${id}/submission`,questionDto)
  }


}
