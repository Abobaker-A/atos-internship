import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TakeExamService } from './../services/take-exam.service';
import { QuestionToDispolay } from './../Modals/question-to-dispolay';
import { ExamDefinitions } from './../Modals/examDefinitions';
import { QuestionDto } from './../Modals/question-dto';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-exam',
  templateUrl: './exam.component.html',
  styleUrls: ['./exam.component.css'],
})
export class ExamComponent implements OnInit {
  constructor(
    private _ActivatedRoute: ActivatedRoute,
    private _TakeExamService: TakeExamService,
    private _Router: Router,
    private spinner:NgxSpinnerService
  ) {}

  examDuration!: number;
  examTimerId: any;
  questionTimerId: any;
  examTimeRemaining!: number;
  ExamInstanceId = '';
  ExamData: any[] = [];
  QuestionsView: QuestionToDispolay[] = [];
  currentQuestionIndex = 0;
  currentQuestion: QuestionToDispolay | undefined;
  questionDuration: number | undefined;
  questionTimeRemaining = 0;
  showSubmitButton = false;
  questionDisplayTime = new Date();
  questionAnswerTime = new Date();
  selectedAnswerId: string = '';
  isSubmitClicked = false;
  examDefinitionName:string='';
  lastCheckedAnswerId: string="";



  takeExam(id: string) {
    this.spinner.show();
    this._TakeExamService.takeExam(id).subscribe((data) => {
      console.log(id);
      console.log(data);
      this.currentQuestionIndex = 0;
      this.examDuration = data.duration;
      this.examDefinitionName=data.examDefinitionName
      this.examTimeRemaining = data.duration * 60;
      this.ExamData = data;
      this.QuestionsView = data.questionsViews;
      this.currentQuestion = this.QuestionsView[this.currentQuestionIndex];
      this.questionDuration = this.currentQuestion?.expectedTime ?? 0;
      this.questionTimeRemaining = this.questionDuration;
      this.startExamTimer();
      this.startQuestionTimer();
    });
    setTimeout(() => {
      this.spinner.hide();
    }, 250);
  }

  ngOnInit(): void {
    this.ExamInstanceId = this._ActivatedRoute.snapshot.params['id'];
    this.takeExam(this.ExamInstanceId);
  }

  startExamTimer() {
    this.examTimerId = setInterval(() => {
      this.examTimeRemaining--;
      if (this.examTimeRemaining <= 0) {
        this.submitExam();
        clearInterval(this.examTimerId);
      }
    }, 1000);
  }

  startQuestionTimer() {
    this.questionTimerId = setInterval(() => {
      this.questionTimeRemaining--;
      if (this.questionTimeRemaining <= 0) {
        clearInterval(this.questionTimerId);
        this.goToNextQuestion();
      }
    }, 1000);
  }
  formatTime(timeInSeconds: number): string {
    const minutes = Math.floor(timeInSeconds / 60);
    const seconds = timeInSeconds % 60;
    return `${minutes.toString().padStart(2, '0')}:${seconds
      .toString()
      .padStart(2, '0')}`;
  }
  goToNextQuestion() {
    clearInterval(this.questionTimerId);
    if (this.currentQuestion) {
      this.currentQuestion.answerTime = new Date();
    }

    this.currentQuestionIndex++;
    if (this.currentQuestionIndex < this.QuestionsView.length) {
      this.currentQuestion = this.QuestionsView[this.currentQuestionIndex];
      this.currentQuestion.displayTime = new Date();
      this.questionDuration = this.currentQuestion?.expectedTime ?? 0;
      this.questionTimeRemaining = this.questionDuration;
      this.selectedAnswerId = '';
      this.startQuestionTimer();
    } else {
      this.showSubmitButton = true;
      this.submitExam();
      clearInterval(this.questionTimerId);
    }
  }
  onRadioClick(answer: any) {
    this.QuestionsView[this.currentQuestionIndex].answers.forEach(a => a.isSelected = false);
    answer.isSelected = true;
  }




  submitExam() {
    this.spinner.show();

    clearInterval(this.questionTimerId);

    let questionDtos: QuestionDto[] = [];

    questionDtos = this.QuestionsView.map((question) => {
      const questionDto: QuestionDto = {
        questionId: question.id,
        selectedAnswerId: question.answers
          ?.filter((answer) => answer.isSelected)
          .map((a) => a.id),
        displayTime: question.displayTime || new Date(),
        answerTime: question.answerTime || new Date(),
      };
      return questionDto;
    });
    console.log(questionDtos);
    console.log(this.ExamInstanceId);
    this._TakeExamService
      .submitExam(this.ExamInstanceId, questionDtos)
      .subscribe((result) => {
        console.log(result);
        this.isSubmitClicked = true;
        setTimeout(() => {
          this.spinner.hide();
        }, 250);
          this._Router.navigate(['/home']);
      });
  }

  isLastQuestion(): boolean {
    return this.currentQuestionIndex === this.QuestionsView.length - 1;
  }
}
