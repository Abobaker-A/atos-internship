import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AddAnswerService {

  constructor(private http:HttpClient) { }


  addAnswer(answerData:any):Observable<any>
  {
    return this.http.post(`http://localhost:8080/answers/addanswer`,answerData)
  }
}
