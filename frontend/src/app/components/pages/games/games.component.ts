import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-games',
  standalone: true,
  imports: [CommonModule],
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
