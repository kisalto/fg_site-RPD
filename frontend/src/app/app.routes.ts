import { Routes } from '@angular/router';
import { GamesComponent } from './components/pages/games/games.component';
import { MainComponent } from './components/layout/main/main.component';
import { FighterComponent } from './components/layout/fighter/fighter.component';
import { FighterListComponent } from './components/fighter/fighter-list/fighter-list.component';
import { FighterFormComponent } from './components/fighter/fighter-form/fighter-form.component';

export const routes: Routes = [
    {path: "", redirectTo:"main", pathMatch:"full"},
    {path: "main", component: MainComponent, children:[
        { path: "games", component: GamesComponent },
        { path: 'fighter/:name', component: FighterComponent },
        { path: 'fighter-list', component: FighterListComponent },
        { path: 'fighter/new', component: FighterFormComponent },

    ]}
]