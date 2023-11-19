import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {ImportComponent} from "./import/import.component";

const routes: Routes = [
  {path: "home", component: HomeComponent},
  {path: "import", component: ImportComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }