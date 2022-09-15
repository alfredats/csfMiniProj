import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Contact } from '../model';
import { ContactService } from '../services/contact.service';

@Component({
  selector: 'app-contact-list',
  templateUrl: './contact-list.component.html',
  styleUrls: ['./contact-list.component.css'],
})
export class ContactListComponent implements OnInit, OnDestroy {
  contacts!: Contact[];
  sub$!: Subscription;

  constructor(private cSvc: ContactService) {}

  ngOnDestroy(): void {
    this.sub$.unsubscribe();
  }

  ngOnInit(): void {
    this.sub$ = this.cSvc.onNewContact.subscribe((contacts) => {
      this.contacts = contacts;
    });
    this.cSvc.listContacts();
  }

  removeContact(idx: number) {
    this.cSvc.removeContact(this.contacts[idx]);
  }
}
