import { Component, inject } from '@angular/core';
import { GameService } from '../../../../service/game.service';
import Swal from 'sweetalert2';
import { Game } from '../../../../model/game';
import { Router } from '@angular/router';

@Component({
  selector: 'app-games-list',
  standalone: true,
  imports: [],
  templateUrl: './games-list.component.html',
  styleUrl: './games-list.component.scss'
})
export class GamesListComponent {

  router = inject(Router);
  gameService = inject(GameService);

  constructor(){
    this.findAllGames();
  }

  games: Game[] = [];

  goToGame(sigla: string){
    this.router.navigate(['main',"games", sigla])
  }

  findAllGames(){
    this.gameService.findAllGames().subscribe({
      next: jogo => {
        this.games = jogo;
      },
      error: erro => {
        console.log(erro.error)
      }
    })
  }
}
