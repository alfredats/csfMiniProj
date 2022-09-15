import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom, Subject, tap } from 'rxjs';
import { Contact, ResponseDetails } from '../model';

@Injectable()
export class ContactService {
  private API_URI = 'https://immense-tor-55820.herokuapp.com/contacts';

  onNewContact = new Subject<Contact[]>();

  constructor(private http: HttpClient) {}

  addContact(contact: Contact): Promise<Contact[]> {
    const ENDPOINT = this.API_URI + '/add';
    return firstValueFrom(this.http.post(ENDPOINT, contact))
      .then((x) => {
        console.log('within promise: ', x);
        return this.listContacts();
      })
      .catch((err) => {
        console.log('>>> error:', err);
        return [];
      });
  }

  removeContact(contact: Contact): Promise<Contact[]> {
    const ENDPOINT = this.API_URI + '/remove';
    return firstValueFrom(this.http.post(ENDPOINT, contact))
      .then((x) => {
        console.log('within promise: ', x);
        return this.listContacts();
      })
      .catch((err) => {
        console.log('>>> error: ', err);
        return [];
      });
  }

  listContacts(): Promise<Contact[]> {
    const ENDPOINT = this.API_URI + '/get';
    return firstValueFrom(this.http.get(ENDPOINT))
      .then((resObj) => {
        const resp = resObj as ResponseDetails;
        const respData = resp.data as Contact[];
        this.onNewContact.next(respData);
        return respData;
      })
      .catch((errResp) => {
        console.error(errResp);
        return [];
      });
  }
}
