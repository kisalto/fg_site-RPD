import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Fighter } from '../model/fighter';

@Injectable({
  providedIn: 'root'
})
export class FighterService {

  http = inject(HttpClient)

  API = "http://localhost:8080/api/rdp/fighter"

  constructor() { }

  findByGameNome(nome: string): Observable<Fighter[]> {
    return this.http.get<Fighter[]>(this.API + "/findByNomeJogo/" + nome)
  }

  findByNome(nome: string): Observable<Fighter> {
    return this.http.get<Fighter>(this.API + "/findByNome/" + nome)
  }

  editar(fighter: Fighter) {
    return this.http.put<string>(this.API + "update/" + fighter.id, fighter, { responseType: 'text' as 'json' })
  }

  salvar(fighter: Fighter) {
    return this.http.post<string>(this.API + "/save", fighter, { responseType: 'text' as 'json' })
  }

  delete(id: number) {
    return this.http.delete<string>(this.API+"/delete/"+id, { responseType: 'text' as 'json' })
  }

}
