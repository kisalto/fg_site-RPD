import { Component, inject, TemplateRef, ViewChild, viewChild } from '@angular/core';
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
    this.findAll();
  }

  findAll(){
    this.guideService.findAll().subscribe({
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
    this.router.navigate([`admin/guide/${id}`]);
  }
  
  deletar(guide: Guide) {
    Swal.fire({
      title: 'Tem certeza?',
      text: `Deseja deletar o guia ${guide.titulo}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sim, deletar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.guideService.delete(guide.id).subscribe({
          next: mensagem => {
            Swal.fire({
              title: mensagem,
              text: `O eleitor ${guide.titulo} foi deletado com sucesso.`,
              icon: 'success',
              confirmButtonText: 'Ok'
            });
            this.findAll(); // Atualiza a lista após deletar
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
