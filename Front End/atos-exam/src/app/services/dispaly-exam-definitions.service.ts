import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class DispalyExamDefinitionsService {
  constructor(private http:HttpClient ) { }

  getExamDefinitions():Observable<any>
  {
    return this.http.get("http://localhost:8082/exams/definitions")
  }
}
