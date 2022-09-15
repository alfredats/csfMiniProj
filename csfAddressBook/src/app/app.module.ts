import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ContactService } from './services/contact.service';
import { AddFormComponent } from './components/add-form.component';
import { ContactListComponent } from './components/contact-list.component';

@NgModule({
  declarations: [AppComponent, AddFormComponent, ContactListComponent],
  imports: [BrowserModule, HttpClientModule, FormsModule, ReactiveFormsModule],
  providers: [ContactService],
  bootstrap: [AppComponent],
})
export class AppModule {}
