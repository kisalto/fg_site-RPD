import { Component, inject } from '@angular/core';
import { Game } from '../../../../model/game';
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { GameService } from '../../../../service/game.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-games-forms',
  standalone: true,
  imports: [MdbFormsModule, FormsModule],
  templateUrl: './games-forms.component.html',
  styleUrl: './games-forms.component.scss'
})
export class GamesFormsComponent {

  tituloComponente: string = "Novo Jogo";

  game: Game = new Game(0,'','','','',null);

  router = inject(Router);
  rotaAtual = inject(ActivatedRoute);

  gameService = inject(GameService);

  constructor(){
    let id = this.rotaAtual.snapshot.params['id'];
    if (id > 0){

      this.tituloComponente = "Editar Jogo";
      this.findById(id);
    }
  }

  findById(id: number){
    this.gameService.findById(id).subscribe({
      next: jogo => {
        this.game = jogo;
      },
      error: erro =>{
        alert('Não Achou o ID');
      }
    })
  }

  save() {
    this.gameService.save(this.game).subscribe({
      next: resposta => {
        Swal.fire({
          title: 'Jogo cadastrado com sucesso!',
          icon: "success"
        }).then(() => {
          this.router.navigate(['main/games']);
        });
      },
      error: erro => {
        console.error(erro);
        alert(erro.error)
        Swal.fire( 'Erro', erro.error, 'error');
        }
      })
    };

  update(){
    this.gameService.save(this.game).subscribe({
      next: resposta => {
        Swal.fire({
          title: 'Jogo atualizado!',
          icon: "success"
        }).then(() => {
          this.router.navigate(['main/games']);
        });
      },
      error: erro => {
        Swal.fire({
          title: erro.error,
          icon: 'error'
        });
      }
    });
  }

}
