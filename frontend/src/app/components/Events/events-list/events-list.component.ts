import { Component, inject, Inject } from '@angular/core';
import { Event } from '../../../model/event';
import { CommonModule } from '@angular/common';
import { EventService } from '../../../service/event.service';
@Component({
  selector: 'app-events-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './events-list.component.html',
  styleUrl: './events-list.component.scss'
})
export class EventsListComponent {
  lista:Event[] = [];
  eventService = inject(EventService);
  
  constructor(){
    
    this.findAll
    
    
    
  }

  findAll(){
    this.eventService.findAll().subscribe({
      next: lista => {
          lista = lista;
      },
      error: erro =>{
        alert("Ocorreu um erro")
      }
    });
  }
}
