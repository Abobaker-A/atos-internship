import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GetallExamInstancesService {
  constructor(private http:HttpClient ) { }

  GetAllExamInstancesService():Observable<any>
  {
    return this.http.get(`http://localhost:8082/exams/instances`)
  }

}
