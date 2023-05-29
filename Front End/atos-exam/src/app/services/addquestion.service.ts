import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AddquestionService {

  constructor(private http:HttpClient) { }


  addQuestion(questionsData:any):Observable<any>
  {
    return this.http.post(`http://localhost:8080/questions/add`,questionsData)
  }


}
