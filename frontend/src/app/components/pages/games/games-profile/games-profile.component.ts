import { Component } from '@angular/core';
import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';

@Component({
  selector: 'app-games-profile',
  standalone: true,
  imports: [MdbCollapseModule],
  templateUrl: './games-profile.component.html',
  styleUrl: './games-profile.component.scss'
})
export class GamesProfileComponent {

}
