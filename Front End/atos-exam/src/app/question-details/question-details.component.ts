import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ViewQuestionDetailsService } from './../services/view-question-details.service';
import { QuestionToDispolay } from './../Modals/question-to-dispolay';
import { DeleteQuestionsService } from './../services/delete-questions.service';
import { DeleteAnswerService } from './../services/delete-answer.service';
import { FormGroup, FormControl,FormBuilder, Validators } from '@angular/forms';
import { NgxSpinnerService } from 'ngx-spinner';
import { AddAnswerService } from './../services/add-answer.service';


@Component({
  selector: 'app-question-details',
  templateUrl: './question-details.component.html',
  styleUrls: ['./question-details.component.css']
})
export class QuestionDetailsComponent implements OnInit  {
  questionId:String=this._ActivatedRoute.snapshot.params['id'];
  questiondetails:QuestionToDispolay|null=null;
  constructor(private _ViewQuestionDetailsService:ViewQuestionDetailsService,
              private _ActivatedRoute:ActivatedRoute,
              private _DeleteQuestionsService:DeleteQuestionsService,
              private _Router:Router,
              private _DeleteAnswerService:DeleteAnswerService,
              private spinner:NgxSpinnerService,
              private _AddAnswerService:AddAnswerService,
              private _FormBuilder: FormBuilder,
    ){}


viewQustionDetails(id:string){
  this._ViewQuestionDetailsService.viewQustionDetails(id).subscribe((data)=>{
    console.log(data);

    this.questiondetails = data;
  })
}

  ngOnInit(): void {
    this.questionId=this._ActivatedRoute.snapshot.params['id'];
    this.viewQustionDetails(this._ActivatedRoute.snapshot.params['id']);
  }
  deleteQuestion(id: string|undefined){
    this._DeleteQuestionsService.deleteQuestions(id).subscribe(()=>{
      this._Router.navigateByUrl("/dispalyq")
    })
  }
  deleteAnswer(id: string|undefined){
    this._DeleteAnswerService.deleteAnswer(id).subscribe(()=>{
      this.viewQustionDetails(this._ActivatedRoute.snapshot.params['id']);
    })
  }
  addAnswers: FormGroup = this._FormBuilder.group({
    name: ['', Validators.required],
    isTrue: ['', Validators.required]
  });

  addAnswer(){
    console.log(this.addAnswers.value);
    if(this.addAnswers.invalid){
      return;
    }
    this.spinner.show();
    const questionId = this._ActivatedRoute.snapshot.params['id'];
    this._AddAnswerService.addAnswer({...this.addAnswers.value, questionId}).subscribe(()=>{
      this.viewQustionDetails(questionId);
      this.spinner.hide();
    }, error => {
      this.spinner.hide();
    });
    this.addAnswers.reset();
  }

}



