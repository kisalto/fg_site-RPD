import { Routes } from '@angular/router';
import { GuideFormComponent } from './components/guide-form/guide-form.component';
import { GuideListComponent } from './components/guide-list/guide-list.component';

export const routes: Routes = [ 
    {path: "guide-form", component: GuideFormComponent},
    {path: "guide-list", component: GuideListComponent},
];
