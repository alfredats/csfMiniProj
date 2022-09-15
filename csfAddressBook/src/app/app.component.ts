import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'csfAddressBook';

  state = { activeTab: '' };

  showAdd() {
    this.state.activeTab='addForm';
  }

  showList() {
    this.state.activeTab='contactList';
  }
}
