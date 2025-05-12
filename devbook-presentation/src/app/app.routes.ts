import { Routes } from '@angular/router';
import { NotFoundComponent } from './layouts/not-found/not-found.component';
import { SignInComponent } from './features/sign-in/sign-in.component';
import { SignupComponent } from './features/signup/signup.component';
import { AuthPinComponent } from './features/auth-pin/auth-pin.component';
import { LandingPageComponent } from './layouts/landing-page/landing-page.component';
export const routes: Routes = [
  { path: 'auth-pin/:token', component: AuthPinComponent },
  { path: '', component: LandingPageComponent },
  {
    path: 'profile',
    loadComponent: () => import('./layouts/profile/profile.component')
      .then(mod => mod.ProfileComponent)
  },
  { path: '**', component: NotFoundComponent }

];
