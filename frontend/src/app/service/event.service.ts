import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  http = inject(HttpClient)

  API = "http://localhost:8080/api/rdp/event"

  constructor() { }
}
