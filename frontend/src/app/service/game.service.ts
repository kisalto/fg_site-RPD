import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Game } from '../model/game';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  http = inject(HttpClient)

  API = "http://localhost:8080/api/rdp/game"

  constructor() { }

  findById(id: number): Observable<Game> {
    return this.http.get<Game>(this.API+"/findById/"+id)
  }
}
