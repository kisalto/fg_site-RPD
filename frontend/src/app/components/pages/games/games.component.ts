import { Component } from '@angular/core';
import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';

@Component({
  selector: 'app-games',
  standalone: true,
  imports: [MdbCollapseModule],
  templateUrl: './games.component.html',
  styleUrl: './games.component.scss'
})
export class GamesComponent {

  sidebarOpen = false;

  toggleSidebar() {
      this.sidebarOpen = !this.sidebarOpen;
      console.log('Sidebar aberta?', this.sidebarOpen);

  }
}
