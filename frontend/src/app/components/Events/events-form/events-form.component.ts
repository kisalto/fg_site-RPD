import { Component, inject } from '@angular/core';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';
import { Evento } from '../../../model/evento';
import { EventService } from '../../../service/event.service';
import { User } from '../../../model/user';
import { Game } from '../../../model/game';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-events-form',
  standalone: true,
  imports: [MdbFormsModule, FormsModule],
  templateUrl: './events-form.component.html',
  styleUrl: './events-form.component.scss',
})
export class EventsFormComponent {
  router = inject(Router);
  activeroutes = inject(ActivatedRoute);
  evento: Evento = new Evento();
  eventService = inject(EventService);
  user: User = new User();
  game: Game[] = [
    { id: 1, nome: 'teste', link: 'batata', preco: 122, descricao: 'teste' },
  ];

  constructor() {
    this.user.id = 1;
    this.user.apelido = 'Josh';
    this.user.senha = 'batata';
    this.user.email = 'batata@gmail.com';
    this.user.data_reg = '03/05/2005';
    this.user.isMod = true;
    this.user.isVet = true;
    let id = this.activeroutes.snapshot.params["id"];
    if(id){
      this.findById(id);
    }
  }

  save() {
    if (this.evento.id > 0) {
      this.eventService.update(this.evento, this.evento.id).subscribe({
        next: (mensagem) => {
          Swal.fire({
            title: mensagem,
            icon: 'success',
            confirmButtonText: 'Ok',
          });
          this.router.navigate(['main', 'community', 'events-list']);
        },
        error: (erro) => {
          Swal.fire({
            title: 'Ocorreu um erro: ' + erro.error,
            icon: 'error',
            confirmButtonText: 'Ok',
          });
        },
      });
    } else {
      this.evento.user = [this.user];
      this.evento.game = this.game;
      this.eventService.save(this.evento).subscribe({
        next: (mensagem) => {
          Swal.fire({
            title: mensagem,
            icon: 'success',
            confirmButtonText: 'Ok',
          });
            
        },
        error: (erro) => {
          Swal.fire({
            title: 'Ocorreu um erro: ' + erro.error,
            icon: 'error',
            confirmButtonText: 'Ok',
          });
        },
      });
    }
  }

  findById(id:number) {
    this.eventService.findById(id).subscribe({
      next: (retorno) => {
        this.evento = retorno;
      },
      error: (erro) => {
        Swal.fire({
          title: 'Ocorreu um erro: ' + erro.error,
          icon: 'error',
          confirmButtonText: 'Ok',
        });
      },
    });
  }
}
