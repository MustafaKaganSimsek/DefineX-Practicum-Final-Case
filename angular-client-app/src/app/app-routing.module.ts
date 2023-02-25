import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule,Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CreateCreditComponent } from './create-credit/create-credit.component';
import { FindCreditComponent } from './find-credit/find-credit.component';
import { UpdateCreditComponent } from './update-credit/update-credit.component';

const routes: Routes =[
  {path: 'credit', component:HomeComponent,
    children:[
      {path:'create',component:CreateCreditComponent},
      {path:'find',component:FindCreditComponent},
      {path:'update',component:UpdateCreditComponent}
    ]
  },
  { path: '**', redirectTo: 'credit/create', pathMatch: 'full' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports:[RouterModule]
})
export class AppRoutingModule { }
