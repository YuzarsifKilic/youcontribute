import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {ImportComponent} from "./import/import.component";
import {IssueListComponent} from "./issues/issue-list/issue-list.component";
import {AcceptComponent} from "./challanges/accept/accept.component";
import {RejectComponent} from "./challanges/reject/reject.component";
import {ChallengeListComponent} from "./challanges/challenge-list/challenge-list.component";

const routes: Routes = [
  {path: "home", component: HomeComponent},
  {path: "import", component: ImportComponent},
  {path: "repository/:id/issues", component: IssueListComponent},
  {path: "challenge/:id/accept", component: AcceptComponent},
  {path: "challenge/:id/reject", component: RejectComponent},
  {path: "challenges", component: ChallengeListComponent},
  {path: "", redirectTo: "home", pathMatch: "full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
