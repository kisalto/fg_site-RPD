import { Routes } from '@angular/router';
import { FighterComponent } from './components/layout/fighter/fighter.component';
import { FighterListComponent } from './components/fighter/fighter-list/fighter-list.component';

export const routes: Routes = [
    { path: '', redirectTo: 'fighter', pathMatch: 'full' },
    { path: 'fighter', component: FighterComponent },
    { path: 'fighter-list', component: FighterListComponent },
];
