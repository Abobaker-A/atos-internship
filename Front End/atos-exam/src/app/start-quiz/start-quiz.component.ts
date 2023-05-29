import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable, interval, Subject } from 'rxjs';
import { map, takeUntil } from 'rxjs/operators';
import { ExamDefinitions } from '../Modals/examDefinitions';
import { QuestionToDispolay } from '../Modals/question-to-dispolay';
import { TakeExamService } from '../services/take-exam.service';
import { GetallExamInstancesService } from './../services/getall-exam-instances.service';



@Component({
  selector: 'app-start-quiz',
  templateUrl: './start-quiz.component.html',
  styleUrls: ['./start-quiz.component.css']
})
export class StartQuizComponent implements OnInit {
  examDefinitionDto: ExamDefinitions | undefined;
  questionsViews: any[]=[];
  currentQuestionIndex: number = 0;
  currentQuestion: QuestionToDispolay | undefined;
  timer: number|undefined;
  selectedAnswerId:string="";


  constructor(
    private _TakeExamService: TakeExamService,
    private route: ActivatedRoute,
    private _GetallExamInstancesService:GetallExamInstancesService
  ) { }


  takeExam(id:string){
    this._TakeExamService.takeExam(id).subscribe(exam => {
      console.log(exam);


      this.examDefinitionDto = exam.examDefinitionDto;
      this.questionsViews = exam.questionsViews;

      // shuffle the answers for each question
      this.questionsViews.forEach(question => {
        question.answers?.sort(() => Math.random() - 0.5);
        if(question.expectedTime){question.expectedTime *= 60;}
      });

      this.currentQuestion = this.questionsViews[0];
      this.timer = this.currentQuestion?.expectedTime;

      this.startTimer();
    });
  }

  ngOnInit(): void {
  }

  startTimer() {
    const interval = setInterval(() => {
      if(this.timer){
        this.timer--
        if (this.timer <= 0) {
          clearInterval(interval);
          this.submitExam();
        }
      };

    }, 1000);
  }

  submitExam() {
    // // submit the exam using the examService
    // this.examService.submitExam().subscribe(result => {
    //   console.log(result);
    // });
  }

  // selectAnswer(answerId: string) {
  //   if(this.currentQuestion){this.currentQuestion.selectedAnswerId = answerId;}
  // }

  nextQuestion() {
    // save the selected answer for the current question
  //   if(this.currentQuestion?.answers){
  //  const selectedAnswer = this.currentQuestion.answers.find(answer => answer.id === this.currentQuestion.selectedAnswerId);
  //   this.currentQuestion.selectedAnswer = selectedAnswer;
  //   }


    // go to the next question
    this.currentQuestionIndex++;
    if (this.currentQuestionIndex < this.questionsViews.length) {
      this.currentQuestion = this.questionsViews[this.currentQuestionIndex];
      this.timer = this.currentQuestion?.expectedTime;
    } else {
      this.submitExam();
    }
  }
  onAnswerSelected(answer: any) {
    this.selectedAnswerId = answer.id;
  }
}
