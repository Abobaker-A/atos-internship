import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DeleteExamDefinitionService {
  constructor(private http:HttpClient) { }
  deleteAnswer(id: string):Observable<any>
  {
    return this.http.delete(`http://localhost:8082/exams/definitions/${id}`)
}
}
