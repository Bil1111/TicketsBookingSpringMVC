import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AppComponent } from './app.component';
import { RegestComponent } from './regest/regest.component';
import { SingINComponent } from './sing-in/sing-in.component';
import { SharedService } from './shared.service';
import { routes } from './app.routes';

@NgModule({
  declarations: [
    AppComponent,
    RegestComponent,
    SingINComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
