import { Routes } from '@angular/router';
import { NotFoundComponent } from './layouts/not-found/not-found.component';
import { SignInComponent } from './features/sign-in/sign-in.component';
import { SignupComponent } from './features/signup/signup.component';
import { AuthPinComponent } from './features/auth-pin/auth-pin.component';
import { ConfirmEmailComponent } from './features/confirm-email/confirm-email.component';
import { LandingPageComponent } from './layouts/landing-page/landing-page.component';
import { accountGuard } from './core/guards/account.guard';
export const routes: Routes = [
  { path: 'sign-in', component: SignInComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'confirm/:token', component: ConfirmEmailComponent },
  { path: 'auth-pin/:token', component: AuthPinComponent },
  { path: '', component: LandingPageComponent },
  {
    path: 'rss-providers',
    loadComponent: () =>
      import('./features/rss-providers/rss-providers.component').then(
        (mod) => mod.RssProvidersComponent
      ),
    canActivate: [accountGuard],
  },
  {
    path: 'profile',
    loadComponent: () =>
      import('./layouts/profile/profile.component').then(
        (mod) => mod.ProfileComponent
      ),
  },
  {
    path: 'shareArticle',
    loadComponent: () =>
      import('./layouts/share-article/share-article.component').then(
        (mod) => mod.ShareArticleComponent
      ),
  },
  { path: '**', component: NotFoundComponent },
];
