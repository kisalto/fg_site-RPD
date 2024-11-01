import { Component, inject } from '@angular/core';
import Swal from 'sweetalert2';
import { User } from '../../model/user';
import { UserService } from '../../service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.scss'
})
export class UserListComponent {
  lista: User[] = [];
  userService =  inject (UserService);
  router = inject(Router);

  constructor(){
    this.findAll();
  }

  findAll(){
    this.userService.findAll().subscribe({
      next: lista => { 
        this.lista = lista;
      },
      error: erro => { 
        Swal.fire({
          title: erro,
          icon: 'error',
          confirmButtonText: 'Ok',
        });
      }
    });
  
  }
  
  edit(id: number): void {
    this.router.navigate([`user/${id}`]);
  }
  
  deletar(user: User) {
    Swal.fire({
      title: 'Tem certeza?',
      text: `Deseja deletar o guia ${user.apelido}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sim, deletar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.userService.delete(user.id).subscribe({
          next: mensagem => {
            Swal.fire({
              title: mensagem,
              text: `O eleitor ${user.apelido} foi deletado com sucesso.`,
              icon: 'success',
              confirmButtonText: 'Ok'
            });
            this.findAll(); 
          },
          error: erro => {
            Swal.fire({
              title: "Erro ao Deletar",
              text: 'Não foi possível deletar o guia.',
              icon: 'error',
              confirmButtonText: 'Ok'
            });
          }
        });
      }
    });
  }

}
