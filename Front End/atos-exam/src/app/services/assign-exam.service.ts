import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AssignExamService {

  constructor(private http: HttpClient) { }

  AssignExam(assignData:any) {
    return this.http.post(`http://localhost:8082/exams/assignments`,assignData );
  }
}

