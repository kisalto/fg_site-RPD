import { Component } from '@angular/core';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { EventsFormComponent } from '../../Events/events-form/events-form.component';
import { RouterOutlet } from '@angular/router';
import { EventsListComponent } from "../../Events/events-list/events-list.component";
@Component({
  selector: 'app-community',
  standalone: true,
  imports: [MdbFormsModule, EventsFormComponent, RouterOutlet, EventsListComponent],
  templateUrl: './community.component.html',
  styleUrl: './community.component.scss'
})
export class CommunityComponent {

}
