import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import {APP_BASE_HREF} from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { DocumentEditorAllModule } from '@syncfusion/ej2-angular-documenteditor';










@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,HttpClientModule,
    DocumentEditorAllModule,


  ],
  providers: [{provide: APP_BASE_HREF, useValue: '/'}],
  bootstrap: [AppComponent]
})
export class AppModule { }
