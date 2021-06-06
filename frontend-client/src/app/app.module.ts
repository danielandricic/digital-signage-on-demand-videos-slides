import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material-module';
import { HeaderComponent } from './core/header/header.component';
import {ApiService} from './core/services/api.service';
import {HttpClientModule} from '@angular/common/http';
import { DisplayComponent } from './display/display.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    DisplayComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule
  ],
  providers: [ApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
