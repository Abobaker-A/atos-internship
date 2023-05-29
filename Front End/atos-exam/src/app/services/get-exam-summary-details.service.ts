import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GetExamSummaryDetailsService {

  constructor(private http:HttpClient ) { }

  getExamSummaryDetails(id:string):Observable<any>
  {
    return this.http.get(`http://localhost:8081/examsummary/by-id/${id}`)
  }


}
