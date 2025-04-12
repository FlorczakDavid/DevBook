import { Routes } from '@angular/router';
import { SignInComponent } from './features/sign-in/sign-in.component';
import { SignupComponent } from './features/signup/signup.component';

export const routes: Routes = [
  { path: 'sign-in', component: SignInComponent },
  { path: 'signup', component: SignupComponent },
];
