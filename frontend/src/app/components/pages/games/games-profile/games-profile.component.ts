import { Component, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';
import { GameService } from '../../../../service/game.service';
import { Game } from '../../../../model/game';

@Component({
  selector: 'app-games-profile',
  standalone: true,
  imports: [MdbCollapseModule],
  templateUrl: './games-profile.component.html',
  styleUrl: './games-profile.component.scss'
})
export class GamesProfileComponent {

  rotaAtual = inject(ActivatedRoute);
  gameService = inject(GameService);

  game: Game = new Game(0,'','','','',null);

  constructor() {
    let sigla = this.rotaAtual.snapshot.params['sigla']
    this.findBySigla(sigla);
  }

  findBySigla(sigla: string) {
    this.gameService.findBySigla(sigla).subscribe({
      next: char => {
        this.game = char;
      },
      error: erro => {
        alert('Jogo Não Encontrado');      }
    })
  }

}
