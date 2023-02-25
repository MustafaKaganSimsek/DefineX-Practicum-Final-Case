import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CreateCreditComponent } from './create-credit/create-credit.component';
import { FindCreditComponent } from './find-credit/find-credit.component';
import { UpdateCreditComponent } from './update-credit/update-credit.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CreateCreditComponent,
    FindCreditComponent,
    UpdateCreditComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
