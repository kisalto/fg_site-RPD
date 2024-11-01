import { Component, inject } from '@angular/core';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../../model/user';
import { UserService } from '../../service/user.service';
import Swal from 'sweetalert2';
import { isContext } from 'vm';

@Component({
  selector: 'app-user-form',
  standalone: true,
  imports: [MdbFormsModule, FormsModule],
  templateUrl: './user-form.component.html',
  styleUrl: './user-form.component.scss',
})
export class UserFormComponent {
  dataAtual = new Date();
  ano = this.dataAtual.getFullYear(); 
  mes = this.dataAtual.getMonth() + 1; 
  dia = this.dataAtual.getDate(); 
  dataFormatada = this.dataAtual.toLocaleDateString(); 

  router = inject(Router);
  user: User = new User('', '', '', '', '');
  userService = inject(UserService);

  save() {
    this.user.isMod = true;
    this.user.isVet = true; 
    this.user.data_reg = this.dataFormatada;
    this.userService.save(this.user).subscribe({
      next: (mensagem) => {
        Swal.fire({
          title: 'Guia criado com sucesso!',
          icon: 'success',
        }).then(() => {
          this.router.navigate(['login']);
        });
      },
      error: (erro) => {
        console.error(erro);
        alert(erro.error);
        console.log(this.user);
      },
    });
  }

  delete() {
    this.userService.delete(this.user.id).subscribe({
      next: (mensagem) => {
        Swal.fire({
          title: mensagem,
          icon: 'success',
        });
      },
      error: (erro) => {
        console.error(erro);
        alert(erro.error);
        console.log(this.user);
      },
    });
  }

  update() {
    this.userService.update(this.user).subscribe({
      next: (mensagem) => {
        Swal.fire({
          title: mensagem,
          icon: 'success',
        });
      },
      error: (erro) => {
        console.error(erro);
        alert(erro.error);
        console.log(this.user);
      },
    });
  }
}
