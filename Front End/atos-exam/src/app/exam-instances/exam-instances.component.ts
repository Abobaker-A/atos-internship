import { Component, OnInit } from '@angular/core';
import { GetallExamInstancesService } from './../services/getall-exam-instances.service';

@Component({
  selector: 'app-exam-instances',
  templateUrl: './exam-instances.component.html',
  styleUrls: ['./exam-instances.component.css']
})
export class ExamInstancesComponent implements OnInit{
  constructor(
    private _GetallExamInstancesService:GetallExamInstancesService
  ) { }
  ngOnInit(): void {
    this.getAllExamInstancesService();
  }
  examInstances :any[]=[];
  getAllExamInstancesService(){
    this._GetallExamInstancesService.GetAllExamInstancesService().subscribe((data)=>{
      this.examInstances=data;
    })
  }
}
