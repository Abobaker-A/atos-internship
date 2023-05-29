import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { AssignExamService } from './../services/assign-exam.service';


@Component({
  selector: 'app-assign-exam-form',
  templateUrl: './assign-exam-form.component.html',
  styleUrls: ['./assign-exam-form.component.css']
})
export class AssignExamFormComponent implements OnInit {
  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private _AssignExamService:AssignExamService,
  ) {}
  ngOnInit(): void {
  }
   examdefinitionid:string = this.route.snapshot.params['id'];
  assignForm:FormGroup = this.fb.group({
    examdefinitionid:this.route.snapshot.params['id'],
    useremail: ['', Validators.required],
    scheduledTimeFrom: [null],
    scheduledTimeTo: [null],
    createdby: ['', Validators.required]
  });


  assignExam() {
    const body = this.assignForm.value;
    console.log(body);
  this._AssignExamService.AssignExam(body).subscribe(()=>{
    })

  }



}
