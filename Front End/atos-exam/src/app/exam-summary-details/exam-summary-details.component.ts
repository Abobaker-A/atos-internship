import { Component, OnInit } from '@angular/core';
import { ExamResult } from '../Modals/exam-result';
import { GetExamSummaryDetailsService } from './../services/get-exam-summary-details.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-exam-summary-details',
  templateUrl: './exam-summary-details.component.html',
  styleUrls: ['./exam-summary-details.component.css']
})
export class ExamSummaryDetailsComponent implements OnInit {
  constructor(private _GetExamSummaryDetailsService:GetExamSummaryDetailsService,
              private _ActivatedRoute:ActivatedRoute,
    ){}

  examSummary:ExamResult | undefined;

getExamSummaryDetailsService(id:string){
  this._GetExamSummaryDetailsService.getExamSummaryDetails(id).subscribe((data)=>{
    this.examSummary=data;
  })
}

  ngOnInit(): void {
    this.getExamSummaryDetailsService(this._ActivatedRoute.snapshot.params['id'])
  }
}
