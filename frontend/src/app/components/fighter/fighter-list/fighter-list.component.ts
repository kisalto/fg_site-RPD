import { Component, inject, Input } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FighterService } from '../../../service/fighter.service';
import Swal from 'sweetalert2';
import { Fighter } from '../../../model/fighter';
import { User } from '../../../model/user';
import { GameService } from '../../../service/game.service';
import { Game } from '../../../model/game';

@Component({
  selector: 'app-fighter-list',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './fighter-list.component.html',
  styleUrl: './fighter-list.component.scss'
})
export class FighterListComponent {

  @Input() sigla!: string;

  user: User = new User();

  router = inject(Router);
  activatedRoute = inject(ActivatedRoute)
  fighterService = inject(FighterService)
  gameService = inject(GameService)
  
  game: Game = new Game();

  gSigla = 'BBCF'
  cNome = 'Kokonoe'

  constructor() {
    this.user.isMod = true;
    let bigroute = this.activatedRoute.snapshot.params['sigla']
    this.findBySigla(bigroute);
    this.findAllFighters()
  }

  characters: Fighter[] = [];

  goToFighter(name: string) {
    this.router.navigate(['main', "fighter", name])
  }

  findBySigla(algo:string) {
    this.gameService.findBySigla(algo).subscribe({
      next: game => {
        this.game = game;
      },
      error: erro => {
        console.log(erro.error)
      }
    })
  }

  findAllFighters() {
    this.fighterService.findByGameNome(this.game.nome).subscribe({
      next: char => {
        this.characters = char;
      },
      error: erro => {
        console.log(erro.error)
      }
    })
  }

  deleteCharacter(delCharacter: Fighter) {
    Swal.fire({
      title: "Tem certeza que deseja deletar o personagem: " + delCharacter.nome + "?",
      showCancelButton: true,
      confirmButtonText: "Sim",
      cancelButtonText: `Cancelar`
    }).then((result) => {
      if (result.isConfirmed) {
        this.fighterService.delete(delCharacter.id).subscribe({
          next: mensagem => {
            Swal.fire(mensagem, "", "success");
            this.findAllFighters();
          },
          error: erro => {
            alert(erro.error);
          }
        });
      }
    });
  }

}
