import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Game } from '../model/game';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  http = inject(HttpClient)

  API = "http://localhost:8080/api/rdp/game"

  constructor() { }

  save(game: Game): Observable<Game>{
    return this.http.post<Game>(this.API+"/save",game, { responseType: 'text' as 'json' });
  }

  findAllGames(): Observable<Game[]>  {
    return this.http.get<Game[]>(this.API+"/findAll");
  }

  findById(id: number): Observable<Game>{
    return this.http.get<Game>(this.API+"//findById/"+id);
  }
  
  update(game: Game): Observable<Game>{
    return this.http.put<Game>(this.API+"/update/"+game.id, game, {responseType: 'text' as 'json'});
  }

  delete(id: number): Observable<string>{
    return this.http.delete<string>(this.API+"/delete/"+id, {responseType: 'text' as 'json'});
  }

  findByNome(nome: string): Observable<Game[]>  {
    return this.http.get<Game[]>(this.API+"/findByNome/"+nome);
  }
  findBySigla(sigla: string): Observable<Game>{
    return this.http.get<Game>(this.API+"/findBySigla/"+sigla);
  }
}
