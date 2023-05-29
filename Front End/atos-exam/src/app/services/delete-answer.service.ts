import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DeleteAnswerService {

  constructor(private http:HttpClient) { }
  deleteAnswer(id: string|undefined):Observable<any>
  {
    return this.http.delete(`http://localhost:8080/answers/${id}`)
  }
}
