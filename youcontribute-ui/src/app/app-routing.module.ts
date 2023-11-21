import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {ImportComponent} from "./import/import.component";
import {IssueListComponent} from "./issues/issue-list/issue-list.component";

const routes: Routes = [
  {path: "home", component: HomeComponent},
  {path: "import", component: ImportComponent},
  {path: "repository/:id/issues", component: IssueListComponent},
  {path: "", redirectTo: "home", pathMatch: "full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
