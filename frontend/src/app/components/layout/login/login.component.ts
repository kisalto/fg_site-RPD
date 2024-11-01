import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import Swal from 'sweetalert2';
import { Login } from '../../../model/login';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule,MdbFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  login: Login = new Login();

  router = inject(Router);

  autenticar(){
    if(this.login.email == "admin@rdp.com" && this.login.senha == "123"){
      Swal.fire({
        title: "Bem vindo!",
        text: "Logado com sucesso!",
        icon: "success"
      });
      this.router.navigate(["/main/community/event-list"])
    }else{
      Swal.fire({
        icon: "error",
        text: "Dados incorretos"
      })
    }

  }


}
