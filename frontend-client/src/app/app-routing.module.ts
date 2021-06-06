import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DisplayComponent} from './display/display.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: '/displays'},
  {path: 'displays', component: DisplayComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
