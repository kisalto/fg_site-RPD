import { Component, Inject, inject, Input, input, TemplateRef, ViewChild } from '@angular/core';
import { Guide } from '../../model/guide';
import { Router } from '@angular/router';
import { GuideService } from '../../service/guide.service';
import Swal from 'sweetalert2'
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { FormsModule } from '@angular/forms';
import { Fighter } from '../../model/fighter';
import { Game } from '../../model/game';
import { User } from '../../model/user';
import { MdbDropdownModule } from 'mdb-angular-ui-kit/dropdown';
import { MdbModalModule, MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { FighterListComponent } from '../fighter/fighter-list/fighter-list.component';



@Component({
  selector: 'app-guide-form',
  standalone: true,
  imports: [MdbFormsModule, FormsModule, MdbDropdownModule, MdbModalModule, FighterListComponent],
  templateUrl: './guide-form.component.html',
  styleUrl: './guide-form.component.scss'
})
export class GuideFormComponent {
  guide: Guide = new Guide('','','','',new Fighter,new Game, new User('','','','',''), new Date);
  router = inject(Router);

  guideService = inject(GuideService);

  fighters : Fighter [] = [];
  constructor(){
    
  }

  save(){
    this.guideService.save(this.guide).subscribe({
      next: mensagem => {
        Swal.fire({
          title: "Guia criado com sucesso!",
          icon: "success"
        }).then(() => {
          this.router.navigate(['guide-list']);
        });
      },
      error: erro => {
        console.error(erro);
        alert(erro.error)
        console.log(this.guide);
      }
    })
  }

  
}