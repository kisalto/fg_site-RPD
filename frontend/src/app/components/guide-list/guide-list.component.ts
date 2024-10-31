import { Component, inject } from '@angular/core';
import { Guide } from '../../model/guide';
import { GuideService } from '../../service/guide.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-guide-list',
  standalone: true,
  imports: [],
  templateUrl: './guide-list.component.html',
  styleUrl: './guide-list.component.scss'
})
export class GuideListComponent {
  lista: Guide[] = [];

  guideService =  inject (GuideService);
  router = inject(Router);

  constructor(){

  }

  findAll(){
    this.guideService.findAll().subscribe({
      next: lista => { 
        this.lista = lista;
      },
      error: erro => { 
        Swal.fire({
          title: 'Ocorreu um erro',
          icon: 'error',
          confirmButtonText: 'Ok',
        });
      }
    });
  
  }
  
  edit(id: number): void {
    this.router.navigate([`admin/guide/${id}`]);
  }
  
  deletar(delGuide: Guide) {
    Swal.fire({
      title: 'Tem certeza?',
      text: `Deseja deletar o guia ${delGuide.titulo}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sim, inativar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.guideService.inativar(delGuide.id).subscribe({
          next: () => {
            Swal.fire({
              title: 'Inativado!',
              text: `O eleitor ${delGuide.titulo} foi inativado com sucesso.`,
              icon: 'success',
              confirmButtonText: 'Ok'
            });
            this.findAll(); // Atualiza a lista após inativar
          },
          error: () => {
            Swal.fire({
              title: 'Erro',
              text: 'Não foi possível inativar o eleitor.',
              icon: 'error',
              confirmButtonText: 'Ok'
            });
          }
        });
      }
    });
  }

}
