import { Routes } from '@angular/router';
import { GuideFormComponent } from './components/guide-form/guide-form.component';
import { GuideListComponent } from './components/guide-list/guide-list.component';
import { FighterListComponent } from './components/fighter/fighter-list/fighter-list.component';
import { UserFormComponent } from './components/user-form/user-form.component';
import { UserListComponent } from './components/user-list/user-list.component';

export const routes: Routes = [ 
    {path: "guide-form", component: GuideFormComponent},
    {path: "guide-list", component: GuideListComponent},
    {path: "user-form", component: UserFormComponent},
    {path: "user-list", component: UserListComponent},
    {path: "fighter-list", component: FighterListComponent},
    
];
