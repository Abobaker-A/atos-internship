import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddrolesComponent } from './addroles/addroles.component';
import { AllrolesComponent } from './allroles/allroles.component';
import { AllusersComponent } from './allusers/allusers.component';
import { AssignExamFormComponent } from './assign-exam-form/assign-exam-form.component';
import { DispalyquestionsComponent } from './dispalyquestions/dispalyquestions.component';
import { ErrorpageComponent } from './errorpage/errorpage.component';
import { ExamInstancesComponent } from './exam-instances/exam-instances.component';
import { ExamSummaryDetailsComponent } from './exam-summary-details/exam-summary-details.component';
import { ExamSummaryListComponent } from './exam-summary-list/exam-summary-list.component';
import { ExamComponent } from './exam/exam.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { QuestionDetailsComponent } from './question-details/question-details.component';
import { QuestionsComponent } from './questions/questions.component';
import { RegisterComponent } from './register/register.component';
import { RolesComponent } from './roles/add-roles.component';
import { StartQuizComponent } from './start-quiz/start-quiz.component';
import { UserDetailsComponent } from './user-details/user-details.component';

const routes: Routes = [
  {path:"",redirectTo:'questions',pathMatch:"full"},
  {path:"home",component:HomeComponent},
  {path:"questions",component:QuestionsComponent},
  {path:"login",component:LoginComponent},
  {path:"register",component:RegisterComponent},
  {path:"dispalyq",component:DispalyquestionsComponent},
  {path:"startquiz",redirectTo:'startquiz/examinstances',pathMatch:"full"},
  {path:"startquiz",component:StartQuizComponent,children:[
    {path:"exam/:id",component:ExamComponent},
    {path:"examinstances",component:ExamInstancesComponent},
  ]},
  {path:"userdetails/:id",component:UserDetailsComponent},
  {path:"questiondetails/:id",component:QuestionDetailsComponent},
  {path:"assignexam/:id",component:AssignExamFormComponent},
  {path:"examsummary/:id",component:ExamSummaryListComponent},
  {path:"examsummarydetails/:id",component:ExamSummaryDetailsComponent},
  {path:"roles",redirectTo:'roles/allroles',pathMatch:"full"},
  { path: "roles", component: RolesComponent, children: [
    { path: "addrole", component: AddrolesComponent },
    { path: "allroles", component: AllrolesComponent },
    { path: "allusers", component: AllusersComponent },

  ] },
  {path:"**",component:ErrorpageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
exports: [RouterModule]
})
export class AppRoutingModule { }
