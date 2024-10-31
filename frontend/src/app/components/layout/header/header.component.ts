import { Component, ViewChild } from '@angular/core';
import { MdbDropdownDirective, MdbDropdownModule } from 'mdb-angular-ui-kit/dropdown';
import { MdbRippleModule } from 'mdb-angular-ui-kit/ripple';
import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [MdbDropdownModule, MdbRippleModule, MdbCollapseModule, RouterOutlet],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  @ViewChild('dropdown') dropdown!: MdbDropdownDirective;

  openDropdown() {
    this.dropdown.show();
  }

  closeDropdown() {
    this.dropdown.hide();
  }

}
