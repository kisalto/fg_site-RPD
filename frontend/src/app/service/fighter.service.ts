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
    return this.http.get<Fighter[]>(this.API+"/findByNomeJogo/"+nome)
  }

  findByNome(nome: string): Observable<Fighter> {
    return this.http.get<Fighter>(this.API+"/findByNome/"+nome)
  }
}
