import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {ImportComponent} from "./import/import.component";
import {IssueListComponent} from "./issues/issue-list/issue-list.component";
import {AcceptComponent} from "./challanges/accept/accept.component";
import {RejectComponent} from "./challanges/reject/reject.component";
import {ChallengeListComponent} from "./challanges/challenge-list/challenge-list.component";
import {SigninComponent} from "./auth/signin/signin.component";
import {GithubCallbackComponent} from "./auth/github-callback/github-callback.component";
import {PermissionService} from "./_services/permission.service";

const routes: Routes = [
  {path: "home", component: HomeComponent},
  {path: "import", component: ImportComponent},
  {path: "repository/:id/issues", component: IssueListComponent},
  {path: "challenge/:id/accept", component: AcceptComponent},
  {path: "challenge/:id/reject", component: RejectComponent},
  {path: "challenges", component: ChallengeListComponent},
  {path: "signin", component: SigninComponent},
  {path: "auth/github/callback", component: GithubCallbackComponent}
];

// @ts-ignore
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
