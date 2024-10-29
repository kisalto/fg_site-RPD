import { Routes } from '@angular/router';
import { FighterComponent } from './components/layout/fighter/fighter.component';

export const routes: Routes = [
    { path: '', redirectTo: 'fighter', pathMatch: 'full' },
    { path: 'fighter', component: FighterComponent }
];
