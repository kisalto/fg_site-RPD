import { Routes } from '@angular/router';
import { MainComponent } from './components/layout/main/main.component';
// import { FighterComponent } from './components/layout/fighter/fighter.component';
// import { FighterListComponent } from './components/fighter/fighter-list/fighter-list.component';
import { GamesListComponent } from './components/pages/games/games-list/games-list.component';

export const routes: Routes = [
    {path: "", redirectTo:"main", pathMatch:"full"},
    {path: "main", component: MainComponent, children:[
        {path: "games", component: GamesListComponent},
        {path: "games/:name", component: GamesListComponent},
        // { path: 'fighter', component: FighterComponent },
        // { path: 'fighter-list', component: FighterListComponent },

    ]}
]