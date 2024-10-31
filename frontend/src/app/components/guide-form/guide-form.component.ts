import { Component, inject } from '@angular/core';
import { Guide } from '../../model/guide';
import { Router } from '@angular/router';
import { GuideService } from '../../service/guide.service';
import Swal from 'sweetalert2'
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-guide-form',
  standalone: true,
  imports: [MdbFormsModule, FormsModule],
  templateUrl: './guide-form.component.html',
  styleUrl: './guide-form.component.scss'
})
export class GuideFormComponent {
  guide: Guide = new Guide('','','','');

  router = inject(Router);

  guideService = inject(GuideService);

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
