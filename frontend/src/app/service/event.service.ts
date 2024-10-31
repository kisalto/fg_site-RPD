import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Evento } from '../model/evento';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  http = inject(HttpClient)

  API = "http://localhost:8080/api/rdp/event"

  constructor() { }

  findAll(): Observable<Evento[]>{

    return this.http.get<Evento[]>(this.API+"/findAll")
  }

  delete(id:number): Observable<string>{
    return this.http.delete<string>(this.API+"/delete/"+id, {responseType: 'text' as 'json'});
  }

  save(evento: Evento): Observable<string>{
    return this.http.post<string>(this.API+"/save", evento, {responseType: 'text' as 'json'});
  }

  update(evento: Evento, id:number): Observable<string>{
    return this.http.put<string>(this.API+"/update/"+id, evento, {responseType: 'text' as 'json'});
  }

  findById(id:number): Observable<Evento>{
    return this.http.get<Evento>(this.API+"/findById/"+id);
  }
}

