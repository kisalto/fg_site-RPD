import { Routes } from '@angular/router';
import { MainComponent } from './components/layout/main/main.component';
import { GamesListComponent } from './components/pages/games/games-list/games-list.component';
import { GamesFormsComponent } from './components/pages/games/games-forms/games-forms.component';
import { GamesProfileComponent } from './components/pages/games/games-profile/games-profile.component';
import { DashboardComponent } from './components/layout/dashboard/dashboard.component';

export const routes: Routes = [
    {path: "", redirectTo:"main", pathMatch:"full"},
    {path: "main", component: MainComponent, children:[
        {path: "dashboard", component: DashboardComponent},
        {path: "games", component: GamesListComponent},
        {path: "games/cadastro", component: GamesFormsComponent},
        {path: "games/edit/:id", component: GamesFormsComponent},
        {path: "games/:sigla", component: GamesProfileComponent},

    ]}
]