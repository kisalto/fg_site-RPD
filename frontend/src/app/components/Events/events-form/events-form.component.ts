import { Component, inject } from '@angular/core';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { FormsModule } from '@angular/forms';

import { Evento } from '../../../model/evento';
import { EventService } from '../../../service/event.service';
@Component({
  selector: 'app-events-form',
  standalone: true,
  imports: [MdbFormsModule, FormsModule],
  templateUrl: './events-form.component.html',
  styleUrl: './events-form.component.scss'
})
export class EventsFormComponent {

  lista: Evento[] = [];
  eventService = inject(EventService);
}
