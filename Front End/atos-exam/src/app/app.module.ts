import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { QuestionsComponent } from './questions/questions.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ErrorpageComponent } from './errorpage/errorpage.component';
import { DispalyquestionsComponent } from './dispalyquestions/dispalyquestions.component';
import { LoadingpageComponent } from './loadingpage/loadingpage.component';
import { HttpClientModule } from '@angular/common/http';
import { StartQuizComponent } from './start-quiz/start-quiz.component';
import { FooterComponent } from './footer/footer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgxSpinnerModule } from 'ngx-spinner';
import { NgxPaginationModule } from 'ngx-pagination';
import { ModalModule } from 'ngx-bootstrap/modal';
import { AddrolesComponent } from './addroles/addroles.component';
import { AllrolesComponent } from './allroles/allroles.component';
import { RolesComponent } from './roles/add-roles.component';
import { AllusersComponent } from './allusers/allusers.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { QuestionDetailsComponent } from './question-details/question-details.component';
import { AssignExamFormComponent } from './assign-exam-form/assign-exam-form.component';
import { ExamComponent } from './exam/exam.component';
import { ExamInstancesComponent } from './exam-instances/exam-instances.component';
import { ShufflePipe } from './Pipes/shuffle.pipe';
import { ExamSummaryListComponent } from './exam-summary-list/exam-summary-list.component';
import { OrderByPipe } from './Pipes/order-by.pipe';
import { ExamSummaryDetailsComponent } from './exam-summary-details/exam-summary-details.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    QuestionsComponent,
    LoginComponent,
    RegisterComponent,
    ErrorpageComponent,
    DispalyquestionsComponent,
    LoadingpageComponent,
    StartQuizComponent,
    FooterComponent,
    RolesComponent,
    AddrolesComponent,
    AllrolesComponent,
    AllusersComponent,
    UserDetailsComponent,
    QuestionDetailsComponent,
    AssignExamFormComponent,
    ExamComponent,
    ExamInstancesComponent,
    ShufflePipe,
    ExamSummaryListComponent,
    OrderByPipe,
    ExamSummaryDetailsComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    NgxSpinnerModule,
    NgxPaginationModule,
    FormsModule,
    ModalModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
