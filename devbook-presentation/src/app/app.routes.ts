import { Routes } from '@angular/router';
import { NotFoundComponent } from './layouts/not-found/not-found.component';
import { SignInComponent } from './features/sign-in/sign-in.component';
import { SignupComponent } from './features/signup/signup.component';
import { AuthPinComponent } from './features/auth-pin/auth-pin.component';
import { LandingPageComponent } from './layouts/landing-page/landing-page.component';
import {accountGuard} from './core/guards/account.guard';
import {roleGuard} from './core/guards/role.guard';
export const routes: Routes = [
  { path: 'auth-pin/:token', component: AuthPinComponent },
  { path: '', component: LandingPageComponent},
  {
    path: 'rss-providers',
    loadComponent: () => import('./features/rss-providers/rss-providers.component')
      .then(mod => mod.RssProvidersComponent),
    canActivate: [roleGuard]
  },
  {
    path: 'profile',
    loadComponent: () => import('./layouts/profile/profile.component')
      .then(mod => mod.ProfileComponent),
    canActivate: [accountGuard]
  },
  {
    path:'shareArticle',
    loadComponent: () => import('./layouts/share-article/share-article.component')
    .then(mod=>mod.ShareArticleComponent),
    canActivate: [accountGuard]
  },
  {
    path: 'auth',
    loadComponent: () => import('./layouts/landing-page/pages/anonymous-home/anonymous-home.component')
      .then(mod=>mod.AnonymousHomeComponent),
  },

  { path: '**', component: NotFoundComponent }
];
