import { Component, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Fighter } from '../../../model/fighter';
import { FighterService } from '../../../service/fighter.service';

@Component({
  selector: 'app-fighter',
  standalone: true,
  imports: [],
  templateUrl: './fighter.component.html',
  styleUrl: './fighter.component.scss'
})
export class FighterComponent {

  activatedRoute = inject(ActivatedRoute);
  fighterService = inject(FighterService)

  colors: string[] = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23'];
  character: Fighter = new Fighter();

  constructor() {
    let nome = this.activatedRoute.snapshot.params['name']
    this.findByNome(nome);
  }

  findByNome(nome: string) {
    this.fighterService.findByNome(nome).subscribe({
      next: char => {
        this.character = char;
      },
      error: erro => {
        alert(erro.error)
      }
    })
  }

}
