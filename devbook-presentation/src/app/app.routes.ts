import { Routes } from '@angular/router';
import {SignInComponent} from './features/sign-in/sign-in.component'
import { AuthPinComponent } from './features/auth-pin/auth-pin.component';

export const routes: Routes = [
    { path: 'sign-in', component: SignInComponent },
    { path: 'auth-pin/:token', component: AuthPinComponent }
];
