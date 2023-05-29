import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AddDefinitionService {

  constructor(private http:HttpClient) { }


  addDefinition(definitionData:any):Observable<any>
  {
    return this.http.post(`http://localhost:8082/exams/definitions`,definitionData)
  }

}
