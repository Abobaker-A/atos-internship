import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GetExamSummaryService {

  constructor(private http:HttpClient ) { }

  getExamSummary(page:number,id:string):Observable<any>
  {
    return this.http.get(`http://localhost:8081/examsummary/${id}?page=${page-1}`)
  }


}
