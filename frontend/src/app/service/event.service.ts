import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  http = inject(HttpClient)

  API = "http://localhost:8080/api/rdp/event"

  constructor() { }

  findAll(): Observable<Event[]>{

    return this.http.get<Event[]>(this.API+"/findAll")
  }
}
