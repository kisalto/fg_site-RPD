import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { FighterService } from '../../../service/fighter.service';
import Swal from 'sweetalert2';
import { Fighter } from '../../../model/fighter';

@Component({
  selector: 'app-fighter-list',
  standalone: true,
  imports: [],
  templateUrl: './fighter-list.component.html',
  styleUrl: './fighter-list.component.scss'
})
export class FighterListComponent {

  router = inject(Router);
  fighterService = inject(FighterService)

  game: string = 'BBCF';

  constructor() {
    this.findAllFighters()
  }

  characters: Fighter[] = [];

  goToFighter(name: string){
    this.router.navigate(['main',"fighter", name])
  }

  findAllFighters() {
    this.fighterService.findByGameNome(this.game).subscribe({
      next: char => {
        this.characters = char;
      },
      error: erro=> {
        console.log(erro.error)
      }
    })
  }

}
