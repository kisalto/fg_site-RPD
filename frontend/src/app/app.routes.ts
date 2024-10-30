import { Routes } from '@angular/router';
import { GamesComponent } from './components/pages/games/games.component';
import { MainComponent } from './components/layout/main/main.component';

export const routes: Routes = [
    {path: "", redirectTo:"main", pathMatch:"full"},
    {path: "main", component: MainComponent, children:[
        {path: "games", component: GamesComponent}

    ]}
];
