import { Component, OnInit } from '@angular/core';
import { GetExamSummaryService } from './../services/get-exam-summary.service';
import { ActivatedRoute } from '@angular/router';
import { ExamResult } from './../Modals/exam-result';

@Component({
  selector: 'app-exam-summary-list',
  templateUrl: './exam-summary-list.component.html',
  styleUrls: ['./exam-summary-list.component.css']
})
export class ExamSummaryListComponent implements OnInit {
  constructor(
    private _GetExamSummaryService:GetExamSummaryService,
    private _ActivatedRoute:ActivatedRoute,

  ){}

  examSummaryList:ExamResult[]=[];
  page = 1;
  pages:number[]=[];

  getExamSummaryService(pageNumber:number){
    this.page = pageNumber;
    const id = this._ActivatedRoute.snapshot.params['id'];
    this._GetExamSummaryService.getExamSummary(pageNumber,id).subscribe((data)=>{
      console.log(data);
      this.examSummaryList=data.content;
      console.log(this.examSummaryList);
      this.pages = new Array(data.totalPages).fill("").map((x,i)=>i+1);
    })
  }

  ngOnInit(): void {
  this.getExamSummaryService(1);
  }

}
