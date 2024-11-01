import { Component, inject, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { Fighter } from '../../../model/fighter';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FighterService } from '../../../service/fighter.service';
import Swal from 'sweetalert2';
import { GameService } from '../../../service/game.service';

@Component({
  selector: 'app-fighter-form',
  standalone: true,
  imports: [MdbFormsModule, FormsModule, RouterLink],
  templateUrl: './fighter-form.component.html',
  styleUrl: './fighter-form.component.scss'
})
export class FighterFormComponent {

  tituloComponente = "Criar Novo Personagem"

  fighter: Fighter = new Fighter();

  router = inject(Router)
  gameService = inject(GameService)
  activatedRoute = inject(ActivatedRoute)
  fighterService = inject(FighterService)

  name!: string
  sigla!: string;

  constructor() {
    let name = this.activatedRoute.snapshot.params['name']
    this.sigla = this.activatedRoute.snapshot.params['sigla']
    if (name != null) {
      this.tituloComponente = "Editar Personagem"
      this.findByNome(name)
    }
  }

  findByNome(nome: string) {
    this.fighterService.findByNome(nome).subscribe({
      next: char => {
        this.fighter = char;
      },
      error: erro => {
        console.log(erro.error)
      }
    })
  }

  editar() {
    this.fighterService.editar(this.fighter).subscribe({
      next: msg => {
        Swal.fire({
          title: msg,
          icon: "success"
        }).then(() => {
          this.router.navigate(['main', 'games', this.sigla]);
        });
      },
      error: erro => {
        Swal.fire({
          title: erro.error,
          icon: "error"
        }).then(() => {
          this.router.navigate(['main', 'games', this.sigla]);
        });
      }
    })
  }

  salvar() {
    this.gameService.findBySigla(this.sigla).subscribe({
      next: game => {
        this.fighter.game = game;
      },
      error: erro => {
        console.log(erro.error)
      }

    });

    this.fighterService.salvar(this.fighter).subscribe({
      next: msg => {
        Swal.fire({
          title: msg,
          icon: "success"
        }).then(() => {
          this.router.navigate(['main', 'games', this.sigla]);
        });
      },
      error: erro => {
        Swal.fire({
          title: erro.error,
          icon: "error"
        })
      }
    })
  }

}
