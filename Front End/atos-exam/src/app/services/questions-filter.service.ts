import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class QuestionsFilterService {

  constructor(private http:HttpClient ) { }

  getQuestionsfilter(url:string):Observable<any>
  {
    return this.http.get(`http://localhost:8080/questions/${url}`)
  }
}
