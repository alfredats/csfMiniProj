import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Contact } from '../model';
import { ContactService } from '../services/contact.service';

@Component({
  selector: 'app-add-form',
  templateUrl: './add-form.component.html',
  styleUrls: ['./add-form.component.css'],
})
export class AddFormComponent implements OnInit {
  form!: FormGroup;

  constructor(private fb: FormBuilder, private cSvc: ContactService) {
    this.form = this.makeForm();
  }

  ngOnInit(): void {}

  private makeForm() {
    return this.fb.group({
      name: this.fb.control('', [Validators.required, Validators.minLength(3)]),
      email: this.fb.control('', [Validators.required, Validators.email]),
      mobile: this.fb.control('', [
        Validators.required,
        Validators.pattern(/^([0-9]+)$/),
      ]),
    });
  }

  processForm() {
    console.log('>>> form submitted: ', this.form.value);
    this.cSvc.addContact(this.form.value as Contact);
    this.form = this.makeForm();
  }
}
