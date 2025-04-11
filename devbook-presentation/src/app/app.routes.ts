import { Routes } from '@angular/router';
import { NotFoundComponent } from './layouts/not-found/not-found.component';

export const routes: Routes = [
  {
    path: 'profile',
    loadComponent: () => import('./layouts/profile/profile.component')
      .then(mod => mod.ProfileComponent)
  },
  { path: '**', component: NotFoundComponent }
];
