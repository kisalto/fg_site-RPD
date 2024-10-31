import { Routes } from '@angular/router';
import { MainComponent } from './components/layout/main/main.component';
import { GamesListComponent } from './components/pages/games/games-list/games-list.component';
import { GamesFormsComponent } from './components/pages/games/games-forms/games-forms.component';
import { GamesProfileComponent } from './components/pages/games/games-profile/games-profile.component';
import { DashboardComponent } from './components/layout/dashboard/dashboard.component';
import { FighterComponent } from './components/layout/fighter/fighter.component';
import { FighterFormComponent } from './components/fighter/fighter-form/fighter-form.component';
import { FighterListComponent } from './components/fighter/fighter-list/fighter-list.component';


export const routes: Routes = [
    {path: "", redirectTo:"main", pathMatch:"full"},
    {path: "main", component: MainComponent, children:[
        { path: 'fighter/:name', component: FighterComponent },
        { path: 'fighter-form/new', component: FighterFormComponent },
        { path: 'fighter-form/edit/:name', component: FighterFormComponent },
        { path: 'fighter-list', component: FighterListComponent },
        {path: "dashboard", component: DashboardComponent},
        {path: "games", component: GamesListComponent},
        {path: "games/cadastro", component: GamesFormsComponent},
        {path: "games/edit/:id", component: GamesFormsComponent},
        {path: "games/:sigla", component: GamesProfileComponent},

    ]}
]