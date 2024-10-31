import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

import { GamesComponent } from './components/pages/games/games.component';
import { MainComponent } from './components/layout/main/main.component';
import { FighterComponent } from './components/layout/fighter/fighter.component';
import { FighterListComponent } from './components/fighter/fighter-list/fighter-list.component';
import { CommunityComponent } from './components/layout/community/community.component';
import { EventsFormComponent } from './components/Events/events-form/events-form.component';
import { EventsListComponent } from './components/Events/events-list/events-list.component';

export const routes: Routes = [
  { path: '', redirectTo: 'main', pathMatch: 'full' },
  {
    path: 'main',
    component: MainComponent,
    children: [
      { path: 'games', component: GamesComponent },
      { path: 'fighter', component: FighterComponent },
      { path: 'fighter-list', component: FighterListComponent },
      {
        path: 'community',
        component: CommunityComponent,
        children: [
          { path: 'event-form', component: EventsFormComponent },
          {path: "event-form/edit/:id", component: EventsFormComponent},
          { path: 'event-list', component: EventsListComponent },
        ],
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
