import { Component, OnInit } from '@angular/core';
import {Validators, FormArray, FormControl, FormGroup ,FormBuilder  } from '@angular/forms';
import { Router } from '@angular/router';
import { AddquestionService } from '../services/addquestion.service';
import { NgxSpinnerService } from 'ngx-spinner';





@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']

})



export class QuestionsComponent implements OnInit{
  constructor(private _Router:Router ,
    private spinner:NgxSpinnerService ,
    private _AddquestionService:AddquestionService,
    private _FormBuilder:FormBuilder
    ){}



  addQuestions = this._FormBuilder.group({
    name: [null, [Validators.required,Validators.maxLength(200)]],
    levelId: [null, [Validators.required,Validators.min(1),Validators.max(4)]],
    categoryId: [null, [Validators.required,Validators.min(1),Validators.max(4)]],
    mark: [null, [Validators.required,Validators.min(1),Validators.max(5)]],
    expectedTime: [null, [Validators.required,Validators.min(30),Validators.max(120)]],
    createdBy: [null, [Validators.required]],
    answers: this._FormBuilder.array([
      this.createAnswer(),
      this.createAnswer(),
      this.createAnswer(),
    ])
  });
  createAnswer(): FormGroup {
    return this._FormBuilder.group({
      name: ['', Validators.required],
      description: [false, Validators.required]
    });
  };
  get answersArray(): FormArray {
    return this.addQuestions.get('answers') as FormArray;
  }

  addAnswer() {
    this.answersArray.push(this._FormBuilder.group({
      name: '',
      description: ''
    }));
  }
  deleteAnswer() {
    this.answersArray.removeAt(this.answersArray.length - 1);
  }

  addQuestion(){
    if(this.addQuestions.invalid){
      return;
    }
    this.spinner.show();
    this._AddquestionService.addQuestion(this.addQuestions.value).subscribe((data)=>{
        this._Router.navigateByUrl('/dispalyq')
    });
    this.spinner.hide();
  }
  ngOnInit(): void {



  }



}
