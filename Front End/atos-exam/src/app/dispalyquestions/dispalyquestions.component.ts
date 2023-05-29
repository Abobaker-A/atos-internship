import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from "ngx-spinner";
import { QuestionToDispolay } from '../Modals/question-to-dispolay';
import { FormGroup, FormBuilder } from '@angular/forms';
import { QuestionsFilterService } from './../services/questions-filter.service';
import { AddDefinitionService } from './../services/add-definition.service';
import { DispalyExamDefinitionsService } from './../services/dispaly-exam-definitions.service';
import { ExamDefinitions } from './../Modals/examDefinitions';
import { DeleteExamDefinitionService } from '../services/delete-exam-definition.service';





@Component({
  selector: 'app-dispalyquestions',
  templateUrl: './dispalyquestions.component.html',
  styleUrls: ['./dispalyquestions.component.css']
})


export class DispalyquestionsComponent implements OnInit  {

  page = 1;


  constructor(private spinner:NgxSpinnerService ,
              private formBuilder:FormBuilder,
              private _QuestionsFilterService:QuestionsFilterService,
              private _AddDefinitionService:AddDefinitionService,
              private _DispalyExamDefinitionsService:DispalyExamDefinitionsService,
              private _DeleteExamDefinitionService:DeleteExamDefinitionService,
    ){
  }
  pages:number[]=[];
  questions:QuestionToDispolay[]=[];
  idOfQuestion:string="1";

  filterForm = this.formBuilder.group({
    levelId: [''],
    categoryId: ['']
  });

  getQuestionsfilter(pageNumber:number): void {
    this.page = pageNumber ;
    pageNumber=pageNumber-1
    this.spinner.show();
    const levelId = this.filterForm.value.levelId;
    const categoryId = this.filterForm.value.categoryId;
    let url = 'list/filter';
    if (levelId && categoryId) {
      url += `?levelid=${levelId}&categoryid=${categoryId}&page=${pageNumber}`;
    } else if (levelId) {
      url += `?levelid=${levelId}&page=${pageNumber}`;
    } else if (categoryId) {
      url += `?categoryid=${categoryId}&page=${pageNumber}`;
    } else {
      url += `?page=${pageNumber}`;
    }
    console.log(url);
    this._QuestionsFilterService.getQuestionsfilter(url).subscribe((data)=>{
      if(data){
        this.questions=data.content;
        console.log(data);
        this.pages = new Array(data.totalPages).fill("").map((x,i)=>i+1);
      }
      setTimeout(() => {
        this.spinner.hide();
      }, 250);
    });
  }


  ngOnInit():void{
    this.getQuestionsfilter(1);
    this.getExamDefinitions();

  }


  public questionIds: string[] = [];

  public toggleSelection(questionId: string): void {
    const index = this.questionIds.indexOf(questionId);
    if (index >= 0) {
      this.questionIds.splice(index, 1);
    } else {
      this.questionIds.push(questionId);
    }
  }

  public isSelected(questionId: string): boolean {
    return this.questionIds.includes(questionId);
  }

 definitionForm: FormGroup = this.formBuilder.group({
  name: '',
  passingScore: '',
  duration: '',
  questionIds: [],
});


adddefinition() {
  this.spinner.show();
    const formValue = this.definitionForm.value;
    const definition = {
      name: formValue.name,
      passingScore: formValue.passingScore,
      duration : formValue.duration,
      questionIds: this.questionIds,
    };
    console.log('Definition:', definition);
    this._AddDefinitionService.addDefinition(definition).subscribe(()=>{
      this.getQuestionsfilter(1);
      this.questionIds=[];
      this.getExamDefinitions();
    });
    this.definitionForm.reset();
    setTimeout(() => {
      this.spinner.hide();
    }, 250);
  }
  examDefinitions:ExamDefinitions[]=[];
  getExamDefinitions() {
    this.spinner.show();
    this._DispalyExamDefinitionsService.getExamDefinitions().subscribe((data)=>{
      if(data){
        this.examDefinitions=data;
        console.log(data);
      }
      setTimeout(() => {
        this.spinner.hide();
      }, 250);
    });
  }
  deleteExamDefinition(id:string){
    this._DeleteExamDefinitionService.deleteAnswer(id).subscribe(()=>{
      this.getExamDefinitions();
    })
  }

}




