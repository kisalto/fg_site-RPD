import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FighterService } from '../../../service/fighter.service';
import Swal from 'sweetalert2';
import { Fighter } from '../../../model/fighter';
import { User } from '../../../model/user';

@Component({
  selector: 'app-fighter-list',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './fighter-list.component.html',
  styleUrl: './fighter-list.component.scss'
})
export class FighterListComponent {

  user: User = new User();

  router = inject(Router);
  fighterService = inject(FighterService)

  game: string = 'BBCF';

  constructor() {
    this.user.isMod = true;
    this.findAllFighters()
  }

  characters: Fighter[] = [];

  goToFighter(name: string) {
    this.router.navigate(['main', "fighter", name])
  }

  findAllFighters() {
    this.fighterService.findByGameNome(this.game).subscribe({
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
