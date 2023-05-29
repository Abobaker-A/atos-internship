import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DeleteQuestionsService {

  constructor(private http:HttpClient) { }
  deleteQuestions(id: string|undefined):Observable<any>
  {
    return this.http.delete(`http://localhost:8080/questions/${id}`)
  }
}
