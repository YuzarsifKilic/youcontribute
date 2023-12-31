import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { ImportComponent } from './import/import.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {RepositoryService} from "./services/repository.service";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ToastrModule} from "ngx-toastr";
import { IssueListComponent } from './issues/issue-list/issue-list.component';
import { AcceptComponent } from './challanges/accept/accept.component';
import { RejectComponent } from './challanges/reject/reject.component';
import { ChallengeListComponent } from './challanges/challenge-list/challenge-list.component';
import { StatusComponent } from './badges/status/status.component';
import { SigninComponent } from './auth/signin/signin.component';
import { GithubCallbackComponent } from './auth/github-callback/github-callback.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ImportComponent,
    IssueListComponent,
    AcceptComponent,
    RejectComponent,
    ChallengeListComponent,
    StatusComponent,
    SigninComponent,
    GithubCallbackComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
